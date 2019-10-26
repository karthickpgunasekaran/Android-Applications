package com.gmail.kpkarthick1995.attendancecare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class GetData extends Activity implements OnClickListener {

	private EditText getNoOfPeriods;
	private CheckBox satCheck;
	private Button nextNoOfPeriods;
	private int withSat;
	private int valueOfPeriods;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.no_of_periods);
		
		getNoOfPeriods = (EditText) findViewById(R.id.getNoOfPeriods);
		 nextNoOfPeriods = (Button) findViewById(R.id.nextNoOfPeriodsButton);
	     satCheck =(CheckBox) findViewById(R.id.satCheck);
		 nextNoOfPeriods.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		if (satCheck.isChecked() == true) {

			withSat = 6;

		} else if (satCheck.isChecked() == false) {
			withSat = 5;
		}

		if (getNoOfPeriods.length() == 0) {
			Toast toast = Toast.makeText(this,
					getString(R.string.error_name_letters), Toast.LENGTH_SHORT);
			toast.show();

		}
		if (getNoOfPeriods.length() > 0) {

			valueOfPeriods = Integer.parseInt(getNoOfPeriods.getText()
					.toString());

			if (valueOfPeriods >= 11 || valueOfPeriods < 4) {
				Toast toast = Toast.makeText(this,
						getString(R.string.error_name_length),
						Toast.LENGTH_SHORT);
				toast.show();
				onCreate(null);
			}

			if (valueOfPeriods == 4) {

				Intent intent = new Intent(GetData.this, fourClass.class);				;
				intent.putExtra("withSat", withSat);
				startActivity(intent);
			}
			
			  else if(valueOfPeriods==5){ Intent intent =new
			  Intent(GetData.this,Five.class);;
			 intent.putExtra("withSat",withSat); startActivity(intent); } else
			  if(valueOfPeriods==6){ Intent intent =new
			  Intent(GetData.this,Six.class);;
			  intent.putExtra("withSat",withSat); startActivity(intent); } else
			  if(valueOfPeriods==7){ Intent intent =new
			  Intent(GetData.this,Seven.class);;
			  Log.d("filter","getdata maxdays:"+withSat);
			  intent.putExtra("withSat",withSat); startActivity(intent); } else
			  if(valueOfPeriods==8){ Intent intent =new
			  Intent(GetData.this,Eight.class);;
			  intent.putExtra("withSat",withSat); startActivity(intent); } else
			 if(valueOfPeriods==9){ Intent intent =new
			  Intent(GetData.this,Nine.class);;
			  intent.putExtra("withSat",withSat); startActivity(intent); } else
			  if(valueOfPeriods==10){ Intent intent =new
			  Intent(GetData.this,Ten.class);
			  intent.putExtra("withSat",withSat); startActivity(intent); }
			 

		}
	}

}
