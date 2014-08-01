/*******************************************************************************
 *    Copyright 2014 Sharath Kumar
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 *    
 *******************************************************************************/
package net.sharathkumar.android.utils.alerts;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.view.WindowManager.LayoutParams;

public class ThisDevice {
	
	private final static int normalPulse = 500;
	private final static int dot = 200; // Length of a Morse Code "dot" in milliseconds
	private final static int dash = 500; // Length of a Morse Code "dash" in milliseconds
	private final static int short_gap = 200; // Length of Gap Between dots/dashes
	private final static int medium_gap = 500; // Length of Gap Between Letters
	private final static int long_gap = 1000; // Length of Gap Between Words
	
	private final static long[] SOS_PATTERN = { 
				0, // Start immediately
				dot, short_gap, dot, short_gap, dot, // s
				medium_gap, dash, short_gap, dash, short_gap, dash, // o
				medium_gap, dot, short_gap, dot, short_gap, dot, // s
				long_gap 
			};

	private final static long[] NORMAL_PATTERN = { 
				0, // Start immediately
				normalPulse 
			};
	
	public static void vibrateThisDevice(Context applicationContextInput) {
		vibrateThisDevice(applicationContextInput, 0);
	}
	
	public static void vibrateThisDevice(Context applicationContextInput, int numberOfTimesToRepeatInput) {
		Vibrator vibrationAlertNotifier = (Vibrator) applicationContextInput.getSystemService(Context.VIBRATOR_SERVICE);
		if(numberOfTimesToRepeatInput <= 0) {
			numberOfTimesToRepeatInput = -1;			
		}
		vibrationAlertNotifier.vibrate(NORMAL_PATTERN, numberOfTimesToRepeatInput);
	}
	
	public static void vibrateThisDeviceLikeYouMeanIt(Context applicationContextInput) {
		vibrateThisDeviceLikeYouMeanIt(applicationContextInput, 0);
	}
	
	public static void vibrateThisDeviceLikeYouMeanIt(Context applicationContextInput, int numberOfTimesToRepeatInput) {
		Vibrator vibrationAlertNotifier = (Vibrator) applicationContextInput.getSystemService(Context.VIBRATOR_SERVICE);
		if(numberOfTimesToRepeatInput <= 0) {
			numberOfTimesToRepeatInput = -1;			
		}
		vibrationAlertNotifier.vibrate(SOS_PATTERN, numberOfTimesToRepeatInput);
	}
	
	public static void preventScreenCapture(Activity activityContextInput) {
		activityContextInput.getWindow().setFlags(LayoutParams.FLAG_SECURE, LayoutParams.FLAG_SECURE);
	}
	
}
