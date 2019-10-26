package com.gmail.kpkarthick1995.attendancecare;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class reset extends Activity implements OnClickListener{
private Button reset,cancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset);
		declaration();
		setListener();
		
	}
	public void setListener(){
	reset.setOnClickListener(this);
	cancel.setOnClickListener(this);
	
	}
	public void declaration(){
	reset = (Button) findViewById(R.id.button2);
	cancel =(Button) findViewById(R.id.button1);
	}
	


	 public void clearApplicationData() {
	        File cache = getCacheDir();
	        File appDir = new File(cache.getParent());
	        if (appDir.exists()) {
	            String[] children = appDir.list();
	            for (String s : children) {
	                if (!s.equals("lib")) {
	                    deleteDir(new File(appDir, s));
	                    Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
	                }
	            }
	        }
	    }

	    public static boolean deleteDir(File dir) {
	        if (dir != null && dir.isDirectory()) {
	            String[] children = dir.list();
	            for (int i = 0; i < children.length; i++) {
	                boolean success = deleteDir(new File(dir, children[i]));
	                if (!success) {
	                    return false;
	                }
	            }
	        }

	        return dir.delete();
	    
	
}

	@Override
	public void onClick(View v) {
	     if(v==reset){
	    		clearApplicationData();
	       	 Intent in = new Intent(reset.this,MainScreen.class);
	    	 startActivity(in);
	     }
	     else if(v==cancel){
	    	 Intent in = new Intent(reset.this,settings.class);
	    	 startActivity(in);
	     }
		
	}
}
