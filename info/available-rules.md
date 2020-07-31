| Chap | Standard | Rule name                               | Description                                                                                                                       | Fix | Tests | Config | FixMe |
| ---- | -------- | --------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------- | --- | ----- | ------ | ----- |
|    1 |      1.3 | PACKAGE_NAME_MISSING                    | Check: warns if package name is missing in a file<br>Fix: automatically adds package directive with the name that starts from the domain name (in example - com.huawei) and contains the real directory| yes |PackageNamingWarnTest<br>    missing package name (check)PackageNamingFixTest<br>    missing company name (fix)| no |Domain name should not be hardcoded. It should be moved to extra configuration.Recursively fix all imports in project.Fix the directory where the code is stored.Make this check isolated from domain name addition|
|    1 |      1.3 | PACKAGE_NAME_INCORRECT_CASE             | Check: warns if package name is in incorrect (non-lower) case<br>Fix: automatically update the case in package name| yes |PackageNamingWarnTest<br>    package name should be in a lower case (check)PackageNamingFixTest<br>     incorrect case of package name (fix)| no |Recursively update all imports in the project.|
|    1 |      1.3 | PACKAGE_NAME_INCORRECT_PREFIX           | Check: warns if package name does not start with the company's domain<br>Fix: automatically update the prefix in the package name| yes |PackageNamingWarnTest<br>    package name should start from domain namePackageNamingFixTest<br>     fixing incorrect domain name (fix)| no |Fix the directory where the code is stored.Recursively update all imports in the project.Domain name should not be hardcoded. It should be moved to extra configuration.|
|    1 |      1.3 | PACKAGE_NAME_INCORRECT_SYMBOLS          | Check: warns if package name has incorrect symbols like underscore or non-ASCII letters/digits.Exception: underscores that are used for differentiating of keywords in a name.<br>Fix: no but will be| no |PackageNamingWarnTest<br>    underscore exceptions - incorrect underscore case    incorrect symbol in package name    underscore exceptions - positive case - keyword| no |Add autofix for at least converting underscore to a dot or replacing itFix the directory where the code is stored.Cover autofix with tests|
|    1 |      1.3 | PACKAGE_NAME_INCORRECT_PATH             | Check: warns if the path for a file does not match with a package name<br>Fix: replacing incorrect package name with the name constructed from a path to the file.  | yes |PackageNamingWarnTest<br>    missing package name (check)<br>    package name should be in a lower case (check)<br>    package name should start from domain name (check)<br>    underscore exceptions - incorrect underscore case<br>    incorrect symbol in package name<br>    underscore exceptions - positive case - keyword<br>    regression - incorrect warning on file under test directory<br>    <br><br>PackagePathFixTest<br>     fixing package name that differs from a path (fix)<br>    fix missing package name with a proper location (fix)<br>    fixing package name that differs from a path     without domain (fix)<br>    fix missing package name with a proper location     without domain (fix)| no |Make this check isolated from domain name creationRecursively update all imports in the project.Fix the directory where the code is stored.Add test mechanism to test checker|
|    1 |      1.3 | INCORRECT_PACKAGE_SEPARATOR             | Check: warns if underscore is incorrectly used to split package naming<br>Fix: fixing all nodes in AST and the package name to remove all underscores |  no | PackageNamingFixTest<br>     incorrect usage of package separator (fix)| no |Recursively update usages of this class in the project|
|    1 |      1.4 | CLASS_NAME_INCORRECT                    | Check: warns if the Class/Enum/Interface name does not match Pascal case ("([A-Z][a-z0-9]+)+")<br>Fix: fixing the case: if the it is some fixed case (like snake/camel) - with word saving. If not - will restore PascalCase as is.| yes |IdentifierNamingWarnTest<br>      check class name (check)<br>    check identifiers case format (check - negative)IdentifierNamingFixTest<br>     incorrect class name (fix)| no |Recursively update usages of this class in the projectCheck and decide the better way on converting identifier to PascalCaseNeed to add checks using natural language processing to check that class name contains only nouns|
|    1 |      1.2 | OBJECT_NAME_INCORRECT                   | Check: warns if the object does not match Pascal case ("([A-Z][a-z0-9]+)+")<br>Fix: fixing the case in the same way as for classes| yes      | IdentifierNamingWarnTest<br>    check case for object (check - negative)<br>IdentifierNamingFixTest<br>    incorrect object name (fix)| no |Recursively update usages of this class in the project|
|    1 |      1.2 | VARIABLE_NAME_INCORRECT_FORMAT          | Check: warns if the name of variable is not in lowerCamelCase or contains non-ASCII letters<br>Fix: fixing the case format to lowerCamelCase|  | IdentifierNamingWarnTest<br>    check identifiers case format (check - negative)| no |-|
|    1 |      1.7 | VARIABLE_NAME_INCORRECT                 | Check: warns if variable contains one single letter, only exceptions are fixed names that used in industry like {i, j}<br>Fix: no fix as we are not able to imagine good name by machine| no |IdentifierNamingWarnTest<br>    check identifiers case format (check - negative)| no |Recursively update usages of this class in the projectMake exceptions configurable|
|    1 |      1.6 | CONSTANT_UPPERCASE                      | Check: warns if CONSTANT (treated as const val from companion object or class level) is in non UPPER_SNAKE_CASE<br>Fix: name is changed to UPPER_SNAKE_CASE| yes |IdentifierNamingWarnTest<br>    check identifiers case format (check - negative)| no |Recursively update usages of this identifier in the project|
|    1 |      1.5 | VARIABLE_HAS_PREFIX                     | Check: warns if variable has prefix (like mVariable or M_VARIABLE), generally it is a bad code style (Android - is the only exception<br>Fix: no fix as we are not able to imagine good name by machine| no |IdentifierNamingWarnTest<br>     checking that there should be no prefixes    in variable name| no |may be we can fix it, but I do not see any sense for it|
|    1 |      1.2 | IDENTIFIER_LENGTH                       | Check: identifier length should be in range [2,64] except names that used in industry like {i, j}<br>Fix: no fix as we are not able to imagine good name by machine          | no |IdentifierNamingWarnTest<br>     check variable length (check - negative)<br>    regression - function argument type| no |May be make this rule configurable (length)|
|    1 |      1.4 | ENUM_VALUE                              | Check:  warns if enum value is in non UPPER_SNAKE_CASE<br>Fix: automatically converting case to UPPER_SNAKE_CASE                                                             | yes |IdentifierNamingWarnTest<br>    check case for enum values (check - negative)IdentifierNamingFixTest<br>     incorrect enum values case (fix)| no |Recursively update usages of this identifier in the project|
|    1 |      1.2 | GENERIC_NAME                            | Check: warns if generic name contains more than 1 letter (capital). It can be followed by numbers, example: T12, T<br>Fix:                                                   | yes |IdentifierNamingWarnTest<br>    generic class - single capital letter,    can be followed by a number (check - negative1)<br>    generic class - single capital letter,    can be followed by a number (check - negative2)| no |Recursively update usages of this identifier in the project|
|    1 |      1.5 | FUNCTION_NAME_INCORRECT_CASE            | Check: function/method name should be in lowerCamelCase:<br>Fix:                                                                                                             | yes | MethodNamingWarnTest<br>     method name incorrect, part 1<br>    method name incorrect, part 2<br>      method name incorrect, part 3<br>    method name incorrect, part 4<br>    method name incorrect, part 5| no |Recursively update usages of this function in the project|
|    1 |      1.5 | FUNCTION_BOOLEAN_PREFIX                 | Check: functions/methods that return boolean should have special prefix like "is/should/e.t.c"<br>Fix:                                                                       | yes | MethodNamingWarnTest<br>     boolean method name incorrect<br>IdentifierNamingWarnTest<br>    FUNCTION_BOOLEAN_PREFIX - positive example<br>    FUNCTION_BOOLEAN_PREFIX - negative example| no |Recursively update usages of this function in the projectAggressive fix - what if new name will not be valid|
|    1 |      1.2 | FILE_NAME_INCORRECT                     | Check: warns if file name does not have extension .kt/.kts<br>Fix: no                                                                                                        |  no | ? | no |Extensions should be configurableIt can be aggressively autofixed|
|    1 |      1.2 | FILE_NAME_MATCH_CLASS                   | Check: warns<br>Fix: no                                                                                                                                                      |  no | ? | no | Probably it can be autofixed, but it will be aggressive fix|
|    1 |      1.2 | EXCEPTION_SUFFIX                        | Check: warns if class that extends any Exception class does not have Exception suffix<br>Fix: Adding suffix "Exception" to a class name                                      | yes | IdentifierNamingWarnTest<br>     check exception case and suffix     (with type call entry) - negative<br>     check exception case and suffix     (only parent name inheritance) - negative| no |Need to add tests for this|
|    2 |      2.1 | MISSING_KDOC_TOP_LEVEL                  | Check: warns on file level internal or public class or function has missing KDoc<br>Fix: no                                                                                  |  no | KdocWarnTest<br>    all public classes should be documented with KDoc<br>    all internal classes should be documented with KDoc<br>    all internal and public functions on top-level should be documented with Kdoc<br>    all internal and public functions on top-level should be documented with Kdoc (positive case)<br>    positive Kdoc case with private class| no |Support extension for setters/gettersSupport extension for method "override"|
|    2 |      2.1 | MISSING_KDOC_CLASS_ELEMENTS             | Check: warns if accessible internal elements (protected, public, internal) in a class are not documented<br>Fix: no                                                          |  no | KdocWarnTest<br>    Kdoc should present for each class element    Kdoc should present for each class element (positive)| no |May be need to add exception cases for setters and getters. There is no sense in adding KDoc to them.|
|    2 |      2.1 | MISSING_KDOC_ON_FUNCTION                | Check: warns if accessible function doesn't have KDoc<br>Fix: adds KDoc template if it is not empty                                                                          | yes | - | - | |
|    2 |      2.2 | KDOC_WITHOUT_PARAM_TAG                  | Check: warns if accessible method has parameters and they are not documented in KDoc<br>Fix: If accessible method has no KDoc, KDoc template is added                        | yes | KdocSignatureTest<br>    Accessible methods with parameters, return type and throws       should have proper KDoc (positive example)<br>    Warning should not be triggered for private functions<br>    Empty parameter list should not trigger warning about @param<br>    All methods with parameters should have @param KDoc      for each parameter<br>    Rule should suggest KDoc template for missing KDocs| no |Should also make separate fix, even if there is already some Kdoc in place|
|    2 |      2.2 | KDOC_WITHOUT_RETURN_TAG                 | Check: warns if accessible method has explicit return type they it is not documented in KDoc<br>Fix: If accessible method has no KDoc, KDoc template is added                | yes | KdocSignatureTest<br>    All methods with explicit return type excluding Unit      should have @return KDoc                |  | Should also make separate fix, even if there is already some Kdoc in place |
|    2 |      2.2 | KDOC_WITHOUT_THROWS_TAG                 | Check: warns if accessible method has throw keyword and it is not documented in KDoc<br>Fix: If accessible method has no KDoc, KDoc template is added                        | yes | KdocSignatureTest<br>    All methods with throw in method body should have      @throws KDoc, throw in comments is ignored |  | Should also make separate fix, even if there is already some Kdoc in place |
|    2 |      2.3 | BLANK_LINE_AFTER_KDOC                   | Check: warns if Kdoc is incorrectly separated with newlines<br>Fix: removing incorrect newlines between Kdoc and it's declaration                                            | yes | KdocFormattingTest<br> There should be no blank line between kdoc and it's declaration codeKdocFormattingFixTest<br> There should be no blank line between kdoc and it's declaration code                                                                          |   | - |
|    2 |      2.3 | KDOC_EMPTY_KDOC                         | Check: warns if KDoc is empty<br>Fix: no                                                                                                                                     |  no | KdocFormattingTest<br>empty KDocs are not allowed - example with empty KDOC_SECTION<br>empty KDocs are not allowed - example with no KDOC_SECTION<br>empty KDocs are not allowed - without bound identifier<br>empty KDocs are not allowed - with anonymous entity | - |   |
|    2 |      2.3 | KDOC_WRONG_SPACES_AFTER_TAG             | Check: warns if there is more than one space after KDoc tag<br>Fix: removes redundant spaces                                                                                 | yes | KdocFormattingTest<br>  KDocs should contain only one white space between tag and     its content (positive example)  KDocs should contain only one white space between tag and its contentKdocFormattingFixTest<br>  There should be exactly one white space after tag name|  ||
|    2 |      2.3 | KDOC_WRONG_TAGS_ORDER                   | Check: warns if basic KDoc tags are not oredered properly<br>Fix: reorders them `@param`, `@return`, `@throws`                                                               | yes | KdocFormattingTest<br>  tags should be ordered in KDocs (positive example)  tags should be ordered in KDocsKdocFormattingFixTest<br>  Tags should be ordered in KDocs|  |Ensure basic tags are at the end of KDoc|
|    2 |      2.3 | KDOC_NEWLINES_BEFORE_BASIC_TAGS         | Check: warns if block of tags @param, @return, @throws is not separated from previous part of KDoc by exactly one empty line<br>Fix: adds empty line or removes redundant    | yes | KdocFormattingTest<br>basic tags block shouldn't have empty line before if there is no other KDoc content<br>basic tags block should have empty line before if there is other KDoc content | - | |
|    2 |      2.3 | KDOC_NO_NEWLINES_BETWEEN_BASIC_TAGS     | Check: if there is newline of empty KDoc line (with leading asterisk) between `@param`, `@return`, `@throws` tags<br>Fix: removes line                                       | yes | KdocFormattingTest<br>  newlines are not allowed between basic tagsKdocFormattingFixTest<br>  basic tags should not have empty lines between|  ||
|    2 |      2.3 | KDOC_NO_NEWLINE_AFTER_SPECIAL_TAG       | Check: warns if special tags `@apiNote`, `@implNote`, `@implSpec` don't have exactly one empty line after<br>Fix: removes redundant lines or adds one                        | yes | KdocFormattingTest<br>  Special tags should have exactly one newline after them (positive example)  Special tags should have exactly one newline after them (no line)  Special tags should have exactly one newline after them (many lines)KdocFormattingFixTest<br>  Special tags should have newline after them|  |Handle empty lines without leading asterisk|
|    2 |      2.3 | KDOC_NO_EMPTY_TAG                       | Check: warns if KDoc tags have empty content                                                                                                                                 |  no | KdocFormattingTestKdoc tags should not have empty content|  ||
|    2 |      2.3 | KDOC_NO_DEPRECATED_TAG                  | Check: warns if `@deprecated` is used in KDoc<br>Fix: adds `@Deprecated` annotation with message, removes tag                                                                | yes | KdocFormattingTest<br> `@deprecated` tag is not allowedKdocFormattingFixTest<br> `@deprecated` tag should be substituted with annotation|  |Annotation's `replaceWith` field can be filled too|
|    2 |      2.4 | HEADER_WRONG_FORMAT                     | Checks: warns if there is no newline after header KDoc<br>Fix: adds newline                                                                                                  | yes | HeaderCommentRuleTest<br>  file header comment (positive example)  header KDoc should have newline after itHeaderCommentRuleFixTest<br>  new line should be inserted after header KDoc|  |Check if header is on the very top of file. It's hard to determine when it's not.|
|    2 |      2.4 | HEADER_MISSING_OR_WRONG_COPYRIGHT       | Checks: copyright exists on top of file and is properly formatted (as a block comment)<br>Fix: adds copyright if it is missing and required                                  | yes | HeaderCommentRuleTest<br>  copyright should not be placed inside KDoc  copyright should not be placed inside single line commentHeaderCommentRuleFixTest<br>  if no copyright is present and mandatoryCopyright=true, it is added|mandatoryCopyright|Allow setting copyright patterns via configuration|
|    2 |      2.4 | HEADER_CONTAINS_DATE_OR_AUTHOR          | Check: warns if header KDoc contains `@author` tag                                                                                                                                   |  no | HeaderCommentRuleTest<br>  `@author tag is not allowed in header comment`|  |Detect author by other patterns (e.g. 'created by' etc.)Detect creation date (no standard tag even in javadoc)|
|    2 |      2.4 | HEADER_MISSING_IN_NON_SINGLE_CLASS_FILE | Check: warns if file with zero or >1 classes doesn't have header KDoc                                                                                                        |  no | HeaderCommentRuleTest<br>  file with zero classes should have header KDoc  file with multiple classes should have header KDoc|  ||
|    2 |      2.7 | COMMENTED_OUT_CODE                      | Check: warns if commented code is detected (when un-commented, can be parsed)                                                                                                |  no | CommentedCodeTest<br>  Should warn if commented out import or package directive is detected (single line comments)  Should warn if commented out imports are detected (block comments)  Should warn if commented out code is detected (block comments)  Should warn if commented out code is detected (single line comments)  Should warn if commented out function is detected (single line comments)  Should warn if commented out function is detected (single line comments with surrounding text)  Should warn if commented out function is detected (block comment)  Should warn if detects commented out code (example with indents)  Should warn if detects commented out code (example with IDEA style indents)|  |Offset is lost when joined EOL comments are split again|
|    2 |      2.4 | HEADER_NOT_BEFORE_PACKAGE               | Check: warns if header KDoc if file is located not before package directive<br>Fix: moves this KDoc                                                                          | yes | HeaderCommentRuleTestheader KDoc should be placed before package and imports|  ||
|    3 |      3.1 | FILE_IS_TOO_LONG                        | Check: warns if file has too many lines<br>Fix: no                                                                                                                           |  no | FileSizeWarnTest                                                                                                                                                                                           | maxSize<br> ignoreFolders |                                                                                     - |
|    3 |      3.2 | FILE_CONTAINS_ONLY_COMMENTS             | Check: warns if file contains only comments, imports and package directive.                                                                                                  |  no | FileStructureRuleTest<br>should warn if file contains only comments                                                                                                                                        |                         - |                                                                                     - |
|    3 |      3.2 | FILE_INCORRECT_BLOCKS_ORDER             | Check: warns if general order of code parts is wrong.<br>Fix: rearranges them.                                                                                               | yes | FileStructureRuleTest<br>should warn if file annotations are not immediately before package directive                                                                                                      |                         - | handle other elements that could be present before package directive (other comments) |
|    3 |      3.2 | FILE_NO_BLANK_LINE_BETWEEN_BLOCKS       | Check: warns if there is not exactly one blank line between code parts.<br>Fix: leaves single empty line                                                                     | yes | FileStructureRuleTest<br>should warn if blank lines are wrong between code blocks                                                                                                                          |                         - |                                                                                     - |
|    3 |      3.2 | FILE_UNORDERED_IMPORTS                  | Check: warns if imports are not sorted alphabetically or contain empty lines among them<br>Fix: reorders imports.                                                            | yes | FileStructureRuleTest<br>should warn if imports are not sorted alphabetically                                                                                                                              |                         - |                                                                                     - |
|    3 |      3.2 | FILE_WILDCARD_IMPORTS                   | Check: warns if wildcard imports are used.                                                                                                                                   |  no | FileStructureRuleTest<br>should warn if wildcard imports are used                                                                                                                                          |                         - |                                                                                     - |
|    3 |      3.3 | NO_BRACES_IN_CONDITIONALS_AND_LOOPS     | Check: warns if braces are not used in if, else, when, for, do, and while statements. Exception: single line if statement.<br>Fix: adds missing braces.                      | yes | ?                                                                                                                                                                                                          |                         - |                                                                                     - |
|    3 |      3.2 | WRONG_ORDER_IN_CLASS_LIKE_STRUCTURES    | Check: warns if the declaration part of a class-like code structures (class/interface/etc.) is not in the proper order.<br>Fix: restores order according to code style guide.| yes | ClassLikeStructuresOrderRuleWarnTest<br>should check order of declarations in classes - positive example<br>should warn if loggers are not on top                                                          |                         - |                                                                                     - |
|    3 |      3.2 | BLANK_LINE_BETWEEN_PROPERTIES           | Check: warns if properties with comments are not separated by a blank line, properties without comments are<br>Fix: fixes number of blank lines                              | yes | ClassLikeStructuresOrderRuleWarnTest<br>comments and KDocs on properties should be prepended by newline - positive example<br>should warn if comments and KDocs on properties are not prepended by newline |                         - |                                                                                     - |
|    3 |      3.4 | BRACES_BLOCK_STRUCTURE_ERROR            | Check: warns if non-empty code blocks with braces don't follow the K&R style (1TBS or OTBS style)                                                                            | yes | BlockStructureBracesWarnTest check all expressions that contain block                                                                                                                                      | openBraceNewline closeBraceNewline |  -
|    3 |      3.4 | EMPTY_BLOCK_STRUCTURE_ERROR             | Check: warns if empty block exist or if it's style is incorrect                                                                                                              | yes | EmptyBlockWarnTest check all expressions with empty block                                                                                                                                                  | allowEmptyBlocks styleEmptyBlockWithNewline |                                                                    - |
|    3 |      3.5 | WRONG_INDENTATION                       | Check: warns if indentation is incorrect<br>Fix: corrects indentation.<br><br>Basic cases are covered currently.                                                             | yes | ?                                                                                                                                                                             |extendedIndentOfParameters<br>alignedParameters<br>extendedIndentAfterOperators<br> |                            - |