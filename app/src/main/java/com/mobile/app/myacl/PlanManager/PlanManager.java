package com.mobile.app.myacl.PlanManager;

import android.content.Context;
import android.util.Log;

import com.mobile.app.myacl.ProtocolManager.Category;
import com.mobile.app.myacl.ProtocolManager.Goal;
import com.mobile.app.myacl.ProtocolManager.Week;
import com.mobile.app.myacl.R;
import com.mobile.app.myacl.UserManager.UserProfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by pirave on 15-02-17.
 */
public class PlanManager {
    private static String TAG = "PlanManager";
    private static Plan plan = null;
    private Context context;

    public PlanManager(Context context) {
        // Constructor
        this.context = context;

        if (plan == null && !openPlan()) {
            plan = new PlanGenerator(context).getPlan();
            savePlan();
        }

    }

    public Plan getPlan() {
        return plan;
    }

    private boolean openPlan(){
        // open the object from file if it exists
        Log.d(TAG, "Opening Plan");
        try {
            File file = new File(context.getFilesDir(), context.getString(R.string.plan_filename));
            if (!file.exists())
                return false;
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fis);
            this.plan = (Plan) in.readObject();
            in.close();
            fis.close();
            Log.d(TAG,"Opened Plan. Proof: ");
            PlanGenerator.check(plan);
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void savePlan(){
        // save the object to file
        Log.d(TAG, "Saved Plan");
        try {
            File file = new File(context.getFilesDir(), context.getString(R.string.plan_filename));
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(getPlan());
            out.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * When the user adjusts his or her plan.
     */
    public void adjustPlan(){
        plan = new PlanGenerator(context).getPlan();
        savePlan();
    };
}
