RSKAndroidLibraries
===================

This project contains some useful utils/libraries/tools that you may want to use in your Android Apps.

********************************************
How To Seek Ratings/Reviews From User
********************************************

If you want to solicit user reviews/ratings, for your app, add this snippet of code to the onCreate() method of your Main Activity:

		// First - import these classes
		import net.sharathkumar.android.libs.ratings.RateThisApp;
		import net.sharathkumar.android.libs.exceptions.RatingsException;

		// Replace the ENTER_YOUR_APPS_NAME and ENTER_YOUR_APPS_PACKAGE_NAME parameters with appropriate values
		try {
			RateThisApp.appLaunched(this, "ENTER_YOUR_APPS_NAME", "ENTER_YOUR_APPS_PACKAGE_NAME");
		} catch (RatingsException err) {
			// Add your exception handling logic here
		}


*************************
How To Notify User
*************************

If you want to get the users attention, by vibrating the device, add this snippet of code to your Activity class:
		// First - import this class
		import net.sharathkumar.android.libs.alerts.ThisDevice;

		// This will vibrate the device for 500 millisecs
		ThisDevice.vibrateThisDevice(this);

If you REALLY want to get the users attention, by vibrating the device, add this snippet of code to your Activity class:
		// First - import this class
		import net.sharathkumar.android.libs.alerts.ThisDevice;

		// This will vibrate the device - to be more specific, the device will vibrates to an S.O.S morse code pattern - for approx 3000 millisecs
		ThisDevice.vibrateThisDeviceLikeYouMeanIt(this);