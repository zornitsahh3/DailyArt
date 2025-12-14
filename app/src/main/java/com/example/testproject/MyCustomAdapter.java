package com.example.testproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyCustomAdapter extends ArrayAdapter<Painting> {

    private ArrayList<Painting> paintingsArrayList;
    private Context context;

    public MyCustomAdapter(Context context1, ArrayList<Painting> paintingsArrayList) {
        super(context1, R.layout.item_list_layout, paintingsArrayList);
        this.paintingsArrayList = paintingsArrayList;
        this.context = context1;
    }

    private static class MyViewHolder {
        TextView paintingName;
        TextView authorName;
        ImageView paintingImage;
        ImageButton buttonFavorite;
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
          //  viewHolder.buttonFavorite = convertView.findViewById(R.id.buttonFavorite);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        if (painting != null) {
            viewHolder.paintingName.setText(painting.getPaintingName());
            viewHolder.authorName.setText(painting.getAuthorName());
            viewHolder.paintingImage.setImageResource(painting.getPaintingImage());
        }

        // Set correct heart icon initially
        if (painting.isFavorite) {
            viewHolder.buttonFavorite.setImageResource(R.drawable.filled);
        } else {
            viewHolder.buttonFavorite.setImageResource(R.drawable.sharp);
        }

        // --- Favorite toggle logic ---
        viewHolder.buttonFavorite.setOnClickListener(v -> {
            painting.isFavorite = !painting.isFavorite;

            if (painting.isFavorite) {
                viewHolder.buttonFavorite.setImageResource(R.drawable.filled);
            } else {
                viewHolder.buttonFavorite.setImageResource(R.drawable.sharp);
            }
        });

        return convertView;
    }
}
