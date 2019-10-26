package com.gmail.kpkarthick1995.attendancecare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

public class NumberSetter extends Activity implements OnClickListener,
		OnValueChangeListener {
	private int extraWeek;
	private NumberPicker np;
	private Button set, cancel;
 private int variety;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.number);
		variety=((allData) this.getApplication()).getVariety();
		np = (NumberPicker) findViewById(R.id.numberPick);
		set = (Button) findViewById(R.id.set);
		cancel = (Button) findViewById(R.id.cancel);
		np.setMinValue(1);
		np.setMaxValue(10);
		np.setWrapSelectorWheel(false);
		np.setOnValueChangedListener(this);
		set.setOnClickListener(this);
		cancel.setOnClickListener(this);

	}

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		// TODO Auto-generated method stub
        extraWeek = newVal;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
if(v==set){
	if(variety==4){
	Intent in = new Intent(NumberSetter.this,predict4.class);
	in.putExtra("week",extraWeek);
	 startActivity(in);
	}
	else if(variety==5){
	Intent in = new Intent(NumberSetter.this,predict5.class);
	in.putExtra("week",extraWeek);
	 startActivity(in);
	}
	else if(variety==6){
	Intent in = new Intent(NumberSetter.this,predict6.class);
	in.putExtra("week",extraWeek); 
	startActivity(in);
	}
	else if(variety==7){
		Intent in = new Intent(NumberSetter.this,predict7.class);
		in.putExtra("week",extraWeek); 
		startActivity(in);
		}
	else if(variety==8){
		Intent in = new Intent(NumberSetter.this,predict8.class);
		in.putExtra("week",extraWeek);
		startActivity(in);
		}
	else if(variety==9){
		Intent in = new Intent(NumberSetter.this,predict9.class);
		in.putExtra("week",extraWeek);
		 startActivity(in);
		}
	else if(variety==10){
		Intent in = new Intent(NumberSetter.this,predict10.class);
		in.putExtra("week",extraWeek);
		 startActivity(in);
		}
}
else if(v==cancel){
	if(variety==4){
		Intent in = new Intent(NumberSetter.this,predict4.class);		
		 startActivity(in);
		}
		else if(variety==5){
		Intent in = new Intent(NumberSetter.this,predict5.class);		
		 startActivity(in);
		}
		else if(variety==6){
		Intent in = new Intent(NumberSetter.this,predict6.class);
		 startActivity(in);
		}
		else if(variety==7){
			Intent in = new Intent(NumberSetter.this,predict7.class);
			 startActivity(in);
			}
		else if(variety==8){
			Intent in = new Intent(NumberSetter.this,predict8.class);
			 startActivity(in);
			}
		else if(variety==9){
			Intent in = new Intent(NumberSetter.this,predict9.class);
			 startActivity(in);
			}
		else if(variety==10){
			Intent in = new Intent(NumberSetter.this,predict10.class);
			 startActivity(in);
			}
}
	}
}
