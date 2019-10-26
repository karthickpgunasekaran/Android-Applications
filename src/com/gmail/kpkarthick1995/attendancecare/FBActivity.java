package com.gmail.kpkarthick1995.attendancecare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
 
public class FBActivity extends Activity {
 
	private LoginButton loginBtn;
	private Button postImageBtn;
	private Button updateStatusBtn;
 
	private TextView userName;
	StringBuilder strBu;
	private UiLifecycleHelper uiHelper;
 
	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");
 
	private static String message = "Sample status posted from android app";
 private Facebook face;
 private String name;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 
	
 
		setContentView(R.layout.activity_facebook);
		uiHelper = new UiLifecycleHelper(this, statusCallback);
		uiHelper.onCreate(savedInstanceState);
        face =new Facebook(getString(R.string.APP_ID));
		userName = (TextView) findViewById(R.id.user_name);
		loginBtn = (LoginButton) findViewById(R.id.fb_login_button);
		loginBtn.setUserInfoChangedCallback(new UserInfoChangedCallback() {
			@Override
			public void onUserInfoFetched(GraphUser user) {
				if (user != null) {
					userName.setText("Hello, " + user.getName());
					name=user.getName();
				} else {
					userName.setText("You are not logged");
				}
			}
		});
 
		postImageBtn = (Button) findViewById(R.id.post_image);
		postImageBtn.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View view) {
				postImage();
			}
		});
 
		updateStatusBtn = (Button) findViewById(R.id.update_status);
		updateStatusBtn.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				 postStatusMessage();
			}
		});
 
		buttonsEnabled(false);
	}
 
	private Session.StatusCallback statusCallback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			if (state.isOpened()) {
				buttonsEnabled(true);
				Log.d("FacebookSampleActivity", "Facebook session opened");
			} else if (state.isClosed()) {
				buttonsEnabled(false);
				Log.d("FacebookSampleActivity", "Facebook session closed");
			}
		}
	};
 
	public void buttonsEnabled(boolean isEnabled) {
		postImageBtn.setEnabled(isEnabled);
		updateStatusBtn.setEnabled(isEnabled);
	}
	public void onCompleted(Response response) {
		if (response.getError() == null)
			Toast.makeText(FBActivity.this,
					"Status updated successfully",
					Toast.LENGTH_LONG).show();

	}

	public void postImage() {
		if (checkPermissions()) {
			
			if (FacebookDialog.canPresentShareDialog(getApplicationContext(), 
		            FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
				goSetBuild();
				String go=strBu.toString();
			    FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
		        .setLink("http://play.google.com/store/apps/details?id=com.gmail.kpkarthick1995.attendancecare") 
		        .setDescription("Waiting for an app which could handle your bunks easily??? Here it is .Customized easy to use interface with ease of going back  changing and  predicting attendence!!Install and Start monitering your attendence now.") 
		        .setName("Attendance Care")
		        .setCaption("Attendance tracking app for android")
		        .setApplicationName("Attendance Care")
		        .setPicture("http://1.bp.blogspot.com/-TCLnrlelaHQ/U-3m3i4HdkI/AAAAAAAAAAg/ejs3oWmzj9w/s1600/fbpro.png")
		        .build();
				/*
				FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
		        .setLink("http://play.google.com/store/apps/details?id=com.gmail.kpkarthick1995.attendancecare") 
		        .setDescription("karthick told me to inform his friends that he has published his app on play store.He wants everyone to experience his first android app.He wants everyone to rate his app so that his app name can be found easily on google play. ") 
		        .setName("Attendance Care")
		        .setCaption("A Bunker friendly app")
		        .setApplicationName("Attendance Care")
		        .setPicture("http://1.bp.blogspot.com/-TCLnrlelaHQ/U-3m3i4HdkI/AAAAAAAAAAg/ejs3oWmzj9w/s1600/fbpro.png")
		        .build();				
				
				/*
				 * 
				 * 
				 * 
				 */
		uiHelper.trackPendingDialogCall(shareDialog.present());

		} else {
			goSetBuild();
			String go=strBu.toString();
			    Bundle params = new Bundle();
			    params.putString("name", "Attendence Care");
			    params.putString("caption", "A Bunker friendly app");
			    params.putString("description", "Waiting for an app which could handle your bunks easily??? Here it is .Customized easy to use interface with ease of going back  changing and  predicting attendence!!Install and Start monitering your attendence now.");
			    params.putString("link", "http://play.google.com/store/apps/details?id=com.gmail.kpkarthick1995.attendancecare");
			    params.putString("applicationname", "Attendance Care");
			    params.putString("picture","http://1.bp.blogspot.com/-TCLnrlelaHQ/U-3m3i4HdkI/AAAAAAAAAAg/ejs3oWmzj9w/s1600/fbpro.png");
			    

			    WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(FBActivity.this,
			            Session.getActiveSession(),
			            params))
			        .setOnCompleteListener(new OnCompleteListener() {


						@Override
						public void onComplete(Bundle values, FacebookException error) {
							// TODO Auto-generated method stub
							 if (error == null) {
				                    // When the story is posted, echo the success
				                    // and the post Id.
				                    final String postId = values.getString("post_id");
				                    if (postId != null) {
				                        Toast.makeText(FBActivity.this,
				                            "Posted story, id: "+postId,
				                            Toast.LENGTH_SHORT).show();
				                    } else {
				                        // User clicked the Cancel button
				                        Toast.makeText(FBActivity.this.getApplicationContext(), 
				                            "Publish cancelled", 
				                            Toast.LENGTH_SHORT).show();
				                    }
				                } else if (error instanceof FacebookOperationCanceledException) {
				                    // User clicked the "x" button
				                    Toast.makeText(FBActivity.this.getApplicationContext(), 
				                        "Publish cancelled", 
				                        Toast.LENGTH_SHORT).show();
				                } else {
				                    // Generic, ex: network error
				                    Toast.makeText(FBActivity.this.getApplicationContext(), 
				                        "Error posting story", 
				                        Toast.LENGTH_SHORT).show();
				                }
						}

				

			        })
			        .build();
			    feedDialog.show();

		}
				}
	}
	public void goSetBuild(){
		try {
			InputStreamReader in = new InputStreamReader(openFileInput("forFB.txt"));
			BufferedReader br =new BufferedReader(in);
			strBu = new StringBuilder();
			String str1;
			
			while((str1=br.readLine())!=null)
			{
				Log.d("gh","str1:"+str1);
				String[] s = new String[4];
				s=str1.toString().split(" ");
				Log.d("gh","string build:"+s[0]+" "+s[1]);
				if(s[0].equals("Overall")){
					strBu
					.append(name+"'s"+" "+s[0]+" "+"attendence percentage is "+s[1]+"."+'\n')
					.append("Total no of lectures:"+s[2]+"."+'\n')
					.append("no of lectures attended:"+s[3]+"."+'\n')
					.append("no of lectures bunked:"+(Integer.parseInt(s[2])-Integer.parseInt(s[3]))+".");
				}
				else{
					strBu.append(s[0]+" "+s[1]+'\n');
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void postStatusMessage() {
		if (checkPermissions()) {
			
	if (FacebookDialog.canPresentShareDialog(getApplicationContext(), 
            FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
		goSetBuild();
		String go=strBu.toString();
		FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
        .setLink("http://play.google.com/store/apps/details?id=com.gmail.kpkarthick1995.attendancecare") 
        .setDescription( strBu.toString()) 
        .setName("Attendance Care")
        .setCaption(name+"'s"+" "+"Attendence record")
        .setApplicationName("Attendance Care")
        .setPicture("http://1.bp.blogspot.com/-TCLnrlelaHQ/U-3m3i4HdkI/AAAAAAAAAAg/ejs3oWmzj9w/s1600/fbpro.png")
        .build();
uiHelper.trackPendingDialogCall(shareDialog.present());

} else {
	goSetBuild();
	String go=strBu.toString();
	    Bundle params = new Bundle();
	    params.putString("name", "Attendance Care");
	    params.putString("caption", "Attendence Record");
	    params.putString("description", strBu.toString());
	    params.putString("link", "http://play.google.com/store/apps/details?id=com.gmail.kpkarthick1995.attendancecare");
	    params.putString("applicationname", "Attendance Care");
	    params.putString("picture","http://1.bp.blogspot.com/-TCLnrlelaHQ/U-3m3i4HdkI/AAAAAAAAAAg/ejs3oWmzj9w/s1600/fbpro.png");

	    WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(FBActivity.this,
	            Session.getActiveSession(),
	            params))
	        .setOnCompleteListener(new OnCompleteListener() {


				@Override
				public void onComplete(Bundle values, FacebookException error) {
					// TODO Auto-generated method stub
					 if (error == null) {
		                    // When the story is posted, echo the success
		                    // and the post Id.
		                    final String postId = values.getString("post_id");
		                    if (postId != null) {
		                        //Toast.makeText(FBActivity.this,
		                           // "Posted story, id: "+postId,
		                           // Toast.LENGTH_SHORT).show();
		                    } else {
		                        // User clicked the Cancel button
		                        Toast.makeText(FBActivity.this.getApplicationContext(), 
		                            "Publish cancelled", 
		                            Toast.LENGTH_SHORT).show();
		                    }
		                } else if (error instanceof FacebookOperationCanceledException) {
		                    // User clicked the "x" button
		                    Toast.makeText(FBActivity.this.getApplicationContext(), 
		                        "Publish cancelled", 
		                        Toast.LENGTH_SHORT).show();
		                } else {
		                    // Generic, ex: network error
		                    Toast.makeText(FBActivity.this.getApplicationContext(), 
		                        "Error posting story", 
		                        Toast.LENGTH_SHORT).show();
		                }
				}

		

	        })
	        .build();
	    feedDialog.show();

}
		}
	}

 
	public boolean checkPermissions() {
		Session s = Session.getActiveSession();
		if (s != null) {
			return s.getPermissions().contains("publish_actions");
		} else
			return false;
	}
 
	public void requestPermissions() {
		Session s = Session.getActiveSession();
		if (s != null)
			s.requestNewPublishPermissions(new Session.NewPermissionsRequest(
					this, PERMISSIONS));
	}
 
	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
		buttonsEnabled(Session.getActiveSession().isOpened());
	}
 
	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}
 
	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}
 
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}
 
	@Override
	public void onSaveInstanceState(Bundle savedState) {
		super.onSaveInstanceState(savedState);
		uiHelper.onSaveInstanceState(savedState);
	}
 
}