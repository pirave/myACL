package com.mobile.app.myacl.CategoryAdapters;

/**
 * Created by Alaa on 2/23/2015.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobile.app.myacl.ProtocolManager.Category;
import com.mobile.app.myacl.R;

import java.util.List;

public class CategoryListAdapter extends BaseAdapter {
    protected List<Category> categories;
    protected LayoutInflater inflater=null;
    public CategoryListAdapter(Context context, List<Category> categories) {
        // TODO Auto-generated constructor stub
        this.categories =categories;
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
        TextView categoryItem;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.category_list, null);
        holder.categoryItem =(TextView) rowView.findViewById(R.id.goalitem);
        holder.categoryItem.setText(categories.get(position).getDescription());

        return rowView;
    }

    public void remove(int pos){
        // Do nothing
    };

}