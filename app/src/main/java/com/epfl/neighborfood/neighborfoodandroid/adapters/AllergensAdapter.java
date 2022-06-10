package com.epfl.neighborfood.neighborfoodandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.Allergen;

import java.util.List;

/**
 * Adapter class for allergens list
 */
public class AllergensAdapter extends ArrayAdapter<Allergen> {

    /**
     * @param context           context for pastOrders
     * @param allergenArrayList List of chosen allergens
     */
    public AllergensAdapter(Context context, List<Allergen> allergenArrayList) {
        super(context, R.layout.list_item_allergen, allergenArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Allergen allergen = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_allergen, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.allergenPic);

        imageView.setImageResource(allergen.getId());

        return convertView;
    }
}