# Source Formatter Development

## Developing new checks

When developing a new SF checks, you must first decide if the check is better as a "Source check" or
as a "Checkstyle check".

### Writing a Source check

Choose "Source check" if most of the following apply:

- You are writing a SF check that targets languages other than `*.java` or `*.jsp`.
- You are writing a SF check for more than one language (limited).
- You want to autocorrect and/or report issues.

If you are writing a "Source check", follow these steps:

1. Write your SF check as a class in package `com.liferay.source.formatter.check`.
2. Register your SF check in `sourcechecks.xml` under the appropriate `source-processor` tag.
3. Push your local changes to your `liferay-portal` repository using `./gradlew pushCode`.

### Writing a Checkstyle check

Choose "Checkstyle check" if most of the following apply:

- You are writing a SF check that only targets `*.java` and/or `*.jsp` files.
- You want the SF check to target precise parts of the code (e.g. method declarations, annotations, etc.).
- You want to only report issues, not autocorrect them.

If you are writing a "Checkstyle check", follow these steps:

1. Write the SF check as a class in package `com.liferay.source.formatter.checkstyle.check`.
2. Register your SF check in `checkstyle.xml` and/or `checkstyle-jsp.xml` (if you want to apply the rule to JSP files).
3. Push your local changes to your `liferay-portal` repository using `./gradlew pushCode`.

## Generating documentation

SF can automatically generate Markdown documentation for new checks, or update existing ones. See the
following checks to see how:

- [MarkdownSourceFormatterReadmeCheck.java](https://github.com/liferay/liferay-portal/blob/master/modules/util/source-formatter/src/main/java/com/liferay/source/formatter/check/MarkdownSourceFormatterReadmeCheck.java)
- [MarkdownSourceFormatterDocumentationCheck.java](https://github.com/liferay/liferay-portal/blob/master/modules/util/source-formatter/src/main/java/com/liferay/source/formatter/check/MarkdownSourceFormatterDocumentationCheck.java)

You can add dedicated Markdown documentation for your new SF check by adding a new Markdown file in
the `src/main/resources/check` directory. For example, if your new check is named `JavaHelloWorldCheck.java`,
add a new Markdown file in `src/main/resources/documentation/check` called `java_hello_world_check.markdown`.

For the time being, markdown documentation can only be generated through your `liferay-portal`
repository. After pushing a new SF check via `./gradlew pushSource`, do the following:

1. Navigate to `modules/util/source-formatter` directory of your `liferay-portal` repository.
2. Add Markdown file to `src/main/resources/documentation/check` documenting your new check.
3. Run `gw formatSource` to generate documentation.

## Resources

For more information, refer to the following links:

- <https://github.com/liferay/liferay-portal/blob/master/modules/util/source-formatter>
- <https://github.com/kevhlee/liferay-portal/blob/LPS-144213/modules/util/source-formatter/documentation/source-formatter/source-formatter.markdown>