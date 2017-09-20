package com.hod.hodassignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vikas.sunil.barve on 9/19/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper implements DatabaseConstants{

    private static DatabaseHelper databaseHelper = null;
    private static Context mContext;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public  static DatabaseHelper getInstance(Context context) {
        if(databaseHelper == null){
            databaseHelper = new DatabaseHelper(context.getApplicationContext());
        }
        mContext = context;
        return databaseHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //table creation for customer chat
        db.execSQL(DatabaseConstants.TableCustomerChat.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
