package com.liferay.dev;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.tools.ArgumentsUtil;
import com.liferay.portal.tools.GitException;
import com.liferay.portal.tools.GitUtil;
import com.liferay.portal.tools.ToolsUtil;
import com.liferay.source.formatter.SourceFormatter;
import com.liferay.source.formatter.SourceFormatterArgs;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import java.util.Arrays;
import java.util.Map;

public class SourceFormatterDev {

	public static void main(String[] args) throws Exception {
		SourceFormatterArgs sourceFormatterArgs = _getSourceFormatterArgs(args);

		try {
			SourceFormatter sourceFormatter = new SourceFormatter(
				sourceFormatterArgs);

			sourceFormatter.format();
		}
		catch (Exception exception) {
			if (exception instanceof GitException) {
				System.out.println(exception.getMessage());
			}
			else {
				CheckstyleException checkstyleException =
					_getNestedCheckstyleException(exception);

				if (checkstyleException != null) {
					checkstyleException.printStackTrace();
				}
				else {
					exception.printStackTrace(System.err);
				}
			}

			System.exit(1);
		}
	}

	private static CheckstyleException _getNestedCheckstyleException(
		Exception exception) {

		Throwable throwable = exception;

		while (true) {
			if (throwable == null) {
				return null;
			}

			if (throwable instanceof CheckstyleException) {
				return (CheckstyleException)throwable;
			}

			throwable = throwable.getCause();
		}
	}

	private static SourceFormatterArgs _getSourceFormatterArgs(String[] args)
		throws Exception {

		Map<String, String> arguments = ArgumentsUtil.parseArguments(args);
		SourceFormatterArgs sourceFormatterArgs = new SourceFormatterArgs();

		sourceFormatterArgs.setAutoFix(
			ArgumentsUtil.getBoolean(
				arguments, "source.auto.fix", SourceFormatterArgs.AUTO_FIX));

		String baseDirName = ArgumentsUtil.getString(
			arguments, "source.base.dir", SourceFormatterArgs.BASE_DIR_NAME);

		sourceFormatterArgs.setBaseDirName(baseDirName);

		sourceFormatterArgs.setCheckCategoryNames(
			ListUtil.fromString(
				ArgumentsUtil.getString(
					arguments, "source.check.category.names", null),
				StringPool.COMMA));
		sourceFormatterArgs.setCheckNames(
			ListUtil.fromString(
				ArgumentsUtil.getString(arguments, "source.check.names", null),
				StringPool.COMMA));
		sourceFormatterArgs.setFailOnAutoFix(
			ArgumentsUtil.getBoolean(
				arguments, "source.fail.on.auto.fix",
				SourceFormatterArgs.FAIL_ON_AUTO_FIX));
		sourceFormatterArgs.setFailOnHasWarning(
			ArgumentsUtil.getBoolean(
				arguments, "source.fail.on.has.warning",
				SourceFormatterArgs.FAIL_ON_HAS_WARNING));
		sourceFormatterArgs.setFormatCurrentBranch(
			ArgumentsUtil.getBoolean(
				arguments, "format.current.branch",
				SourceFormatterArgs.FORMAT_CURRENT_BRANCH));
		sourceFormatterArgs.setFormatLatestAuthor(
			ArgumentsUtil.getBoolean(
				arguments, "format.latest.author",
				SourceFormatterArgs.FORMAT_LATEST_AUTHOR));
		sourceFormatterArgs.setFormatLocalChanges(
			ArgumentsUtil.getBoolean(
				arguments, "format.local.changes",
				SourceFormatterArgs.FORMAT_LOCAL_CHANGES));
		sourceFormatterArgs.setGitWorkingBranchName(
			ArgumentsUtil.getString(
				arguments, "git.working.branch.name",
				SourceFormatterArgs.GIT_WORKING_BRANCH_NAME));

		int commitCount = ArgumentsUtil.getInteger(
			arguments, "commit.count", SourceFormatterArgs.COMMIT_COUNT);

		sourceFormatterArgs.setCommitCount(commitCount);

		if (commitCount > 0) {
			sourceFormatterArgs.addRecentChangesFileNames(
				GitUtil.getModifiedFileNames(baseDirName, commitCount),
				baseDirName);
		}
		else if (sourceFormatterArgs.isFormatCurrentBranch()) {
			sourceFormatterArgs.addRecentChangesFileNames(
				GitUtil.getCurrentBranchFileNames(
					baseDirName, sourceFormatterArgs.getGitWorkingBranchName(),
					false),
				baseDirName);
		}
		else if (sourceFormatterArgs.isFormatLatestAuthor()) {
			sourceFormatterArgs.addRecentChangesFileNames(
				GitUtil.getLatestAuthorFileNames(baseDirName, false),
				baseDirName);
		}
		else if (sourceFormatterArgs.isFormatLocalChanges()) {
			sourceFormatterArgs.addRecentChangesFileNames(
				GitUtil.getLocalChangesFileNames(baseDirName, false),
				baseDirName);
		}

		String[] fileNames = StringUtil.split(
			ArgumentsUtil.getString(
				arguments, "source.files", StringPool.BLANK),
			StringPool.COMMA);

		if (ArrayUtil.isNotEmpty(fileNames)) {
			sourceFormatterArgs.setFileNames(Arrays.asList(fileNames));
		}
		else {
			String fileExtensionsString = ArgumentsUtil.getString(
				arguments, "source.file.extensions", StringPool.BLANK);

			String[] fileExtensions = StringUtil.split(
				fileExtensionsString, StringPool.COMMA);

			sourceFormatterArgs.setFileExtensions(
				Arrays.asList(fileExtensions));
		}

		sourceFormatterArgs.setIncludeGeneratedFiles(
			ArgumentsUtil.getBoolean(
				arguments, "include.generated.files",
				SourceFormatterArgs.INCLUDE_GENERATED_FILES));

		boolean includeSubrepositories = ArgumentsUtil.getBoolean(
			arguments, "include.subrepositories",
			SourceFormatterArgs.INCLUDE_SUBREPOSITORIES);

		for (String recentChangesFileName :
				sourceFormatterArgs.getRecentChangesFileNames()) {

			if (recentChangesFileName.endsWith("ci-merge")) {
				includeSubrepositories = true;

				break;
			}
		}

		sourceFormatterArgs.setIncludeSubrepositories(includeSubrepositories);

		sourceFormatterArgs.setMaxLineLength(
			ArgumentsUtil.getInteger(
				arguments, "max.line.length",
				SourceFormatterArgs.MAX_LINE_LENGTH));
		sourceFormatterArgs.setMaxDirLevel(
			Math.max(
				ToolsUtil.PORTAL_MAX_DIR_LEVEL,
				StringUtil.count(baseDirName, CharPool.SLASH) + 1));
		sourceFormatterArgs.setOutputFileName(
			ArgumentsUtil.getString(
				arguments, "output.file.name",
				SourceFormatterArgs.OUTPUT_FILE_NAME));
		sourceFormatterArgs.setPrintErrors(
			ArgumentsUtil.getBoolean(
				arguments, "source.print.errors",
				SourceFormatterArgs.PRINT_ERRORS));
		sourceFormatterArgs.setProcessorThreadCount(
			ArgumentsUtil.getInteger(
				arguments, "processor.thread.count",
				SourceFormatterArgs.PROCESSOR_THREAD_COUNT));
		sourceFormatterArgs.setShowDebugInformation(
			ArgumentsUtil.getBoolean(
				arguments, "show.debug.information",
				SourceFormatterArgs.SHOW_DEBUG_INFORMATION));

		String[] skipCheckNames = StringUtil.split(
			ArgumentsUtil.getString(
				arguments, "skip.check.names", StringPool.BLANK),
			StringPool.COMMA);

		if (ArrayUtil.isNotEmpty(skipCheckNames)) {
			sourceFormatterArgs.setSkipCheckNames(
				Arrays.asList(skipCheckNames));
		}

		String[] sourceFormatterProperties = StringUtil.split(
			ArgumentsUtil.getString(
				arguments, "source.formatter.properties", StringPool.BLANK),
			"\\n");

		if (ArrayUtil.isNotEmpty(sourceFormatterProperties)) {
			sourceFormatterArgs.setSourceFormatterProperties(
				Arrays.asList(sourceFormatterProperties));
		}

		sourceFormatterArgs.setValidateCommitMessages(
			ArgumentsUtil.getBoolean(
				arguments, "validate.commit.messages",
				SourceFormatterArgs.VALIDATE_COMMIT_MESSAGES));

		return sourceFormatterArgs;
	}

}