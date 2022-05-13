package com.epfl.neighborfood.neighborfoodandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;

import java.util.ArrayList;

public class MealListAdapter extends ArrayAdapter {

    public MealListAdapter(Context context, ArrayList<Meal> mealArrayList) {
        super(context, R.layout.list_item_meal, mealArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Meal meal = (Meal) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_meal, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.meal_pic);
        TextView mealName = convertView.findViewById(R.id.meal_name);
        TextView mealShortDes = convertView.findViewById(R.id.meal_short_des);

        imageView.setImageResource(meal.getImageId());
        mealName.setText(meal.getName());
        mealShortDes.setText(meal.getShortDescription());

        return convertView;
    }

}