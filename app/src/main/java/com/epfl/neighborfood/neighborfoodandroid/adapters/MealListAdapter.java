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
import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;

import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealListViewModel;
import com.epfl.neighborfood.neighborfoodandroid.util.Pair;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MealListAdapter extends ArrayAdapter {
    private MealListViewModel viewModel;

    public MealListAdapter(Context context, ArrayList<Order> mealArrayList, MealListViewModel viewModel) {
        super(context, R.layout.list_item_meal, mealArrayList);
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Order order = (Order) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_meal, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.meal_pic);
        TextView mealName = convertView.findViewById(R.id.meal_name);
        TextView mealShortDes = convertView.findViewById(R.id.meal_short_des);
        viewModel.getMealById(order.getMealId()).addOnSuccessListener(meal -> {
            mealName.setText(meal.getName());
            Picasso.get().load(meal.getImageUri()).into(imageView);
            mealShortDes.setText(meal.getShortDescription());
        });
        return convertView;
    }
}
