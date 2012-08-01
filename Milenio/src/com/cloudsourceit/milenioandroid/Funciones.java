package com.cloudsourceit.milenioandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
import android.os.StrictMode;
import android.util.Log;



@TargetApi(9)
public class  Funciones {
	
	StringBuilder datos = new StringBuilder();
	Funciones (){}
	
	public String getValores(String link){
				 
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(link);
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
		
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line + "\n");
				}
				content.close();
			} else {
				Log.e(ParseException.class.toString(), "Error");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	 public Drawable LoadImage(String url)
	    {
	         try
	         {
	             InputStream is = (InputStream) new URL(url).getContent();
	             Drawable d = Drawable.createFromStream(is, "image");
	             return d;
	         }catch (Exception e) {
	             System.out.println("Exc="+e);
	             return null;
	         }
	     }
} 

