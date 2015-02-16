package com.mobile.app.myacl.DatabaseManager;

/**
 * Created by Alaa on 2/15/2015.
 */
import java.io.IOException;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataAdapter
{
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDB;
    private ProtocolDBHandler mDBHandler;

    public DataAdapter(Context context){
        this.mContext = context;
        mDBHandler = new ProtocolDBHandler(mContext);
    }

    public DataAdapter createDatabase() throws SQLException{
        try
        {
            mDBHandler.createDataBase();
            Log.d(TAG,"  DoneCreateDatabase");
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException{
        try
        {
            mDBHandler.openDataBase();
            mDBHandler.close();
            mDB = mDBHandler.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void close()
    {
        mDBHandler.close();
    }

    public Cursor getTestData(){
        try
        {
            String sql ="SELECT * FROM goals";

            Cursor mCur = mDB.rawQuery(sql, null);
            if (mCur!=null)
            {
                mCur.moveToNext();
            }
            return mCur;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}