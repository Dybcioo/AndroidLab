package com.example.lab5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private Context mContext;
    final static String _ID = "_id";
    final static String DEBTOR_NAME = "name";
    final static String DEBTOR_PHONE = "phone";
    final static String DEBTOR_PHOTO_URI = "photo_uri";
    final static String TABLE_NAME = "debtors";
    final static String[] columns = {_ID, DEBTOR_NAME, DEBTOR_PHONE,
            DEBTOR_PHOTO_URI};
    final private static String NAME = "debtors_db";
    final private static Integer VERSION = 1;
    final private static String CREATE_CMD =
            "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DEBTOR_NAME + " TEXT NOT NULL, "
            + DEBTOR_PHONE + " TEXT NOT NULL, "
            + DEBTOR_PHOTO_URI+ " TEXT );";
    public DatabaseOpenHelper(Context context){
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }
    void deleteDatabase(){
        mContext.deleteDatabase(NAME);
    }
}