package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class addDebtorActivity extends AppCompatActivity {


    private static final int RESULT_LOAD_IMAGE = 11;
    private static final int PICK_CONTACT_REQUEST = 2;
    private static String picturePath = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debtor);
    }

    public void onFinishAddClick(View view){
        TextView tv1 = (TextView) findViewById(R.id.firstName2);
        TextView tv2 = (TextView) findViewById(R.id.surname2);

        Intent returnIntent = getIntent();
        returnIntent.putExtra("firstName", tv1.getText());
        returnIntent.putExtra("surname", tv2.getText());
        returnIntent.putExtra("imagePath", picturePath);
        System.out.println(picturePath);

        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public void onImageAddClick(View view){
        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);

    }
    public void onAddFromContactClick(View view){
        Intent i = new Intent(
                Intent.ACTION_PICK, Uri.parse("content://contacts"));
        i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(i, PICK_CONTACT_REQUEST);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == PICK_CONTACT_REQUEST){
            if(resultCode == RESULT_OK){
                Uri contactUri = data.getData();

                String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.PHOTO_URI};

                Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);

                cursor.moveToFirst();

                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME);
                ((TextView) findViewById(R.id.firstName2)).setText(cursor.getString(column));

                column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                ((TextView) findViewById(R.id.surname2)).setText(cursor.getString(column));

                column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI);
                picturePath = cursor.getString(column);

            }
        }
        if(requestCode == RESULT_LOAD_IMAGE){
            if(resultCode == RESULT_OK){
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);

            }
        }
    }
}
