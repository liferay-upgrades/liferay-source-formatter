package com.liferay.dev;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.gradle.api.tasks.Input;

/**
 * @author Kevin Lee
 */
public class PullSyncCodeTask extends BaseSyncCodeTask {

	@Input
	@Override
	public String getDescription() {
		return "Synchronizes code from your 'liferay-portal' repository to " +
			"this project";
	}

	@Override
	protected Path getDestinationPath(Path repoPath) {
		return Paths.get("./src");
	}

	@Override
	protected Path getSourcePath(Path repoPath) {
		return repoPath.resolve("modules/util/source-formatter/src/main");
	}

}