package com.hod.hodassignment.database;

import android.provider.BaseColumns;

/**
 * Created by vikas.sunil.barve on 9/20/2017.
 */

public interface DatabaseConstants {
    int DATABASE_VERSION = 1;
    String DATABASE_NAME = "HodAssignment.db";

    String TYPE_TEXT = " TEXT";
    String TYPE_INTEGER = " INTEGER";
    String COMMA_SEP = ",";
    String COLUMN_NAME_NULLABLE = "null";

    int CUSTOMER_ID_CONSTANT = 1;
    int SENDER_ID_CONSTANT = 1;
    int RECEIVER_ID_CONSTANT = 2;

    interface TableCustomerChat extends BaseColumns {

        String TABLE_NAME = "CustomerChatTable";
        String COLUMN_NAME_SENDERID = "senderid";
        String COLUMN_NAME_RECEIVERID = "receiverid";
        String COLUMN_NAME_MESSAGE = "message";

        String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_NAME_SENDERID + TYPE_INTEGER + COMMA_SEP +
                        COLUMN_NAME_RECEIVERID + TYPE_INTEGER + COMMA_SEP +
                        COLUMN_NAME_MESSAGE + TYPE_TEXT +
                        ")";

    }

}
