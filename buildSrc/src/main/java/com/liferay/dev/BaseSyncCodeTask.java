package com.liferay.dev;

import com.github.fracpete.rsync4j.RSync;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

/**
 * @author Kevin Lee
 */
public abstract class BaseSyncCodeTask extends DefaultTask {

	@Input
	@Override
	public String getGroup() {
		return "dev";
	}

	@TaskAction
	public final void syncCode() throws Exception {
		Project project = getProject();

		if (!project.hasProperty("liferay.portal.repo.dir")) {
			throw new Exception(
				"Please provide path to your Liferay Portal repository by " +
					"setting 'liferay.portal.repo.dir' in 'gradle.properties'");
		}

		Path repoPath = Paths.get(
			String.valueOf(project.property("liferay.portal.repo.dir")));

		Path sourcePath = getSourcePath(repoPath);

		if (Files.notExists(sourcePath)) {
			throw new Exception(sourcePath + " does not exist");
		}

		Path destinationPath = getDestinationPath(repoPath);

		if (Files.notExists(destinationPath)) {
			Files.createDirectories(destinationPath);
		}

		RSync rsync = new RSync();

		rsync.source(
			sourcePath.toString()
		).destination(
			destinationPath.toString()
		).exclude(
			"**/com/liferay/dev"
		).recursive(
			true
		).delete(
			true
		).deleteExcluded(
			false
		).quiet(
			true
		).execute();
	}

	protected abstract Path getDestinationPath(Path repoPath);

	protected abstract Path getSourcePath(Path repoPath);

}