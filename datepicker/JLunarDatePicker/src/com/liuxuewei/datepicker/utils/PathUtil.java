package com.liuxuewei.datepicker.utils;

import java.io.File;

public class PathUtil {
	public static String imagePackage="com.liuxuewei.datepicker.images";
	public static String dotToSlash(String oldString) {
		return oldString.replace(getDot(), getSlash());
	}

	public static String getDot() {
		return ".";
	}

	public static String getSlash() {
		return File.separator;
	}
}
