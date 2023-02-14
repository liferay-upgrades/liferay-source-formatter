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

To set up the project, run the following command:

```shell
./gradlew pullCode
```

This command pulls Source Formatter code from your `liferay-portal` repository to this project. Make
sure to save all local changes before running this command.

To push local changes in this project to `liferay-portal`, run the following command:

```shell
./gradlew pushCode
```

This will overwrite and sync files in this project to the ones in your `liferay-portal` repository.