package com.mobile.app.myacl.ShowGoals;

/**
 * Created by Alaa on 2/23/2015.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.app.myacl.R;
import com.mobile.app.myacl.exerciseShow.ExerciseShow;

import java.util.ArrayList;

public class GoalsListAdapter extends BaseAdapter{
    String [] result;
    private static LayoutInflater inflater=null;
    public GoalsListAdapter(Context context, String[] goalsNameList) {
        // TODO Auto-generated constructor stub
        result=goalsNameList;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView goalitem;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.goals_list, null);
        holder.goalitem=(TextView) rowView.findViewById(R.id.goalitem);
        holder.goalitem.setText(result[position]);

        return rowView;
    }

}