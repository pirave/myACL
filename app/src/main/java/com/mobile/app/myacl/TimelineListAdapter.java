package com.mobile.app.myacl;

/**
 * Created by Alaa on 2/23/2015.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.app.myacl.ProtocolManager.Goal;
import com.mobile.app.myacl.ProtocolManager.Week;

import java.text.SimpleDateFormat;
import java.util.List;

public class TimelineListAdapter extends BaseAdapter{
    List<Week> weeks;
    Context context;
    private static LayoutInflater inflater;

    public TimelineListAdapter(Context context, List<Week> weeks) {
        // TODO Auto-generated constructor stub
        this.weeks = weeks;
        this.context =context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return weeks.size();
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Week week = weeks.get(position);
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.timeline_item, null);

        holder.goal = (TextView) rowView.findViewById(R.id.txtGoals);
        holder.weekNum = (TextView) rowView.findViewById(R.id.week_num_image);
        holder.dateDay =(TextView) rowView.findViewById(R.id.startweekdate);
        holder.dateYear =(TextView) rowView.findViewById(R.id.startweekyear);
        holder.pic=(ImageView)rowView.findViewById(R.id.circle_image);

        holder.goal.setText(buildGoalsText(week.getGoals()));
        holder.weekNum.setText(String.valueOf(position));
        holder.dateDay.setText(new SimpleDateFormat("MMM dd").format(week.getDate()));
        holder.dateYear.setText(new SimpleDateFormat("yyyy").format(week.getDate()));

        // if week.date <,=,> today date we will change the pics
        if (position==0)
           holder.pic.setImageResource(R.drawable.circle_week_comp);
        else if (position==1)
            holder.pic.setImageResource(R.drawable.circle_week_notcom);
        else
            holder.pic.setImageResource(R.drawable.circle_week_all);


        return rowView;
    }

    public class Holder
    {
        TextView goal;
        TextView weekNum;
        TextView dateDay;
        TextView dateYear;
        ImageView pic;
    }

    private String buildGoalsText(List<Goal> goals){
        StringBuilder result = new StringBuilder();
        for(Goal goal : goals) {
            result.append(goal.getDescription());
            result.append("\n");
        }
        return result.length() > 0 ? result.substring(0, result.length() - 1): "";
    }

}