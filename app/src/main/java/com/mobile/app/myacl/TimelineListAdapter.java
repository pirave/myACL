package com.mobile.app.myacl;

/**
 * Created by Alaa on 2/23/2015.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.ProtocolManager.Goal;
import com.mobile.app.myacl.ProtocolManager.Week;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimelineListAdapter extends BaseAdapter{
    List<Week> weeks;
    Context context;
    private static LayoutInflater inflater;
    private Typeface tfLight;
    private Typeface tfSemiBold;

    public TimelineListAdapter(Context context, List<Week> weeks) {
        // TODO Auto-generated constructor stub
        this.weeks = weeks;
        this.context = context;
        this.tfLight = Typeface.createFromAsset(context.getAssets(),"fonts/OpenSans-Light.ttf");
        this.tfSemiBold = Typeface.createFromAsset(context.getAssets(),"fonts/OpenSans-Semibold.ttf");
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
        holder.weekText = (TextView) rowView.findViewById(R.id.week_text_image);
        holder.dateDay = (TextView) rowView.findViewById(R.id.startweekdate);
        holder.dateYear = (TextView) rowView.findViewById(R.id.startweekyear);
        holder.pic = (ImageView) rowView.findViewById(R.id.circle_image);

        holder.goal.setText(buildGoalsText(week.getGoals()));
        holder.goal.setTypeface(tfLight);
        holder.weekNum.setText(String.valueOf(position));
        holder.weekNum.setTypeface(tfSemiBold);
        holder.weekText.setText("Week");
        holder.weekText.setTypeface(tfSemiBold);
        holder.dateDay.setText(new SimpleDateFormat("MMM dd").format(week.getDate()));
        holder.dateDay.setTypeface(tfSemiBold);
        holder.dateYear.setText(new SimpleDateFormat("yyyy").format(week.getDate()));
        holder.dateYear.setTypeface(tfSemiBold);

        // Custom "Day 1" for week 0
        if (position == 0) {
            holder.weekText.setText("Day");
            holder.weekNum.setText(String.valueOf(1));
        }

        // if week.date <,=,> today date we will change the pics
        Date today;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            today = sdf.parse(sdf.format(new Date()));
        } catch (Exception e){
            today = new Date();
        }
        if (new PlanManager(context).getPlan().getWeekDaysByDate(week.getDate()).contains(today))
            holder.pic.setImageResource(R.drawable.timeline_circle_curr);
        else if (week.getDate().before(today))
            holder.pic.setImageResource(R.drawable.timeline_circle_comp);
        else
            holder.pic.setImageResource(R.drawable.timeline_circle_incomp);


        return rowView;
    }

    public class Holder
    {
        TextView goal;
        TextView weekNum;
        TextView weekText;
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