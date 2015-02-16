package com.mobile.app.myacl.DatabaseManager;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pirave on 15-02-16.
 */
public class ProtocolDB {

    private ProtocolDBHandler mDBHandler;
    private final Context mContext;
    private SQLiteDatabase mDB;

    public ProtocolDB(Context c) {
        mContext = c;
    }

    public void open() throws SQLException {
        mDBHandler = new ProtocolDBHandler(mContext);
        mDB = mDBHandler.getWritableDatabase();
    }
}
