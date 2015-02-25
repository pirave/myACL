
package com.mobile.app.myacl.exerciseShow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobile.app.myacl.DailyPlan;
import com.mobile.app.myacl.DatabaseManager.ProtocolDB;
import com.mobile.app.myacl.ProtocolManager.Category;
import com.mobile.app.myacl.ProtocolManager.ExerciseManager.Exercise;
import com.mobile.app.myacl.R;
import com.mobile.app.myacl.slidetabss.SlidingTabLayout;

import java.util.List;


public class ExerciseTabs extends ActionBarActivity {
    private ProtocolDB pdb;
    static final String LOG_TAG = "SlidingTabsBasicFragment";
    private List<Exercise> exercises;

    /**
     * A custom {@link android.support.v4.view.ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link android.support.v4.view.ViewPager} which will be used in conjunction with the {@link com.mobile.app.myacl.slidetabss.SlidingTabLayout} above.
     */
    private ViewPager mViewPager;

    /**
     * Inflates the {@link android.view.View} which will be displayed by this {@link android.support.v4.app.Fragment}, from the app's
     * resources.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Exercise from Activity
        Intent intent = getIntent();
        exercises = ((Category) intent.getSerializableExtra(DailyPlan.EXTRA_EXERCISE))
                .getExercises();

        setContentView(R.layout.exercise_tabs);


        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());

        // Set tabs for view pager
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }
    // END_INCLUDE (fragment_onviewcreated)

    /**
     * The {@link android.support.v4.view.PagerAdapter} used to display pages in this sample.
     * The individual pages are simple and just display two lines of text. The important section of
     * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
     * {@link com.mobile.app.myacl.slidetabss.SlidingTabLayout}.
     */
    class SamplePagerAdapter extends PagerAdapter {
        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return exercises.size();
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(android.view.ViewGroup, int)} is the
         * same object as the {@link android.view.View} added to the {@link android.support.v4.view.ViewPager}.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)
        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link com.mobile.app.myacl.slidetabss.SlidingTabLayout}.
         * <p>
         * Here we construct one using the position value, but for real application the title should
         * refer to the item's contents.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return Integer.toString(position + 1);
        }
        // END_INCLUDE (pageradapter_getpagetitle)

        /**
         * Instantiate the {@link android.view.View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // Inflate a new layout from our resources
            View view = getLayoutInflater().inflate(R.layout.exercise_page,
                    container, false);
            // Add the newly created View to the ViewPager
            container.addView(view);

            Exercise exercise = exercises.get(position);


            ListView lv = (ListView) view.findViewById(R.id.listView);
            lv.setAdapter(new StepsListAdapter(getApplicationContext(), exercise.getSteps()));



            // Return the View
            return view;
        }

        /**
         * Destroy the item from the {@link android.support.v4.view.ViewPager}. In our case this is simply removing the
         * {@link android.view.View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
