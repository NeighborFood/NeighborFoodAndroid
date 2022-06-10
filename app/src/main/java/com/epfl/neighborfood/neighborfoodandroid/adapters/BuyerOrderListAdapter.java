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
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.util.Triplet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Adapter class for past orders list
 */
public class BuyerOrderListAdapter extends ArrayAdapter<Triplet<Order, Meal, User>> {
    /**
     * @param context        context for pastOrders
     * @param orderArrayList List of past orders
     */
    public BuyerOrderListAdapter(Context context, ArrayList<Triplet<Order, Meal, User>> orderArrayList) {
        super(context, R.layout.list_item_meal, orderArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Triplet<Order, Meal, User> detailedOrder = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_order, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.order_image);
        TextView mealName = convertView.findViewById(R.id.order_title);
        TextView mealShortDes = convertView.findViewById(R.id.order_des);
        Picasso.get().load(detailedOrder.getSecond().getImageUri()).into(imageView);
        mealName.setText(new StringBuilder().append(detailedOrder.getSecond().getName()).append(" By ").append(detailedOrder.getThird().getFirstName()).toString());
        mealShortDes.setText(detailedOrder.getFirst().orderStatusDes());

        return convertView;
    }
}