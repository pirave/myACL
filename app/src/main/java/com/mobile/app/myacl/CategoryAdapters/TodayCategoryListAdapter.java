package com.mobile.app.myacl.CategoryAdapters;

import android.content.Context;
import com.mobile.app.myacl.ProtocolManager.Category;
import com.mobile.app.myacl.UserManager.ProgressTracker;

import java.util.List;

/**
 * Created by pirave on 15-02-27.
 */
public class TodayCategoryListAdapter extends CategoryListAdapter {

    public TodayCategoryListAdapter(Context context, List<Category> categories) {
        super(context, categories);
    }

    @Override
    public Category remove(int pos) {
        Category c = categories.get(pos);
        categories.remove(c);
        notifyDataSetChanged();
        return c;
    }

    @Override
    public void insert(Category c){
        categories.add(c);
        notifyDataSetChanged();
    }

}
