RSKAndroidLibraries
===================

This project contains some useful utility classes that you may want to use in your Android Apps.
**P.S:** I'll keep updating the README as I add more utility classes/functionality.


**How To Seek Ratings/Reviews From User**

If you want to solicit user reviews/ratings, for your app, add this snippet of code to the onCreate() method of your Main Activity:

		// First - import these classes
		import net.sharathkumar.android.utils.ratings.RateThisApp;
		import net.sharathkumar.android.utils.exceptions.RatingsException;

		// Replace the ENTER_YOUR_APPS_NAME and ENTER_YOUR_APPS_PACKAGE_NAME parameters with appropriate values
		try {
			RateThisApp.appLaunched(this, "ENTER_YOUR_APPS_NAME", "ENTER_YOUR_APPS_PACKAGE_NAME");
		} catch (RatingsException err) {
			// Add your exception handling logic here
		}


**How To Notify User**

If you want to get the users attention, by vibrating the device, add this snippet of code to your Activity class:
		
		// First - import this class
		import net.sharathkumar.android.utils.device.alerts.AlertThisDevice;

		// This will vibrate the device for 500 millisecs
		AlertThisDevice.vibrateThisDevice(this);

If you REALLY want to get the users attention, by vibrating the device, add this snippet of code to your Activity class:
		
		// First - import this class
		import net.sharathkumar.android.utils.device.alerts.AlertThisDevice;

		// This will vibrate the device - to be more specific, the device will vibrates to an S.O.S morse code pattern - for approx 3000 millisecs
		AlertThisDevice.vibrateThisDeviceLikeYouMeanIt(this);

**Prevent Users From Taking Screenshots**

If you want to prevent users from being able to take screenshots within your Apps, add this snippet of code - in the onCreate() method - of every Activity class where this behavior is desired:
		
		// First - import this class
		import net.sharathkumar.android.utils.device.screens.ThisDeviceScreen;

		// This will prevent the user from taking a screenshot.
		ThisDeviceScreen.preventScreenCapture(this);

**Allow Users From Taking Screenshots**

If you prevented users from being able to take screenshots within your Apps, but now want to allow it, add this snippet of code - in the onCreate() method - of every Activity class where this behavior is desired:
		
		// First - import this class
		import net.sharathkumar.android.utils.device.screens.ThisDeviceScreen;

		// This will allow the user to take a screenshot.
		ThisDeviceScreen.allowScreenCapture(this);