package com.epfl.neighborfood.neighborfoodandroid.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.Allergen;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import java.util.ArrayList;
import java.util.List;


public class AllergensAdapter extends ArrayAdapter {
    public AllergensAdapter(Context context, List<Allergen> allergenArrayList) {
        super(context, R.layout.list_item_allergen, allergenArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Allergen allergen = (Allergen) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_allergen, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.allergenPic);

        imageView.setImageResource(allergen.getId());

        return convertView;
    }
}