package com.mobile.app.myacl.exerciseShow;

/**
 * Created by Alaa on 2/23/2015.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.app.myacl.R;

import java.util.ArrayList;

public class StepsListAdapter extends BaseAdapter{
    String [] result;
    Context context;
    ArrayList<Bitmap> imageId;
    private static LayoutInflater inflater=null;
    public StepsListAdapter(Context context, String[] stepNameList, ArrayList<Bitmap> stepImages) {
        // TODO Auto-generated constructor stub
        result=stepNameList;
        imageId=stepImages;
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
        TextView stepdesc;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.exercise_list, null);
        holder.stepdesc=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.stepdesc.setText(result[position]);
        holder.img.setImageBitmap(imageId.get(position));

        return rowView;
    }

}