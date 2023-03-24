# Liferay Source Formatter

[Liferay Source Formatter](https://github.com/liferay/liferay-portal/tree/master/modules/util/source-formatter) as a
standalone project for easier development.

## Setup

__Requirements:__

- `liferay-portal` repository

This development environment relies on your [Liferay Portal](https://github.com/liferay/liferay-portal/)
repository. It is important to keep your `liferay-portal` up-to-date when using this project.

To get started, create a `gradle.properties` file in the root directory and set the field `liferay.portal.repo.dir`:

```properties
liferay.portal.repo.dir=[insert path to your Liferay Portal repository]
```

## Usage

This project provides several Gradle tasks for developing and running SF checks.

The `pullCode` task pulls Source Formatter code from your `liferay-portal` repository to this project.
Make sure to save all local changes before running this command.

The `pushCode` task pushes local changes in this project to `liferay-portal`. This will overwrite and
sync files in this project to the ones in your `liferay-portal` repository.

You can run Source Formatter code in this project by one of two ways:

- Execute `./src/main/java/com/liferay/dev/SourceFormatterDev.java` from an editor/IDE
- Execute the Gradle task `./gradlew run`

`./gradlew run` takes arguments `args` that configure Source Formatter. For example:

```shell
./gradlew run --args="source.base.dir=[path to directory to format]"
```

To see what you can configure through `args`, check the following documentation:

- <https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-source-formatter#task-properties>
- <https://github.com/liferay/liferay-portal/blob/master/modules/util/source-formatter/src/main/java/com/liferay/source/formatter/SourceFormatter.java#L118-L281>