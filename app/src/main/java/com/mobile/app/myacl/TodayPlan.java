package com.mobile.app.myacl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.mobile.app.myacl.exerciseShow.ExerciseShow;


/**
 * Created by Alaa on 2/16/2015.
 */
public class TodayPlan extends ActionBarActivity {

    Button gotoexe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todayplan);

        gotoexe = (Button) findViewById(R.id.gotoe);
        gotoexe.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(TodayPlan.this, ExerciseShow.class));
            }
        });
    }

}
