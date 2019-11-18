package com.example.lab6;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;

public class LoginActivity extends AppCompatActivity {

    private String response;
    private String[] parts;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = getSharedPreferences("lab6",MODE_PRIVATE);

        TextView nick = (TextView) findViewById(R.id.nickname);
        TextView pass = (TextView) findViewById(R.id.password);

        nick.setText(preferences.getString("login",""));
        pass.setText(preferences.getString("pass",""));
        token = preferences.getString("token","");
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onLoginClick(View view) throws UnsupportedEncodingException {
        TextView nick = (TextView) findViewById(R.id.nickname);
        TextView pass = (TextView) findViewById(R.id.password);
        CheckBox check = (CheckBox) findViewById(R.id.checkBox);

        LoginTask task = new LoginTask((string) ->{
                JSONObject json = new JSONObject(string);
                response = json.getString("response");
                if(response.equals("success")){
                    parts = string.split(",");
                    token =  parts[1];
                    System.out.println(token);
                    if (check.isChecked()) {
                        SharedPreferences preferences = getSharedPreferences("lab6", MODE_PRIVATE);
                        SharedPreferences.Editor preferencesEditor = preferences.edit();
                        preferencesEditor.putString("login", nick.getText().toString());
                        preferencesEditor.putString("pass", pass.getText().toString());
                        preferencesEditor.putString("token", token);
                        preferencesEditor.apply();
                    }else{
                        SharedPreferences preferences = getSharedPreferences("lab6", MODE_PRIVATE);
                        SharedPreferences.Editor preferencesEditor = preferences.edit();
                        preferencesEditor.putString("token", token);
                        preferencesEditor.apply();
                    }
                    Intent intent = new Intent(this, DebtorActivity.class);
                    startActivity(intent);
                }else{
                    TextView tv = (TextView) findViewById(R.id.errorText);
                    tv.setTextColor(getResources().getColor(R.color.red));
                    tv.setText("Bad login or password");
                }
        });
        task.execute("http://apps.ii.uph.edu.pl:88/MSK/MSK/Authenticate", "login=" +
                URLEncoder.encode(nick.getText().toString(), "UTF-8"), "password=" +
                URLEncoder.encode(pass.getText().toString(), "UTF-8"));
    }
}
