package com.liferay.dev;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Kevin Lee
 */
public class PullSyncCodeTask extends BaseSyncCodeTask {

    @Override
    protected Path getSourcePath(Path repoPath) {
        return repoPath.resolve("modules/util/source-formatter/src/main");
    }

    @Override
    protected Path getDestinationPath(Path repoPath) {
        return Paths.get("./src");
    }

}