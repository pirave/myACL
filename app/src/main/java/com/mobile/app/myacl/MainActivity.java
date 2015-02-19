package com.mobile.app.myacl;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mobile.app.myacl.DatabaseManager.DataAdapter;
import com.mobile.app.myacl.DatabaseManager.UserDB;
import com.mobile.app.myacl.PlanManager.PlanManager;
import com.mobile.app.myacl.UserManager.UserProfile;

import java.text.SimpleDateFormat;


public class MainActivity extends ActionBarActivity {
    static UserDB adb;
    Button show,creat;
    // private ArrayList<UserProfile> userprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creat=(Button)findViewById(R.id.buttoncreat);
        show=(Button)findViewById(R.id.buttonshow);


       // DataAdapter mDataAdapter = new DataAdapter(this);
       // mDataAdapter.createDatabase();
       // adb = new UserDB(this);
      //  adb.open();
        //userprofile = adb.getProfileData();
      //  adb.close();
        //mDataAdapter.open();

        // Cursor testdata = mDataAdapter.getTestData();

        // mDataAdapter.close();
        //UserProfile x = UserProfile.getInstance();
     //   x.setSurgeryType("ACL");
     //   PlanManager planManager = new PlanManager(this);
     //   planManager.getPlan();




        creat.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent view = new Intent(MainActivity.this, ProfileCreate.class);
                MainActivity.this.startActivity(view);
            }
        });
        show.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent view = new Intent(MainActivity.this, ProfileShow.class);
                MainActivity.this.startActivity(view);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
