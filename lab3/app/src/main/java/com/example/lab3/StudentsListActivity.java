package com.example.lab3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentsListActivity extends AppCompatActivity {
    List<String> myStrings = new ArrayList<>();
    int index = 0;
    StudentsAdapter adapter;
    List<Student> myStudents = new ArrayList<>();
    boolean kaczka = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        adapter = new StudentsAdapter(this,myStudents,R.layout.students_list_item);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


        final Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText().equals("Add")) {
                    Intent intent = new Intent(getApplicationContext(), AddStudentActivity.class);
                    startActivityForResult(intent, 1);
                }else{

                }
            }
        });

        listView.setOnItemClickListener(new
                                                AdapterView.OnItemClickListener() {
                                                    public void onItemClick(AdapterView parent, View v, int position,
                                                                            long id) {

                        /*
                        Log.d("TagName", "position:" + position + " id:" + id);
                        String el = adapter.getItem(position);
                        adapter.remove(el);
                        adapter.notifyDataSetChanged();
                        */
                                                        if(kaczka) {
                                                            CheckedTextView checkedTextView =
                                                                    (CheckedTextView) v.findViewById(android.R.id.text1);

                                                            checkedTextView.setChecked(!checkedTextView.isChecked());

                                                        }

                                                    }
                                                });

        final Button b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(b.getText().equals("Remove")){
                    adapter.setL(android.R.layout.simple_list_item_multiple_choice);
                    adapter.notifyDataSetChanged();
                    kaczka = true;
                    b.setText("cancel");
                    button.setText("delete");
                }else{
                    adapter.setL(R.layout.students_list_item);
                    adapter.notifyDataSetChanged();
                    kaczka = false;
                    b.setText("Remove");
                    button.setText("Add");
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                CharSequence result = data.getCharSequenceExtra("result");
                CharSequence result2 = data.getCharSequenceExtra("result2");
                myStudents.add(new Student(result.toString(),result2.toString()));
                adapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //W przpyadku otrzymania błędnych rezultatów
            }
        }

    }
}
