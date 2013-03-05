package com.liuxuewei.datepicker.utils;

import java.util.HashMap;

import javax.swing.ImageIcon;


public class ImageUtil {
	private static HashMap<String, ImageIcon> imageRegistry = new HashMap<String, ImageIcon>();

	public static ImageIcon getImageIcon(String imageName, String description) {
		ImageIcon getImageIcon = (ImageIcon) imageRegistry.get(imageName);
		if (getImageIcon == null) {
			getImageIcon = description == null ? new ImageIcon(
					ResouceLoader.getResouce(imageName)) : new ImageIcon(
					ResouceLoader.getResouce(imageName), description);
			imageRegistry.put(imageName, getImageIcon);
		}
		return getImageIcon;
	}
	public static ImageIcon getImageIcon(String imageName) {
		return getImageIcon(imageName, null);
	}
	public static ImageIcon getImageIconByShortName(String imageName) {
		return getImageIcon(PathUtil.dotToSlash(PathUtil.imagePackage) + PathUtil.getSlash() + imageName);
	}
}
