package com.mobile.app.myacl.exerciseShow;

/**
 * Created by Alaa on 2/23/2015.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.app.myacl.ProtocolManager.ExerciseManager.Step;
import com.mobile.app.myacl.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StepsListAdapter extends BaseAdapter{

    List<Step> steps;
    Context context;
    private static LayoutInflater inflater=null;

    public StepsListAdapter(Context context, List<Step> steps) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.steps=steps;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return steps.size();
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
        Step step = steps.get(position);
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.exercise_list, null);
        holder.stepdesc=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        //holder.stepdesc.setText(steps.get(position).getstepnum());
        if (step.hasPic())
            new RetrieveImageTask(holder.img)
                    .execute(step.getpicpath());

        return rowView;
    }

    private class RetrieveImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public RetrieveImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... paths) {
            Bitmap bitmap = null;
            try {
                InputStream in = context.getAssets().open(paths[0]);
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                bmImage.setVisibility(View.VISIBLE);
                bmImage.setImageBitmap(result);
            }
        }
    }
}
