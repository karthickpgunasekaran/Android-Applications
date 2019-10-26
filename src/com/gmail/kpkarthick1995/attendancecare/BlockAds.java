package com.gmail.kpkarthick1995.attendancecare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class BlockAds extends Activity {
private AdView adView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blockads);
		adView = (AdView) this.findViewById(R.id.adView);

	}
	public void setAd(){
		if(checkAd()){

		  AdRequest adRequest = new AdRequest.Builder().build();
		    adView.loadAd(adRequest);
		  
		    adView.setAdListener(new AdListener() {
		    	 
		    	  @Override
		    	  public void onAdOpened() {
		    		  delAd();
		    			    		 
		    		  writeAd();
		    		  setVisNul();
		    	  }
		    	});
		  
		}
		else{
		
	setVisNul();	
		}
	}
	public void setVisNul(){
		adView.setVisibility(View.GONE);	
	}
	public boolean checkAd(){
		 Calendar cal2 = Calendar.getInstance();
int retint=1;
		 Log.d("filter","cal2 day of month:"+cal2.get(Calendar.DAY_OF_MONTH));
		 boolean ret=true;
		InputStream in;
		try {
			in = openFileInput("Ad.txt");
			if (in != null) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
			String str;
			String[] strNew = new String[2];
			while ((str = br.readLine()) != null) {
				strNew = str.toString().split(" ");
				Log.d("filter","str new:"+strNew[0]+strNew[1]);
				boolean s =Integer.parseInt(strNew[0])==cal2.get(Calendar.DAY_OF_MONTH);
				Log.d("filter","day check ad:"+s);
				if(Integer.parseInt(strNew[0])==cal2.get(Calendar.DAY_OF_MONTH)){
				 retint =0;
				}
				
			}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	Log.d("filter","I returned:"+retint);
	if(retint==1){
	 return true;
	}
	else{
		return false;
		
	}
	
}
	public void delAd(){
		File dir = getFilesDir();
		File old = new File(dir, "lastAd.txt");
		boolean deleted = old.delete();
		Log.d("filter","delete ad file:"+deleted);
	}
	public void writeAd(){
		 Calendar cal1 = Calendar.getInstance();
			try {
				OutputStreamWriter os = new OutputStreamWriter(openFileOutput("lastAd.txt", 0));
				os.write(cal1.get(Calendar.DAY_OF_MONTH)+" "+cal1.get(Calendar.YEAR));
				Log.d("filter","wrote ad details:"+cal1.get(Calendar.DAY_OF_MONTH)+cal1.get(Calendar.YEAR));
				os.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 
	}
}
