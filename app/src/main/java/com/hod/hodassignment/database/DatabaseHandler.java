package com.hod.hodassignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.hod.hodassignment.model.ChatModel;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by vikas.sunil.barve on 9/20/2017.
 */

public class DatabaseHandler implements DatabaseConstants{

    private static DatabaseHandler instance;


    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    private DatabaseHandler() {
    }

    // insert customer Chat
    public void insertCustomerChat(Context context, ChatModel chatModel) {
        SQLiteDatabase db = DatabaseHelper.getInstance(context).getWritableDatabase();

        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(TableCustomerChat.COLUMN_NAME_SENDERID, chatModel.getSenderId());
        values.put(TableCustomerChat.COLUMN_NAME_RECEIVERID, chatModel.getReceiverId());
        values.put(TableCustomerChat.COLUMN_NAME_MESSAGE, chatModel.getMessage());
        db.insert(TableCustomerChat.TABLE_NAME, COLUMN_NAME_NULLABLE, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public ArrayList<ChatModel> getCustomerChat(Context context) {

        ArrayList<ChatModel> chatModelArrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DatabaseConstants.TableCustomerChat.TABLE_NAME;

        SQLiteDatabase db = DatabaseHelper.getInstance(context).getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ChatModel chatModel = new ChatModel();
                chatModel.setSenderId(cursor.getInt(cursor.getColumnIndex(TableCustomerChat.COLUMN_NAME_SENDERID)));
                chatModel.setReceiverId(cursor.getInt(cursor.getColumnIndex(TableCustomerChat.COLUMN_NAME_RECEIVERID)));
                chatModel.setMessage(cursor.getString(cursor.getColumnIndex(TableCustomerChat.COLUMN_NAME_MESSAGE)));
                chatModelArrayList.add(chatModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return chatModelArrayList;
    }

}
