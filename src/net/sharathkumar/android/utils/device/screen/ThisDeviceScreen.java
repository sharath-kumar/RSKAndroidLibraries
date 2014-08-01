package net.sharathkumar.android.utils.device.screen;

import net.sharathkumar.android.utils.exceptions.ScreenshotException;
import android.app.Activity;
import android.view.WindowManager.LayoutParams;

public class ThisDeviceScreen {
	
	public static void preventScreenCapture(Activity activityContextInput) throws ScreenshotException {
		activityContextInput.getWindow().setFlags(LayoutParams.FLAG_SECURE, LayoutParams.FLAG_SECURE);
	}

}
