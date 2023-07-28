/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.source.formatter.check;

import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Alan Huang
 */
public class JSCompatibilityCheck extends BaseFileCheck {

	@Override
	protected String doProcess(
		String fileName, String absolutePath, String content) {

		return StringUtil.replace(
			content,
			new String[] {
				"'javascript:void(0);'", "\"javascript:void(0);\"",
				"\\\"javascript:;\\\""
			},
			new String[] {
				"'javascript:void(0);'", "\"javascript:void(0);\"",
				"\\\"javascript:void(0);\\\""
			});
	}

}