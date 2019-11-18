package com.example.lab6;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DebtorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.LayoutManager layoutManager;
    private List<Debtor> myDebtors = new ArrayList<>();
    private String token;
    private Button remove ;
    private Button add ;
    private CheckBox check ;
    private List<Debtor> removeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debtor);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(myDebtors,R.layout.debtor_list_profile);
        recyclerView.setAdapter(mAdapter);

        remove = (Button) findViewById(R.id.removeDebtor);
        add = (Button) findViewById(R.id.addDebtor);
        check = (CheckBox) findViewById(R.id.checkBox2);
        downloadDebtors();
    }
    public void onRemoveDebtorClick(View view){
        if(remove.getText().equals("remove")) {
            mAdapter2 = new MyAdapter(myDebtors, R.layout.debtor_check_list_profile);
            recyclerView.setAdapter(mAdapter2);
            mAdapter.notifyDataSetChanged();
            remove.setText("cancel");
            add.setText("del");
        }else{
            mAdapter2 = new MyAdapter(myDebtors, R.layout.debtor_list_profile);
            recyclerView.setAdapter(mAdapter2);
            mAdapter.notifyDataSetChanged();
            remove.setText("remove");
            add.setText("add");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onAddDebtorClick(View view) {
        if(add.getText().equals("add")) {
            Intent i = new Intent(this, AddDebtorActivity.class);
            startActivity(i);
            mAdapter.notifyDataSetChanged();
        }else{
            mAdapter2 = new MyAdapter(myDebtors, R.layout.debtor_list_profile);
            recyclerView.setAdapter(mAdapter2);
            mAdapter.notifyDataSetChanged();
            remove.setText("remove");
            add.setText("add");

            SharedPreferences preferences = getSharedPreferences("lab6",MODE_PRIVATE);
            token = preferences.getString("token","");

            removeList = new ArrayList<>();
            myDebtors.forEach(debtor -> {
                if(debtor.getSelected()){
                    removeList.add(debtor);
                    new LoginTask(string -> {}).execute("http://apps.ii.uph.edu.pl:88/MSK/MSK/DeleteDebtor", "token="+token.subSequence(9,45),"dId="+debtor.getId());
                }
            });
            removeList.forEach(i -> {
                myDebtors.remove(i);
            });
            removeList.clear();
            mAdapter.notifyDataSetChanged();
        }
    }

    public void downloadDebtors(){
        LoginTask debtorsTask = new LoginTask(string -> {
            JSONObject job = new JSONObject(string);
            String responseType = job.getString("response");
            if(responseType.equals("success")){
                JSONArray debtorsJsonArray = new JSONArray(job.getString("debtors"));
                for (int i = 0; i < debtorsJsonArray.length(); i++){
                    JSONObject jsonArrayObj = debtorsJsonArray.getJSONObject(i);
                    myDebtors.add(new Debtor(
                            jsonArrayObj.getString("Id"),
                            jsonArrayObj.getString("Name"),
                            jsonArrayObj.getString("Phone"),
                            jsonArrayObj.getString("Photo")
                    ));
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        SharedPreferences preferences = getSharedPreferences("lab6",MODE_PRIVATE);
        token = preferences.getString("token","");
        debtorsTask.execute("http://apps.ii.uph.edu.pl:88/MSK/MSK/GetDebtors", "token="+token.subSequence(9,45));
    }

}
