package com.mobile.app.myacl.CategoryAdapters;

import android.content.Context;
import com.mobile.app.myacl.ProtocolManager.Category;
import java.util.List;

/**
 * Created by pirave on 15-02-27.
 */
public class TodayCategoryListAdapter extends CategoryListAdapter {

    public TodayCategoryListAdapter(Context context, List<Category> categories) {
        super(context, categories);
    }

    public void remove(int pos) {
        categories.remove(pos);
        notifyDataSetChanged();
    }

}
