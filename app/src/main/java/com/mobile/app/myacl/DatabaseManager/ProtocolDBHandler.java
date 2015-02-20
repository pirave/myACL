package com.mobile.app.myacl.DatabaseManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobile.app.myacl.R;
import com.mobile.app.myacl.UserManager.UserProfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by Alaa on 2/15/2015.
 */
public class ProtocolDBHandler extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    public static String DATABASE_NAME;
    private static String DATABASE_PATH = "";

    public static String TABLE_CATEGORY = "category";
    public static String TABLE_MEDIA = "media";
    public static String TABLE_GOALS = "goals";

    public static String KEY_WEEKNUM = "week_num";
    public static String KEY_GOAL_DESC = "goals_desc";
    public static String KEY_CAT_DESC = "cat_desc";
    public static String KEY_CAT_ID = "cat_id";
    public static String FKEY_EXE_ID = "exe_id_fk";

    private SQLiteDatabase mDB;
    private final Context mContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public ProtocolDBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        if(android.os.Build.VERSION.SDK_INT >= 17){
            DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DATABASE_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException {
        if(!doesDatabaseExist()){
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {
                copyDataBase();
                Log.d("DataAdapterH", "createDatabase database created");

            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean doesDatabaseExist() {
        File dbFile = mContext.getDatabasePath(DATABASE_PATH + DATABASE_NAME);
        return dbFile.exists();
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
        //Open your local db as the input stream
        InputStream myInput = mContext.getAssets().open("databases/" + DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DATABASE_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DATABASE_PATH + DATABASE_NAME;
        mDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if(mDB != null)
            mDB.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return mDB.query(....)" so it'd be easy
    // to you to create adapters for your views.

}