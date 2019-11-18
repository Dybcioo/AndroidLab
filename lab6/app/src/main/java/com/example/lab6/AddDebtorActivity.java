package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.Serializable;

public class AddDebtorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debtor);
    }

    public void onFinishAddDebtorActivity(View view){
        TextView id = (TextView) findViewById(R.id.add_id);
        TextView name = (TextView) findViewById(R.id.add_name);
        TextView phone = (TextView) findViewById(R.id.add_phone);

        LoginTask task = new LoginTask(string -> {
            JSONObject job = new JSONObject(string);
            String responseType = job.getString("response");
            if(responseType.equals("success")){
                System.out.println("git");
            }else{
                System.out.println("lipa");
            }
        });
        SharedPreferences preferences = getSharedPreferences("lab6",MODE_PRIVATE);
        String token = preferences.getString("token","");

        task.execute("http://apps.ii.uph.edu.pl:88/MSK/MSK/AddDebtor"
                ,"token="+token.subSequence(9,45)
                ,"dId="+id.getText().toString()
                ,"dName="+name.getText().toString()
                ,"dPhone="+phone.getText().toString());

        finish();
    }
}
