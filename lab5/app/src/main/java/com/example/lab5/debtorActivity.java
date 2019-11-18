package com.example.lab5;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.lab5.DatabaseOpenHelper.DEBTOR_NAME;
import static com.example.lab5.DatabaseOpenHelper.DEBTOR_PHONE;
import static com.example.lab5.DatabaseOpenHelper.DEBTOR_PHOTO_URI;
import static com.example.lab5.DatabaseOpenHelper.TABLE_NAME;

public class debtorActivity extends AppCompatActivity {

    public final static int REQUEST_ADD_DEBTOR = 1;
    DebtorsAdapter adapter;
    List<Debtor> myDebtors = new ArrayList<>();
    SQLiteDatabase mDB;
    DatabaseOpenHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debtor);

        adapter = new DebtorsAdapter(this,myDebtors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        db = new DatabaseOpenHelper(this);

        mDB = db.getWritableDatabase();

        readDb();


    }

    public void addDebtorOnClick(View view){
        Intent intent = new Intent(this, addDebtorActivity.class);
        startActivityForResult(intent, REQUEST_ADD_DEBTOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD_DEBTOR) {
            if(resultCode == Activity.RESULT_OK){
                CharSequence name = data.getCharSequenceExtra("firstName");
                CharSequence phone = data.getCharSequenceExtra("surname");
                CharSequence photo = data.getCharSequenceExtra("imagePath");
                myDebtors.add(new Debtor(name.toString(),phone.toString(),((photo==null)? null : photo.toString())));

                ContentValues values = new ContentValues();
                values.put(DEBTOR_NAME, name.toString());
                values.put(DEBTOR_PHONE, phone.toString());
                values.put(DEBTOR_PHOTO_URI, (photo==null)? null : photo.toString());
                mDB.insert(TABLE_NAME, null, values);
                adapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //W przpyadku otrzymania błędnych rezultatów
            }
        }
    }

    public void readDb(){
        Cursor cursor = db.getReadableDatabase().query(TABLE_NAME,
                null,null,null,null,null,"_id");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Debtor debtor = new Debtor();
            int idx = cursor.getColumnIndex(DEBTOR_NAME);
            debtor.setName(cursor.getString(idx));
            idx = cursor.getColumnIndex(DEBTOR_PHONE);
            debtor.setPhone(cursor.getString(idx));
            idx = cursor.getColumnIndex(DEBTOR_PHOTO_URI);
            debtor.setImagePath(cursor.getString(idx));
            idx = cursor.getColumnIndex("_id");
            debtor.setId(cursor.getString(idx));
            myDebtors.add(debtor);
            cursor.moveToNext();
        }
        cursor.close();
    }
    public void onRemoveClick(View view){

        db.deleteDatabase();
        myDebtors=new ArrayList<>();
        adapter.notifyDataSetChanged();
    }
}
