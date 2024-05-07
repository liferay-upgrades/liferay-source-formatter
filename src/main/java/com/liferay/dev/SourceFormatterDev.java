package com.liferay.dev;

import com.liferay.portal.tools.GitException;
import com.liferay.source.formatter.SourceFormatter;
import com.liferay.source.formatter.SourceFormatterArgs;

import com.liferay.source.formatter.check.BaseFileCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import java.util.Arrays;

public class SourceFormatterDev {

	public static void main(String[] args) throws Exception {
		SourceFormatterArgs sourceFormatterArgs = _getSourceFormatterArgs(false);

		sourceFormatterArgs.setBaseDirName("/Users/c02fh7y7md6r/Repos/sales-tax");
		sourceFormatterArgs.setCheckCategoryNames(Arrays.asList("Upgrade"));
		sourceFormatterArgs.setCheckNames(Arrays.asList("GradleUpgradeReleaseDXPCheck", "UpgradeCatchAllCheck"));
		sourceFormatterArgs.setJavaParserEnabled(true);

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

	private static SourceFormatterArgs _getSourceFormatterArgs(boolean debug) {
		SourceFormatterArgs sourceFormatterArgs = new SourceFormatterArgs();

		sourceFormatterArgs.setAutoFix(SourceFormatterArgs.AUTO_FIX);
		sourceFormatterArgs.setBaseDirName(SourceFormatterArgs.BASE_DIR_NAME);
		sourceFormatterArgs.setFailOnAutoFix(
			SourceFormatterArgs.FAIL_ON_AUTO_FIX);
		sourceFormatterArgs.setFailOnHasWarning(
			SourceFormatterArgs.FAIL_ON_HAS_WARNING);
		sourceFormatterArgs.setFormatCurrentBranch(
			SourceFormatterArgs.FORMAT_CURRENT_BRANCH);
		sourceFormatterArgs.setFormatLatestAuthor(
			SourceFormatterArgs.FORMAT_LATEST_AUTHOR);
		sourceFormatterArgs.setFormatLocalChanges(
			SourceFormatterArgs.FORMAT_LOCAL_CHANGES);
		sourceFormatterArgs.setGitWorkingBranchName(
			SourceFormatterArgs.GIT_WORKING_BRANCH_NAME);
		sourceFormatterArgs.setCommitCount(SourceFormatterArgs.COMMIT_COUNT);
		sourceFormatterArgs.setIncludeGeneratedFiles(
			SourceFormatterArgs.INCLUDE_GENERATED_FILES);
		sourceFormatterArgs.setIncludeSubrepositories(
			SourceFormatterArgs.INCLUDE_SUBREPOSITORIES);
		sourceFormatterArgs.setMaxLineLength(
			SourceFormatterArgs.MAX_LINE_LENGTH);
		sourceFormatterArgs.setMaxDirLevel(SourceFormatterArgs.MAX_DIR_LEVEL);
		sourceFormatterArgs.setOutputFileName(
			SourceFormatterArgs.OUTPUT_FILE_NAME);
		sourceFormatterArgs.setPrintErrors(SourceFormatterArgs.PRINT_ERRORS);
		sourceFormatterArgs.setProcessorThreadCount(
			SourceFormatterArgs.PROCESSOR_THREAD_COUNT);
		sourceFormatterArgs.setShowDebugInformation(debug);
		sourceFormatterArgs.setValidateCommitMessages(
			SourceFormatterArgs.VALIDATE_COMMIT_MESSAGES);

		return sourceFormatterArgs;
	}

}