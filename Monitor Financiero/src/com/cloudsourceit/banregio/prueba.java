package com.cloudsourceit.banregio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class prueba extends Activity { 
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main2);
	        
	        Button p = (Button)findViewById(R.id.prueba);
	       
	        p.setOnClickListener(new View.OnClickListener() {
	          	public void onClick(View v) {
	          		final Intent i = new Intent(prueba.this, Monitor.class);
	          		startActivity(i);
	          	}});
	   }
}