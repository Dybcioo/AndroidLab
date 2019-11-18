package com.example.lab6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Debtor> mDataset;
    private int lay;

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        private View view;
        private CheckBox check;
        public MyViewHolder(View view) {
            super(view);
            this.view=view;
            check = (CheckBox) view.findViewById(R.id.checkBox2);
        }
    }

    public MyAdapter(List<Debtor> mDataset, int layout) {
        this.mDataset = mDataset;
        lay=layout;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext())
               .inflate(lay,parent,false);

       MyViewHolder vh = new MyViewHolder(v);
       return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        View view = holder.view;
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView id = (TextView) view.findViewById(R.id.id);
        TextView phone = (TextView) view.findViewById(R.id.phone);
        ImageView image = (ImageView) view.findViewById(R.id.photo);
        if(lay==R.layout.debtor_check_list_profile) {
            holder.check.setChecked(mDataset.get(position).getSelected());

            holder.check.setTag(position);
            holder.check.setOnClickListener(v -> {
                Integer pos = (Integer) holder.check.getTag();
                if (mDataset.get(pos).getSelected()) {
                    mDataset.get(pos).setSelected(false);
                } else {
                    mDataset.get(pos).setSelected(true);
                }
            });
        }

        name.setText(mDataset.get(position).getName());
        id.setText(mDataset.get(position).getId());
        phone.setText(mDataset.get(position).getPhone());

        Bitmap photo = null;
        String photoUri = mDataset.get(position).getPhoto();
        try {
            if(photoUri != null){
                Uri imageUri = Uri.parse(photoUri);
                photo = MediaStore.Images.Media.getBitmap(view.getContext().getContentResolver(), imageUri);
            }
        } catch (Exception e) {
            photo = null;
            Log.e("lab06Error", e.getStackTrace().toString());
        }
        if(photo == null){
            photo = BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.avatar);
        }
        image.setImageBitmap(photo);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void deleteItem(Debtor debtor) {
        int position=mDataset.indexOf(debtor);
        mDataset.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDataset.size());
    }

}
