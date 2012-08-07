package com.cloudsourceit.milenioandroid;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.StringTokenizer;

import com.cloudsourceit.milenioandroid.Funciones;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

@TargetApi(9)
public class MilenioMain extends Activity {

 public static TextView grados;
 public static TextView minmax;
 public	static ImageView imgclima;

 	static ArrayList<String> edicionesl;
 	static ArrayList<ArrayList<String>> seccionesl;
 	private ArrayList<ArrayList<String>> grupos;
	 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milenio_main);
        
        final Intent i = new Intent(MilenioMain.this, MilenioMain.class);
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        
        edicionesl = new ArrayList<String>();
        seccionesl = new ArrayList<ArrayList<String>>();
        
     	final ImageView home = (ImageView)findViewById(R.id.home);
     	final ImageView impreso = (ImageView)findViewById(R.id.impreso);
     	final ImageView tv = (ImageView)findViewById(R.id.tv);
     	final ImageView radio = (ImageView)findViewById(R.id.radio);
     	final ImageView buscar = (ImageView)findViewById(R.id.buscar);
     	
     	final TextView p1 = (TextView)findViewById(R.id.p1);
     	     	
    	final Spinner secciones = (Spinner)findViewById(R.id.secciones);
     	final Spinner firmas = (Spinner)findViewById(R.id.firmas);
     	final Spinner ediciones = (Spinner)findViewById(R.id.ediciones);
     	final Spinner otros = (Spinner)findViewById(R.id.otros);
     	final Spinner topicos = (Spinner)findViewById(R.id.topicos);
     	
     	ExpandableListView l = (ExpandableListView) findViewById(R.id.esecciones);
		MiExpandableListAdapter adaptador = new MiExpandableListAdapter(this, seccionesl);
	    l.setAdapter(adaptador);

     	 	/*Calendar c= Calendar.getInstance();
     	p1.setText(String.valueOf(c.getTimeInMillis()));
     	*/
     	grados = (TextView)findViewById(R.id.clima);
     	minmax = (TextView)findViewById(R.id.minmax);
     	imgclima = (ImageView)findViewById(R.id.imgclima);
     	
     	menu("000900","nacional");
     	
     	
     	//edicciones
        ArrayAdapter<String> aediciones = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,edicionesl);
        aediciones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ediciones.setAdapter(aediciones);
        
        //secciones
        ArrayAdapter<ArrayList<String>> asecciones = new ArrayAdapter<ArrayList<String>>(this,android.R.layout.simple_spinner_item,seccionesl);
        asecciones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secciones.setAdapter(asecciones);
      
        //otros
        ArrayAdapter<CharSequence> aotros = ArrayAdapter.createFromResource(this, R.array.otros, android.R.layout.simple_spinner_item);
        aotros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        otros.setAdapter(aotros);

      home.setOnClickListener(new View.OnClickListener() {
       		public void onClick(View v) {
       			startActivity(i);
       			home.setImageResource(R.drawable.homeon);
			}});
        
        tv.setOnClickListener(new View.OnClickListener() {
         	public void onClick(View v) {
         		startActivity(i);
         		tv.setImageResource(R.drawable.televisionon);
			}});
        
        impreso.setOnClickListener(new View.OnClickListener() {
          	public void onClick(View v) {
          		startActivity(i);
          		impreso.setImageResource(R.drawable.impresoon);
			}});
        
        radio.setOnClickListener(new View.OnClickListener() {
           	public void onClick(View v) {
           		startActivity(i);
           		radio.setImageResource(R.drawable.radioon);
			}});
        
        buscar.setOnClickListener(new View.OnClickListener() {
           	public void onClick(View v) {
           		startActivity(i);
           		buscar.setImageResource(R.drawable.searchon);
			}});
    }
	
	//funciones para llenar las opciones del menu
	
public void  menu(String idobj, String ct){
 	String id="0"; 
 	String idc="0"; 
 	String sec="";
 	
 	int x=0;
 	Funciones func = new Funciones();
 	
	String obtenersec = func.getValores("http://www.milenio.com/xmlmovil/secciones_ediciones.js");
	 try {
		   
		   JSONObject jsonObject =  new JSONObject(obtenersec);
		   JSONArray jsonsec = jsonObject.getJSONArray("rows");

		for (int i = 0; i < jsonsec.length(); i++) {
		      JSONObject jsid = jsonsec.getJSONObject(i);
		      
		      //Obtenemos la parte de la id que representan la edicion
		   
		      StringTokenizer st = new StringTokenizer(jsid.getString("key"),":");
		      
		      for (int c=0;c<2;c++){
		    	 
		    	  id=st.nextToken();
		      }

	        if (id.equals(idobj))
		      {
	      	  //se obtienen las secciones
	  
	      	  StringTokenizer se = new StringTokenizer(jsid.getString("value"),"\"");
			  
			      for (int c=0;c<2;c++){
			    	 sec=se.nextToken();
			      }
			      final String lala = sec;
			      if(x==0)
			      {
			    	 x=1; 
			    	 edicionesl.add(sec);
			      }
			      else
			      { 
		    	     seccionesl.add(new ArrayList<String>(){{add(lala);}});
			      }
		      }
		      else if(!id.equals(idc))
		      {
		     
		    	  //obtenemos las ediciones de la cadena
		    	  StringTokenizer es = new StringTokenizer(jsid.getString("value"),"\"");
			      String estados="";
			      for (int c=0;c<2;c++){
			           estados=es.nextToken();
			      }

		    	  edicionesl.add(estados);
		    	  idc=id;
		      }

			}
	} catch (Exception e) {
		Log.e(e.toString(), "Error");
	}
	///////////////////////
	 
	 ///////Obtenemos el clima////////////////
	 String obtenerclima = func.getValores("http://www.milenio.com/xmlmovil/json_weather.php?city="+ct+"");
	 try {
		   
	 	JSONObject jsonclima =  new JSONObject(obtenerclima);
		grados.setText(jsonclima.get("temp") +"°");
		minmax.setText("DF".toUpperCase()+"\n MAX."+jsonclima.get("max") +"° - MIN."+jsonclima.get("min")+"°");
	    Drawable drawclima = func.LoadImage(jsonclima.getString("icon_retina"));
		imgclima.setImageDrawable(drawclima);
		
			}
	catch (Exception e) {
		Log.e(e.toString(), "Error");
	}
	/////////////////////////////
}
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_milenio_main, menu);
        return true;
    }
  
}
