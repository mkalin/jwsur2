package com.marakana;

import android.app.Activity;
import android.os.Bundle;

public class HelloWorld extends Activity {  <1>
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  <2>
		setContentView(R.layout.main);
	}
}
