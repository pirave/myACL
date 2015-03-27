package com.mobile.app.myacl;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings.Secure;

import com.mobile.app.myacl.DatabaseManager.DataAdapter;
import com.mobile.app.myacl.DatabaseManager.UserDB;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.UserManager.APIClient;
import com.mobile.app.myacl.UserManager.UserProfile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alaa on 2/16/2015.
 */
public class ProfileCreate extends ActionBarActivity {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private int yr, mon, dy;
    private Calendar selectedDate;
    TextView surgerydate;
    EditText name,age;
    RadioButton typea,typeb,female,male;
    Button bdone,bcancel;
    UserProfile x;
    String android_id,uid;
    UserDB uDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilecreate);
        x = UserProfile.getInstance(this);

        bdone=(Button)findViewById(R.id.buttondone);
        bcancel=(Button)findViewById(R.id.buttoncancel);
        name = (EditText) findViewById(R.id.pname);
        age=(EditText) findViewById(R.id.page);
        typea=(RadioButton) findViewById(R.id.radioa);
        typeb=(RadioButton) findViewById(R.id.radiob);
        female=(RadioButton) findViewById(R.id.radiof);
        male=(RadioButton) findViewById(R.id.radiom);
        surgerydate = (TextView) findViewById(R.id.psurgerydate);
        android_id = Secure.getString(this.getContentResolver(),Secure.ANDROID_ID);
        TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        uid = tManager.getDeviceId();
        selectedDate = Calendar.getInstance();
        yr = selectedDate.get(Calendar.YEAR);
        mon = selectedDate.get(Calendar.MONTH);
        dy = selectedDate.get(Calendar.DAY_OF_MONTH);
        selectedDate.set(yr, mon, dy);
        surgerydate.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                new DatePickerDialog(ProfileCreate.this, dateListener, yr, mon, dy).show();
            }
        });

        bdone.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (name.getText().toString().trim().isEmpty() || age.getText().toString().trim().isEmpty() )
                {
                    Toast.makeText(getApplicationContext(), "Please Fill your name and Age information",Toast.LENGTH_SHORT).show();
                }
                else {
                    x.setID(uid);
                    x.setUsername(name.getText().toString());
                    x.setAge(Integer.parseInt(age.getText().toString()));
                    x.setSurgeryDate(selectedDate.getTime());
                    x.setCreateDate(new Date());
                    if (typea.isChecked())
                    {
                        x.setSurgeryType(getString(R.string.surgery_type_a_label));
                    }
                    else
                    {
                        x.setSurgeryType(getString(R.string.surgery_type_b_label));
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
                    APIClient apiClient = new APIClient(ProfileCreate.this);
                    apiClient.sendProfileData(x);
                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                       .putLong("progressLastSyncDate", x.getSurgeryDate().getTime()).commit();
                    Toast.makeText(getApplicationContext(), "Profile Created!", Toast.LENGTH_SHORT).show();
                    DataAdapter mDataAdapter = new DataAdapter(getApplicationContext());
                    mDataAdapter.createDatabase();
                    PlanManager planManager = new PlanManager(getApplicationContext());
                    startActivity(new Intent(ProfileCreate.this, MainActivity.class));

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
