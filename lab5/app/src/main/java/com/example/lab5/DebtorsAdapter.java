package com.example.lab5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DebtorsAdapter extends BaseAdapter {
    private Context context;
    List<Debtor> debtors = new ArrayList<>();

    public DebtorsAdapter(Context context, List<Debtor> objects){
        this.context=context;
        this.debtors=objects;
    }

    @Override
    public int getCount() {
        return debtors.size();
    }

    @Override
    public Object getItem(int position) {
        return debtors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.debtors_list_item, null);

        TextView firstName = (TextView) row.findViewById(R.id.firstName);
        firstName.setText(debtors.get(position).getName());
        TextView surname = (TextView) row.findViewById(R.id.surname);
        surname.setText(debtors.get(position).getPhone());

        Bitmap photo = null;
        String photoUri = debtors.get(position).getImagePath();

        try {
            if(photoUri != null){
                Uri imageUri = Uri.parse(photoUri);
                photo =
                        MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
            }
        } catch (IOException e) {
           // photo = null;
            Log.e("lab03Error", e.getStackTrace().toString());
        }
        if(photo == null){
            photo = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.avatar);
        }

        ImageView photoo = (ImageView) row.findViewById(R.id.imageView);
        photoo.setImageBitmap(photo);
        return row;
    }
}
