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
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.PickupLocation;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealListViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Adapter class for meals list
 */
public class MealListAdapter extends ArrayAdapter<Order> {
    private final MealListViewModel viewModel;

    /**
     * @param context
     * @param mealArrayList list of orders that contain the meals
     * @param viewModel     viewModel for meals list
     */
    public MealListAdapter(Context context, ArrayList<Order> mealArrayList, MealListViewModel viewModel) {
        super(context, R.layout.list_item_meal, mealArrayList);
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Order order = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_meal, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.meal_pic);
        TextView mealName = convertView.findViewById(R.id.meal_name);
        TextView mealShortDes = convertView.findViewById(R.id.meal_short_des);
        TextView price = convertView.findViewById(R.id.price);
        TextView distanceText = convertView.findViewById(R.id.distance);
        viewModel.getMealById(order.getMealId()).addOnSuccessListener(meal -> {
            mealName.setText(meal.getName());
            Picasso.get().load(meal.getImageUri()).into(imageView);
            mealShortDes.setText(meal.getDescription());
            PickupLocation pickupLocation = viewModel.getUserLocation().getValue();
            if (pickupLocation != null) {
                int distance = (int) PickupLocation.distanceBetweenLocations(pickupLocation, order.getLocation());
                distanceText.setText(getContext().getResources().getString(R.string.distance_tag, String.valueOf(distance)));
            }
            price.setText(getContext().getResources().getString(R.string.price_tag, order.getPrice()));
        });
        return convertView;
    }
}
