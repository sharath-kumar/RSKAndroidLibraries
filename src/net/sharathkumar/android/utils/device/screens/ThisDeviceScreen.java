package net.sharathkumar.android.utils.device.screens;

import net.sharathkumar.android.utils.exceptions.ScreenshotException;
import android.app.Activity;
import android.view.WindowManager.LayoutParams;

public class ThisDeviceScreen {
	
	public static void preventScreenCapture(Activity activityContextInput) throws ScreenshotException {
		try {
			activityContextInput.getWindow().setFlags(LayoutParams.FLAG_SECURE, LayoutParams.FLAG_SECURE);
		} catch(Exception err) {
			throw new ScreenshotException("Exception encountered in ThisDeviceScreen.preventScreenCapture() : " + err.getMessage());
		}
	}
	
	public static void allowScreenCapture(Activity activityContextInput) throws ScreenshotException {
		try {
			activityContextInput.getWindow().clearFlags(LayoutParams.FLAG_SECURE);
		} catch(Exception err) {
			throw new ScreenshotException("Exception encountered in ThisDeviceScreen.allowScreenCapture() : " + err.getMessage());
		}
	}	

}
