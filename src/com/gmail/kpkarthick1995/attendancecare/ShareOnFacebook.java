package com.gmail.kpkarthick1995.attendancecare;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class ShareOnFacebook extends Activity{

	private static  String APP_ID; ;
	private static final String[] PERMISSIONS = new String[] {"publish_stream"};

	private static final String TOKEN = "access_token";
        private static final String EXPIRES = "expires_in";
        private static final String KEY = "facebook-credentials";

	private Facebook facebook;
	private String messageToPost;

	public boolean saveCredentials(Facebook facebook) {
        	Editor editor = getApplicationContext().getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
        	editor.putString(TOKEN, facebook.getAccessToken());
        	editor.putLong(EXPIRES, facebook.getAccessExpires());
        	return editor.commit();
    	}

    	public boolean restoreCredentials(Facebook facebook) {
        	SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(KEY, Context.MODE_PRIVATE);
        	facebook.setAccessToken(sharedPreferences.getString(TOKEN, null));
        	facebook.setAccessExpires(sharedPreferences.getLong(EXPIRES, 0));
        	return facebook.isSessionValid();
    	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
APP_ID=getString(R.string.APP_ID);
		facebook = new Facebook(APP_ID);
		restoreCredentials(facebook);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.facebook_dialog);
		
		String facebookMessage = getIntent().getStringExtra("facebookMessage");
		if (facebookMessage == null){
			facebookMessage = "Test wall post";
		}
		messageToPost = facebookMessage;
	}

	public void doNotShare(View button){
		finish();
	}
	public void share(View button){
		if (! facebook.isSessionValid()) {
			loginAndPostToWall();
		}
		else {
			postToWall(messageToPost);
		}
	}

	public void loginAndPostToWall(){
		 facebook.authorize(this, PERMISSIONS, Facebook.FORCE_DIALOG_AUTH, new LoginDialogListener());
	}

	public void postToWall(String message){
	
	
		
		/*Bundle parameters = new Bundle();
		parameters.putString("name","fdsfds");
                parameters.putString("message", message);
                parameters.putString("description", "topic share");
               try {
        	        facebook.request("me");
			String response = facebook.request("me/feed", parameters, "POST");
			Log.d("Tests", "got response: " + response);
			if (response == null || response.equals("") ||
			        response.equals("false")) {
				showToast("Blank response.");
			}*/
		
      /*  try {
        	Bundle parameters = new Bundle();
            parameters.putString("name","Attendance care");
            parameters.putString("description", "topic share");
            Log.d("Tests", "near try ");
	        facebook.request("me");
	String response = facebook.request("me/feed", parameters, "POST");
	Log.d("Tests", "got response: " + response);
	if (response == null || response.equals("") ||
	        response.equals("false")) {
		showToast("Blank response.");
	}
	else {
		showToast("Message posted to your facebook wall!");
	}
	finish();
} catch (Exception e) {
	showToast("Failed to post to wall!");
	e.printStackTrace();
	finish();
}*/FacebookPoster fp = new FacebookPoster();
fp.execute(message, null, null);
	
	
	}
 class FacebookPoster extends AsyncTask {
		protected String doInBackground(String... message) {
		Bundle parameters = new Bundle();
		parameters.putString("message", message[0]);
		parameters.putString("description", "topic share");
		try {
		facebook.request("me");
		String response = facebook.request("me/feed", parameters, "POST");
		Log.d("Tests", "got response: " + response);
		if (response == null || response.equals("")
		|| response.equals("false")) {
		return "Blank response.";
		} else {
		return "Message posted to your facebook wall!";
		}
		} catch (Exception e) {
		Log.d("ShareOnFacebook" , e.getMessage());
		return "Failed to post to wall!";
		}
		}
		protected void onPostExecute(String result) {
	
		showToast(result);
		finish();
		}
		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
		
			try {
				Log.d("filter","came in");
				Bundle parameters = new Bundle();
				parameters.putString("message", "fadfs");
				parameters.putString("description", "topic share");
			facebook.request("me");
			String response = facebook.request("feed", parameters, "POST");
			Log.d("Tests", "got response: " + response);
			if (response == null || response.equals("")
			|| response.equals("false")) {
			return "Blank response.";
			} else {
		
			Log.d("filter","Message posted to your facebook wall!");
			return null;
			}
			} catch (Exception e) {
			Log.d("ShareOnFacebook" , e.getMessage());
		Log.d("filter","exception");
			return null;
			}
			
		}
		
		}
	
	class LoginDialogListener implements DialogListener {
	    public void onComplete(Bundle values) {
	    	saveCredentials(facebook);
	    	if (messageToPost != null){
			postToWall(messageToPost);
		}
	    }
	    public void onFacebookError(FacebookError error) {
	    	showToast("Authentication with Facebook failed!");
	        finish();
	    }
	    public void onError(DialogError error) {
	    	showToast("Authentication with Facebook failed!");
	        finish();
	    }
	    public void onCancel() {
	    	showToast("Authentication with Facebook cancelled!");
	        finish();
	    }
	}

	private void showToast(String message){
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}
}
 