package com.cloudsourceit.banregio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


import android.app.Activity;

public class Function extends Activity {
    Function() {
	}
public String getplist(String link){
		
	BufferedReader in = null;
    HttpClient client = new DefaultHttpClient();
    HttpGet get = new HttpGet(link);
    String page =""; 
    String res="";
    HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

   try {
	  
	            HttpResponse response=client.execute(get);
	            in = new BufferedReader
	            (new InputStreamReader(response.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String line = "";
	            while ((line = in.readLine()) != null) {
	              
	            	sb.append(line);
	            }
	            in.close();
	            page = sb.toString();   
	            
	            XmlPullParserFactory factory;
				
			    factory = XmlPullParserFactory.newInstance();
				
	            factory.setNamespaceAware(true);
	            XmlPullParser xpp = factory.newPullParser();
	            
	            xpp.setInput( new StringReader(page) );
                

	    		final String KEY = "key", STRING = "string";

	    			xpp.next();
	    			int eventType = xpp.getEventType();
	    			String lastTag = null;
	    			String lastKey = null;

	    			while (eventType != XmlPullParser.END_DOCUMENT) {

	    				if (eventType == XmlPullParser.START_TAG) {
	    					lastTag = xpp.getName();
	    				} 
	    				else if (eventType == XmlPullParser.TEXT) {
	    			
	    					if (KEY.equalsIgnoreCase(lastTag)) {
	    					
	    						lastKey = xpp.getText();
	    					}
	    					else if (STRING.equalsIgnoreCase(lastTag)) {
		    						if (!map.containsKey(lastKey)) {
	    							map.put(lastKey, new ArrayList<String>());
	    						}
                                	res=res+xpp.getText();
    	    						map.get(lastKey).add(xpp.getText());	
                              						
	    					}
	    				}

	    				eventType = xpp.next();
	    			}
	    			
	    		}  catch (ClientProtocolException e) {
	    				e.printStackTrace();
	    		} catch (IOException e) {
	    			    e.printStackTrace();
	    	  } catch (XmlPullParserException e) {
	    				e.printStackTrace();
	    		}
	    		return res;
}
}