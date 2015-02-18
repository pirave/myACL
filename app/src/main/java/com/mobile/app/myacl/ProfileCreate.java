package com.mobile.app.myacl;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.mobile.app.myacl.DatabaseManager.DataAdapter;
import com.mobile.app.myacl.DatabaseManager.UserDB;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Alaa on 2/16/2015.
 */
public class ProfileCreate extends ActionBarActivity {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private int yr, mon, dy;
    private Calendar selectedDate;
    TextView surgerydate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilecreate);
        surgerydate = (TextView) findViewById(R.id.psurgerydate);
        surgerydate.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                selectedDate = Calendar.getInstance();
                yr = selectedDate.get(Calendar.YEAR);
                mon = selectedDate.get(Calendar.MONTH);
                dy = selectedDate.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(ProfileCreate.this, dateListener, yr, mon, dy).show();
            }
        });

    }
    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int
                        monthOfYear, int dayOfMonth){

                    selectedDate= Calendar.getInstance();
                    yr=year;
                    mon=monthOfYear;
                    dy=dayOfMonth;
                    selectedDate.set(yr, mon, dy);
                   surgerydate.setText(dateFormat.format(selectedDate.getTime()));
                }
            };

}
