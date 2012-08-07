package com.cloudsourceit.milenioandroid;

import java.util.ArrayList;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MiExpandableListAdapter  extends BaseExpandableListAdapter {
 
	//Inicializo mis variables	
			private ArrayList<ArrayList<String>> groups; //La lista que tiene los grupos
	       	private Context context; //El context para modificar la activity
	    	 	    
	     MiExpandableListAdapter(Context context, ArrayList<ArrayList<String>> groups) {
	    		this.context = context;
	            this.groups = groups;
	    	}
	     
	        public boolean areAllItemsEnabled()
	        {
	            return true;
	        }

	        //Obtengo el ArrayList con los valores del hijo, si fuera un solo valor podria pedir un String en lugar de ArrayList
	        //@Override
	        public ArrayList<ArrayList<String>> getChild(int groupPosition, int childPosition) {
	        	ArrayList<ArrayList<String>> vector = new ArrayList<ArrayList<String>>();       	
	        	vector.add(new ArrayList<String>());
	        	vector.get(0).add(groups.get(groupPosition).get(4));
	        	vector.get(0).add(groups.get(groupPosition).get(5));
	            return vector;           
	        }
	 
	        //Obtengo la posicion del hijo
	        //@Override
	        public long getChildId(int groupPosition, int childPosition) {
	            return childPosition;
	        }
	 
	        //Meto el xml del hijo
	        //@Override
	        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,View convertView, ViewGroup parent) {
	        	
	        	//Obtengo los valores
	        	String clabe = (String) ((ArrayList<ArrayList<String>>)getChild(groupPosition, childPosition)).get(0).get(0);
	        	String saldobloq = (String) ((ArrayList<ArrayList<String>>)getChild(groupPosition, childPosition)).get(0).get(1);
	        	
	        	//Si no hay un view ya hecho lo creo (para eso crea un espacio y lo llena con el xml del hijo)
	            if (convertView == null) {
	                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	                convertView = infalInflater.inflate(R.layout.child_row, null);
	            }
	 
	            //Mando a llamar los elementos del xml y los lleno con los valores
	            return convertView;
	        }
	 
	        //@Override
	        public int getChildrenCount(int groupPosition) {
	            return 1;
	        }
	 
	        //Ahora obtengo el ArrayList con los valores del grupo (si fuera un solo valor podria pedir que fuera String)
	        //@Override
	        public ArrayList<ArrayList<String>> getGroup(int groupPosition) {
	        	ArrayList<ArrayList<String>> vector = new ArrayList<ArrayList<String>>();
	        	vector.add(new ArrayList<String>());
	        	vector.get(0).add(groups.get(groupPosition).get(0));
	        	vector.get(0).add(groups.get(groupPosition).get(1));
	        	vector.get(0).add(groups.get(groupPosition).get(2));
	        	vector.get(0).add(groups.get(groupPosition).get(3));
	            return vector;
	        }
	 
	        //Cuento cuantos grupos tengo
	        //@Override
	        public int getGroupCount() {
	            return groups.size();
	        }
	 
	        //Devuelvo la posicion del grupo
	        //@Override
	        public long getGroupId(int groupPosition) {
	            return groupPosition;
	        }
	 
	        //Aqui creo la vista del grupo
	        //@Override
	        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
	 
	        	
	 
	        	//Si no existe un view lo creo y le doy el estilo del xml para grupos
	        	if (convertView == null) {
	                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	                convertView = infalInflater.inflate(R.layout.group_row, null);
	            }
	 
	            //Vevuelvo el view con los valores
	            return convertView;
	        }
	 
	        //@Override
	        public boolean hasStableIds() {
	            return true;
	        }
	        
	        //@Override
	        public boolean isChildSelectable(int arg0, int arg1) {
	            return true;
	        }
	 
	    }
