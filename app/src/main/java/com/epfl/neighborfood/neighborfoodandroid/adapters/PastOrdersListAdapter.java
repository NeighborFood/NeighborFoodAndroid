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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Adapter class for past orders list
 */
public class PastOrdersListAdapter extends ArrayAdapter {
    /**
     *
     * @param context context for pastOrders
     * @param orderArrayList List of past orders
     */
    public PastOrdersListAdapter(Context context, ArrayList<Order> orderArrayList) {
        super(context, R.layout.list_item_meal, orderArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Order order = (Order) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_order, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.order_image);
        TextView mealName = convertView.findViewById(R.id.order_title);
        TextView mealShortDes = convertView.findViewById(R.id.order_des);
        Picasso.get().load(order.getMeal().getImageUri()).into(imageView);
        mealName.setText(order.getMeal().getName() + " By " + order.getMealVendor());
        mealShortDes.setText(order.orderStatusDes());

        return convertView;
    }
}