package com.example.finallab;

import android.provider.BaseColumns;

public final class MyDatabaseContract {
    private MyDatabaseContract() {}

    public static class UserEntry {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_VERIFIED = "verified";
        public static final String COLUMN_NAME_OTP_CODE = "otp_code";

        public static final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_EMAIL + " TEXT PRIMARY KEY, " +
                COLUMN_NAME_PASSWORD + " TEXT, " +
                COLUMN_NAME_VERIFIED + " INTEGER DEFAULT 0, " +
                COLUMN_NAME_OTP_CODE + " INTEGER)";

        public static final String SQL_DELETE_USERS_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class UserRegis {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_PHONED = "phone";


        public static final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_NAME + " TEXT PRIMARY KEY, " +
                COLUMN_NAME_EMAIL + " TEXT, " +
                COLUMN_NAME_PASSWORD + " TEXT, " +
                COLUMN_NAME_PHONED + "INTERGER" ;

        public static final String SQL_DELETE_USERS_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class TransactionEntry implements BaseColumns {
        public static final String TABLE_NAME = "transactions";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_MEDICINE_NAME = "medicine_name";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
        public static final String COLUMN_NAME_USER_ID = "user_id";
    }
}

