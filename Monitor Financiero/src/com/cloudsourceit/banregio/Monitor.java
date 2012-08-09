package com.cloudsourceit.banregio;

import com.cloudsourceit.banregio.Function;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Monitor extends Activity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitorfinaciero);
        
    	TextView cdolar = (TextView)findViewById(R.id.cd);
    	TextView vdolar = (TextView)findViewById(R.id.vd);
    	TextView ceuro = (TextView)findViewById(R.id.ce);
    	TextView veuro = (TextView)findViewById(R.id.ve);
    	TextView udis = (TextView)findViewById(R.id.udis);
    	TextView cetes = (TextView)findViewById(R.id.cetes);
    	TextView tiie = (TextView)findViewById(R.id.tiie);
    	TextView oro = (TextView)findViewById(R.id.oro);
    	TextView plata = (TextView)findViewById(R.id.plata);
    	TextView centenario = (TextView)findViewById(R.id.centenario);
    	TextView fecha = (TextView)findViewById(R.id.actualizacion);
    	
    	ImageView actualizar = (ImageView)findViewById(R.id.actualizar);
    	ImageView atras = (ImageView)findViewById(R.id.atras);
    	
    	Function fun = new Function();
    	String link = "http://app.banregio.com/MonitorFinanciero.plist";
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
		try{
			
			
			 StringTokenizer st = new StringTokenizer(fun.getplist(link));
		
			 cetes.setText(st.nextToken()+"% ");
			 tiie.setText(st.nextToken()+"% ");
			 
			 cdolar.setText("$"+st.nextToken()+" MXN");
			 vdolar.setText("$"+st.nextToken()+" MXN ");
			 ceuro.setText("$"+st.nextToken()+" MXN");
			 veuro.setText("$"+st.nextToken()+" MXN ");
			 
			 udis.setText("$"+st.nextToken()+ " MXN ");
		
			 fecha.setText(fecha.getText()+" "+st.nextToken()+" "+st.nextToken()+" hrs");
			
			 /*NOTA: en el ws faltan los valores de los metales en el ws
			 oro.setText("$"+st.nextToken()+"MXN ");
			 plata.setText("$"+st.nextToken()+"MXN ");
			 centenario.setText("$"+st.nextToken()+"MXN ");
		   */
			 
			 actualizar.setOnClickListener(new View.OnClickListener() {
		          	public void onClick(View v) {
		          		final Intent i = new Intent(Monitor.this, Monitor.class);
		          		startActivity(i);
		          	}});
			 
			 atras.setOnClickListener(new View.OnClickListener() {
		          	public void onClick(View v) {
		          		finish();
		          	}});
			 
			 
		}
		catch (Exception e) {
		    Log.e("Error", e.toString());
		   }
    	 }
}


