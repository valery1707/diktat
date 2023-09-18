package com.saveourtool.diktat.ruleset.rules

import com.saveourtool.diktat.api.DiktatErrorEmitter
import com.saveourtool.diktat.common.config.rules.RulesConfig
import com.saveourtool.diktat.common.config.rules.isRuleEnabled
import com.saveourtool.diktat.ruleset.utils.getFilePathSafely

import mu.KotlinLogging
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

private typealias DiktatConfigRule = com.saveourtool.diktat.common.config.rules.Rule
private typealias DiktatRuleApi = com.saveourtool.diktat.api.DiktatRule

/**
 * This is a wrapper around _KtLint_ `com.pinterest.ktlint.core.Rule`.
 *
 * @property id id of the rule
 * @property configRules all rules from configuration
 * @property inspections warnings that are used in the rule's code
 */
@Suppress("TooGenericExceptionCaught")
abstract class DiktatRule(
    override val id: String,
    val configRules: List<RulesConfig>,
    private val inspections: List<DiktatConfigRule>,
) : DiktatRuleApi {
    /**
     * Default value is false
     */
    var isFixMode: Boolean = false

    /**
     * The **file-specific** error emitter, initialized in
     * [invoke] and used in [logic] implementations.
     *
     * Since the file is indirectly a part of the state of a `Rule`, the same
     * `Rule` instance should **never be re-used** to check more than a single
     * file, or confusing effects (incl. race conditions) will occur.
     * See the documentation of the [com.pinterest.ktlint.core.Rule] class for more details.
     *
     * @see com.pinterest.ktlint.core.Rule
     * @see invoke
     * @see logic
     */
    lateinit var emitWarn: DiktatErrorEmitter

    /**
     * @param node
     * @param autoCorrect
     * @param emitter
     * @throws Error
     */
    @Suppress("TooGenericExceptionThrown")
    override fun invoke(
        node: ASTNode,
        autoCorrect: Boolean,
        emitter: DiktatErrorEmitter
    ) {
        emitWarn = emitter
        isFixMode = autoCorrect

        if (areInspectionsDisabled()) {
            return
        } else {
            try {
                logic(node)
            } catch (internalError: Throwable) {
                log.error(
                    """Internal error has occurred in rule [$id]. Please make an issue on this bug at https://github.com/saveourtool/diKTat/.
                       As a workaround you can disable these inspections in yml config: <$inspections>.
                       Root cause of the problem is in [${node.getFilePathSafely()}] file.
                    """.trimIndent(), internalError
                )
                // we are very sorry for throwing common Error here, but unfortunately we are not able to throw
                // any existing Exception, as they will be caught in ktlint framework and the logging will be confusing:
                // in this case it will incorrectly ask you to report issues in diktat to ktlint repository
                throw Error("Internal error in diktat application", internalError)
            }
        }
    }

    private fun areInspectionsDisabled(): Boolean =
        inspections.none { configRules.isRuleEnabled(it) }

    /**
     * Logic of the rule
     *
     * @param node node that are coming from visit
     */
    abstract fun logic(node: ASTNode)

    companion object {
        private val log = KotlinLogging.logger {}
    }
}