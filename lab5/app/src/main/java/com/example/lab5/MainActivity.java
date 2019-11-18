package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        String log = preferences.getString("log", null);
        String pass = preferences.getString("pass", null);
        TextView login = (TextView) findViewById(R.id.editText);
        TextView password = (TextView) findViewById(R.id.editText2);
        login.setText(log);
        password.setText(pass);
    }

    public void loginOnClick(View view){
        TextView login = (TextView) findViewById(R.id.editText);
        TextView password = (TextView) findViewById(R.id.editText2);
        CheckBox Check = (CheckBox) findViewById(R.id.checkBox);
        String log = login.getText().toString();
        String pass = password.getText().toString();
        if(log.equals("") || log == null){
            login.setError(getString(R.string.login_empty_error));
        }
        if(pass.equals("") || pass == null){
            password.setError(getString(R.string.password_empty_error));
        }

        if (Check.isChecked()) {
            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor preferencesEditor = preferences.edit();
            preferencesEditor.putString("log", log);
            preferencesEditor.putString("pass", pass);
            boolean saved = preferencesEditor.commit();
            if (saved == false || !pass.equals("") || !log.equals("")) {
                Toast.makeText(this, getText(R.string.login_data_save_success), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, getText(R.string.login_data_save_error), Toast.LENGTH_SHORT).show();
            }
        }
        if(!(pass.equals("") || log.equals(""))){
            Intent intent = new Intent(this, debtorActivity.class);
            startActivity(intent);
        }
    }
}
