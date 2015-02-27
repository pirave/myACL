package com.mobile.app.myacl;

/**
 * Created by Alaa on 2/23/2015.
 */
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.app.myacl.ProtocolManager.Category;
import com.mobile.app.myacl.R;

import java.util.List;

public class TimelineListAdapter extends BaseAdapter{
    List<Category> categories;
    Context mcontext;
    private static LayoutInflater inflater=null;
    public TimelineListAdapter(Context context, List<Category> categories) {
        // TODO Auto-generated constructor stub
        this.categories =categories;
        mcontext=context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return categories.size();
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
        boolean completed=false;
        rowView = inflater.inflate(R.layout.timeline_item, null);
        holder.goalitem=(TextView) rowView.findViewById(R.id.goalitem1);
        TextView goal2 =(TextView) rowView.findViewById(R.id.goalitem2);
        TextView goal3 =(TextView) rowView.findViewById(R.id.goalitem3);
        TextView goal4 =(TextView) rowView.findViewById(R.id.goalitem4);
        TextView goal5 =(TextView) rowView.findViewById(R.id.goalitem5);
        TextView goal6 =(TextView) rowView.findViewById(R.id.goalitem6);
        TextView goal7 =(TextView) rowView.findViewById(R.id.goalitem7);
        TextView week_number_pic =(TextView) rowView.findViewById(R.id.week_num_image);
        TextView Start_week_date =(TextView) rowView.findViewById(R.id.startweekdate);
        TextView Start_week_year =(TextView) rowView.findViewById(R.id.startweekyear);

        goal2.setText("gaol2");
        goal3.setText("gaol3");
        ImageView temppic=(ImageView)rowView.findViewById(R.id.circle_image);
        holder.goalitem.setText(categories.get(position).getDescription());
        week_number_pic.setText( String.valueOf(position));

        if (position==0)
        {
           temppic.setImageResource(R.drawable.circle_week_comp);
            goal4.setText("Bike with full rotations (all the way around)");
            goal5.setText("gaol5");
            goal6.setText("Bike with full rotations (all the way around");
            goal7.setText("Bike with full rotations (all the way around");

            goal4.setVisibility(View.VISIBLE);
            goal5.setVisibility(View.VISIBLE);
            goal6.setVisibility(View.VISIBLE);
            goal7.setVisibility(View.VISIBLE);



        }
        if (position==1)
        {
            temppic.setImageResource(R.drawable.circle_week_notcom);
        }

      /*  if(position==(getCount()-1)){
            View my_line = (View)
                    rowView.findViewById(R.id.verticallineview);
            //40dp, this is the ImageView height
            int dpsize = 40;
            //convert the height in dp unit to pixel (because the parameter is in px)
            int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpsize, mcontext.getResources().getDisplayMetrics());
            my_line.getLayoutParams().height = px;
        }*/
        return rowView;
    }

}