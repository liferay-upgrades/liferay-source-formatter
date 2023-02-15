package com.liferay.dev.script;

import com.github.fracpete.processoutput4j.output.CollectingProcessOutput;
import com.github.fracpete.rsync4j.RSync;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RSyncCode {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("Expected 2 arguments");
			System.exit(1);
		}

		Path repoDirPath = Paths.get(args[1]);

		if (!Files.exists(repoDirPath)) {
			System.err.printf("'%s' is not a directory\n", repoDirPath);
			System.exit(1);
		}
		else if (!Files.exists(repoDirPath.resolve(".git"))) {
			System.err.printf("'%s' is not a git repository\n", repoDirPath);
			System.exit(1);
		}

		try {
			CollectingProcessOutput collectingProcessOutput = null;

			String action = args[0];

			switch (action) {
				case "pull":
					collectingProcessOutput = _pullCode(repoDirPath);

					break;
				case "push":
					collectingProcessOutput = _pushCode(repoDirPath);

					break;
				default:
					System.err.printf("'%s' is not a valid command\n", action);
					System.exit(1);

					break;
			}

			int exitCode = collectingProcessOutput.getExitCode();

			if (exitCode != 0) {
				System.err.println(collectingProcessOutput.getStdErr());
			}

			System.exit(exitCode);
		}
		catch (Exception exception) {
			exception.printStackTrace(System.err);
			System.exit(1);
		}
	}

	private static CollectingProcessOutput _pullCode(Path repoDirPath)
		throws Exception {

		Path destinationDirPath = Paths.get("./src");

		if (!Files.exists(destinationDirPath)) {
			File destinationDirFile = destinationDirPath.toFile();

			destinationDirFile.mkdirs();
		}

		Path sourceFormatterDirPath = repoDirPath.resolve(
			"modules/util/source-formatter");

		RSync rsync = new RSync();

		rsync = rsync.source(
			String.valueOf(sourceFormatterDirPath.resolve("src/main"))
		).destination(
			destinationDirPath.toString()
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
		);

		return rsync.execute();
	}

	private static CollectingProcessOutput _pushCode(Path repoDirPath)
		throws Exception {

		Path destinationDirPath = repoDirPath.resolve(
			"modules/util/source-formatter/src");

		if (!Files.exists(destinationDirPath)) {
			File destinationDirFile = destinationDirPath.toFile();

			destinationDirFile.mkdirs();
		}

		Path sourceDirPath = Paths.get("./src/main");

		RSync rsync = new RSync();

		rsync = rsync.source(
			sourceDirPath.toString()
		).destination(
			destinationDirPath.toString()
		).exclude(
			"**/com/liferay/dev"
		).recursive(
			true
		).delete(
			true
		).deleteExcluded(
			true
		).quiet(
			true
		);

		return rsync.execute();
	}

}