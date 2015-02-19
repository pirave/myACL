package com.mobile.app.myacl;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings.Secure;

import com.mobile.app.myacl.DatabaseManager.UserDB;
import com.mobile.app.myacl.UserManager.UserProfile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alaa on 2/16/2015.
 */
public class ProfileCreate extends ActionBarActivity {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private int yr, mon, dy;
    private Calendar selectedDate;
    TextView surgerydate;
    EditText name,age;
    RadioButton typea,typeb,female,male;
    Button bdone,bcancel;
    UserProfile x;
    String android_id;
    UserDB uDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilecreate);
        x = UserProfile.getInstance();

        bdone=(Button)findViewById(R.id.buttondone);
        bcancel=(Button)findViewById(R.id.buttoncancel);
        name = (EditText) findViewById(R.id.pname);
        age=(EditText) findViewById(R.id.page);
        typea=(RadioButton) findViewById(R.id.radioa);
        typeb=(RadioButton) findViewById(R.id.radiob);
        female=(RadioButton) findViewById(R.id.radiof);
        male=(RadioButton) findViewById(R.id.radiom);
        surgerydate = (TextView) findViewById(R.id.psurgerydate);
        android_id = Secure.getString(getContentResolver(),Secure.ANDROID_ID);


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

        bdone.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (name.getText().toString() == "" || age.getText().toString()== "" )
                {
                    Toast.makeText(getApplicationContext(), "Please Fill your name and Age information",Toast.LENGTH_SHORT).show();
                }
                else {
                    x.setID(android_id);
                    x.setUsername(name.getText().toString());
                    x.setAge(Integer.parseInt(age.getText().toString()));
                    x.setSurgeryDate(selectedDate.getTime());
                    x.setCreateDate(new Date());
                    if (typea.isChecked())
                    {
                        x.setSurgeryType("A");
                    }
                    else
                    {
                        x.setSurgeryType("B");
                    }
                    if (male.isChecked())
                    {
                        x.setGender("Male");
                    }
                    else
                    {
                        x.setGender("Female");
                    }
                    uDB = new UserDB(ProfileCreate.this);
                    uDB.open();
                    long returnData = uDB.createProfileEntry(x);
                    Log.d("Add UserDB Entery", " " + returnData);
                    uDB.close();
                    Toast.makeText(getApplicationContext(), "Profile Created!", Toast.LENGTH_SHORT).show();

                }
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