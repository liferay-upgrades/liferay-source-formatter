package com.liferay.dev;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.gradle.api.tasks.Input;

/**
 * @author Kevin Lee
 */
public class PushSyncCodeTask extends BaseSyncCodeTask {

	@Input
	@Override
	public String getDescription() {
		return "Synchronizes code from this project to your 'liferay-portal' " +
			"repository";
	}

	@Override
	protected Path getDestinationPath(Path repoPath) {
		return repoPath.resolve("modules/util/source-formatter/src");
	}

	@Override
	protected Path getSourcePath(Path repoPath) {
		return Paths.get("./src/main");
	}

}