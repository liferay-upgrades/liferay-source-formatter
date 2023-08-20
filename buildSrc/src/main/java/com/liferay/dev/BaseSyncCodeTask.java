package com.liferay.dev;

import com.github.fracpete.rsync4j.RSync;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Kevin Lee
 */
public abstract class BaseSyncCodeTask extends DefaultTask {

    @TaskAction
    public final void sync() throws Exception {
        Project project = getProject();

        Path repoPath = Paths.get(
                String.valueOf(project.findProperty("liferay.portal.repo.dir")));

        Path sourcePath = getSourcePath(repoPath);
        Path destinationPath = getDestinationPath(repoPath);

        if (Files.notExists(destinationPath)) {
            Files.createDirectories(destinationPath);
        }

        RSync rsync = new RSync()
                .source(sourcePath.toString())
                .destination(destinationPath.toString())
                .exclude("**/com/liferay/dev")
                .recursive(true)
                .delete(true)
                .deleteExcluded(false)
                .quiet(true);

        rsync.execute();
    }

    protected abstract Path getSourcePath(Path repoPath);

    protected abstract Path getDestinationPath(Path repoPath);

}
