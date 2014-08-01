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
package net.sharathkumar.android.utils.ratings;

import net.sharathkumar.android.utils.exceptions.RatingsException;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RateThisApp {
	
	private static String APP_TITLE = null;
    private static String APP_NAME = null;
    
    private static int DAYS_UNTIL_PROMPT = 2; // Minimum number of days the app needs to be installed on the phone.
    private static int LAUNCHES_UNTIL_PROMPT = 2; // Minimum number of times the app needs to be launched.
    private static int TIME_DELAY_UNTIL_PROMPT = 3000; // in milliseconds
    
	public static void setApplicationTitle(String applicationTitleInput) {
		APP_TITLE = applicationTitleInput;
	}

	public static String getApplicationTitle() {
		return APP_TITLE;
	}

	public static void setApplicationName(String applicationNameInput) {
		APP_NAME = applicationNameInput;
	}
	
	public static String getApplicationName() {
		return APP_NAME;
	}

	public static void setDaysUntilPrompt(int daysUntilPromptInput) {
		DAYS_UNTIL_PROMPT = daysUntilPromptInput;
	}
	
	public static int getDaysUntilPrompt() {
		return DAYS_UNTIL_PROMPT;
	}

	public static void setLaunchesUntilPrompt(int launchesUntilPromptInput) {
		LAUNCHES_UNTIL_PROMPT = launchesUntilPromptInput;
	}
	
	public static int getLaunchesUntilPrompt() {
		return LAUNCHES_UNTIL_PROMPT;
	}
	
	public static void setTimeDelayUntilPrompt(int timeDelayUntilPromptInput) {
		TIME_DELAY_UNTIL_PROMPT = timeDelayUntilPromptInput;
	}
	
	public static int getTimeDelayUntilPrompt() {
		return TIME_DELAY_UNTIL_PROMPT;
	}	
	
	public static void performSanityChecks() throws RatingsException {
		if(APP_NAME==null || APP_NAME.length()==0) {
			throw new RatingsException("APP_NAME is not set");
		}
		if(APP_TITLE==null || APP_TITLE.length()==0) {
			throw new RatingsException("APP_TITLE is not set");		
		}
	}
    
	public static void appLaunched(Context applicationContextInput, String applicationTitleInput , String applicationNameInput) throws RatingsException {
		setApplicationTitle(applicationTitleInput);
		setApplicationName(applicationNameInput);
		appLaunched(applicationContextInput);
	}
	
    private static void appLaunched(Context applicationContextInput) throws RatingsException {
    	performSanityChecks();
    	
        SharedPreferences applicationPreferences = applicationContextInput.getSharedPreferences("RateThisAppSettings", 0);
        if (applicationPreferences.getBoolean("dont_show_again", false)) { return ; }
        
        SharedPreferences.Editor editor = applicationPreferences.edit();
        
        // Increment app launch counter
        long appLaunchCounter = applicationPreferences.getLong("app_launch_counter", 0) + 1;
        editor.putLong("app_launch_counter", appLaunchCounter);

        // Get date the app was first launched
        Long firstLaunchDate = applicationPreferences.getLong("first_launch_date", 0);
        if (firstLaunchDate == null || firstLaunchDate == 0 ) {
        	firstLaunchDate = System.currentTimeMillis();
            editor.putLong("date_first_launched", firstLaunchDate);
        }
        
        // Save config changes
        editor.commit();
        
        // Wait at least n days before opening
        if (appLaunchCounter >= LAUNCHES_UNTIL_PROMPT) {
            if (System.currentTimeMillis() >= (firstLaunchDate + (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) ) {
            	showPleaseRateDialog(applicationContextInput, editor);
            }
        }        
    }   
    
    public static void showPleaseRateMeDialog(final Context applicationContextInput) {
    	showPleaseRateDialog(applicationContextInput, null);	
    }
    
    private static void showPleaseRateDialog(final Context applicationContextInput, final SharedPreferences.Editor editor) {
        final Dialog ratingsDialogContainer = new Dialog(applicationContextInput);
        LinearLayout ratingsLayout = new LinearLayout(applicationContextInput);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(30, 20, 30, 0);
        ratingsDialogContainer.setTitle("Rate " + APP_TITLE);
        ratingsLayout.setOrientation(LinearLayout.VERTICAL);
        
        TextView textViewContainer = new TextView(applicationContextInput);
        textViewContainer.setText("If you enjoy using " + APP_TITLE + ", please take a moment to rate it.\n\nThanks a million for your support!!!");
        textViewContainer.setWidth(240);
        textViewContainer.setPadding(4, 0, 4, 10);
        ratingsLayout.addView(textViewContainer, layoutParams);
        
        Button rateMeButton = new Button(applicationContextInput);
        rateMeButton.setText("Rate " + APP_TITLE);
        rateMeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	applicationContextInput.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_NAME)));
            	ratingsDialogContainer.dismiss();
            }
        });

        Button remindMeLaterButton = new Button(applicationContextInput);
        remindMeLaterButton.setText("Remind me later");
        remindMeLaterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	ratingsDialogContainer.dismiss();
            }
        });

        Button noThanksButton = new Button(applicationContextInput);
        noThanksButton.setText("No, thanks");
        noThanksButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (editor != null) {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                ratingsDialogContainer.dismiss();
            }
        });

        ratingsLayout.addView(rateMeButton, layoutParams); 
        ratingsLayout.addView(remindMeLaterButton, layoutParams);
        ratingsLayout.addView(noThanksButton, layoutParams);
        
        ratingsDialogContainer.setContentView(ratingsLayout);        
        
        // Launch the "Please Rate Me" dialog after pre-defined time delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            	ratingsDialogContainer.show();      
            }
        }, TIME_DELAY_UNTIL_PROMPT);
    }
}