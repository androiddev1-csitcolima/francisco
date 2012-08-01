package com.cloudsourceit.milenioandroid;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class MiExpandableListAdapter  extends BaseExpandableListAdapter {
    // Sample data set. children[i] contains the children (String[]) for
    // groups[i].
    private String[] grupos = { "Parent1", "Parent2",
        "Parent3" };
    private String[][] children = { { "Child1" },{ "Child2" }, { "Child3" },{ "Child4" }, { "Child5" } };
 
    public Object getChild(int groupPosition, int childPosition) {
        return children[groupPosition][childPosition];
    }
 
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    public int getChildrenCount(int groupPosition) {
        int i = 0;
        try {
        i = children[groupPosition].length;
 
        } catch (Exception e) {
        }
 
        return i;
    }

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
}