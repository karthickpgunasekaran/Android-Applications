package com.gmail.kpkarthick1995.attendancecare;

import android.app.Activity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InterstitialAds extends Activity{

	private static final String LOG_TAG = "InterstitialSample";


	  private static final String AD_UNIT_ID = "ca-app-pub-5293147320473871/7133292148";


	  private InterstitialAd interstitialAd;


	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.percentcal);

	    // Create an ad.
	    interstitialAd = new InterstitialAd(this);
	    interstitialAd.setAdUnitId(AD_UNIT_ID);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    interstitialAd.loadAd(adRequest);
	    // Set the AdListener.
	    interstitialAd.setAdListener(new AdListener(){
	          public void onAdLoaded(){
	               displayInterstitial();
	          }
	});
	 
	  }
	  public void displayInterstitial() {
		    if (interstitialAd.isLoaded()) {
		      interstitialAd.show();
		    }
		  }
	   /*   @Override
	      public void onAdFailedToLoad(int errorCode) {
	        String message = String.format("onAdFailedToLoad (%s)", getErrorReason(errorCode));
	        Log.d(LOG_TAG, message);
	        Toast.makeText(InterstitialAds.this, message, Toast.LENGTH_SHORT).show();

	     
	      }
	    });

	  }

	  /** Called when the Load Interstitial button is clicked. */
	/*  public void loadInterstitial() {

	    AdRequest adRequest = new AdRequest.Builder()
	        .build();
	    interstitialAd.loadAd(adRequest);
	    if (interstitialAd.isLoaded()) {
		      interstitialAd.show();
		    } else {
		      Log.d(LOG_TAG, "Interstitial ad was not ready to be shown.");
		    }
	  }

	

	  private String getErrorReason(int errorCode) {
	    String errorReason = "";
	    switch(errorCode) {
	      case AdRequest.ERROR_CODE_INTERNAL_ERROR:
	        errorReason = "Internal error";
	        break;
	      case AdRequest.ERROR_CODE_INVALID_REQUEST:
	        errorReason = "Invalid request";
	        break;
	      case AdRequest.ERROR_CODE_NETWORK_ERROR:
	        errorReason = "Network Error";
	        break;
	      case AdRequest.ERROR_CODE_NO_FILL:
	        errorReason = "No fill";
	        break;
	    }
	    return errorReason;
	  }*/
	
}
