package com.example.lab3;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class StudentsAdapter extends BaseAdapter {
    private Context context;
    List<Student> students = new ArrayList<>();
    int l;

    public StudentsAdapter(Context context, List<Student> objects, int l){
        this.context = context;
        students = objects;
        this.l=l;
    }
    @Override
    public int getCount() {
        return students.size();
    }
    @Override
    public Object getItem(int i) {
        return students.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate( l, null);
        TextView studentName =
                (TextView)row.findViewById(R.id.studentName);
        studentName.setText(students.get(i).name);
        TextView studentPhone =
                (TextView)row.findViewById(R.id.studentSurname);
        studentPhone.setText(students.get(i).phone);
        return row;
    }

    public void setL(int l) {
        this.l = l;
    }
}
