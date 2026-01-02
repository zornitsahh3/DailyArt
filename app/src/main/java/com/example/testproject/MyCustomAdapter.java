package com.example.testproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyCustomAdapter extends ArrayAdapter<Painting> {

    private final ArrayList<Painting> paintingsArrayList;
    private final Context context;

    public MyCustomAdapter(Context context, ArrayList<Painting> paintingsArrayList) {
        super(context, R.layout.item_list_layout, paintingsArrayList);
        this.paintingsArrayList = paintingsArrayList;
        this.context = context;
    }

    private static class MyViewHolder {
        TextView paintingName;
        TextView authorName;
        ImageView paintingImage;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Painting painting = getItem(position);
        MyViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_list_layout, parent, false);

            viewHolder = new MyViewHolder();
            viewHolder.paintingName = convertView.findViewById(R.id.paintingName);
            viewHolder.authorName = convertView.findViewById(R.id.paintingAuthor);
            viewHolder.paintingImage = convertView.findViewById(R.id.paintingImage);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        if (painting != null) {
            viewHolder.paintingName.setText(painting.getPaintingName());
            viewHolder.authorName.setText(painting.getAuthorName());
            viewHolder.paintingImage.setImageResource(painting.getPaintingImage());
        }

        return convertView;
    }
}
