package com.epfl.neighborfood.neighborfoodandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealListViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.VendorOrdersViewModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorOrderListAdapter extends RecyclerView.Adapter<VendorOrderListAdapter.ViewHolder> {
    private static final String TAG = "OrderListAdapter";

    private ArrayList<Order> orderList = new ArrayList<>();
    private VendorOrdersViewModel viewModel;
    private Context context;

    public VendorOrderListAdapter(Context context, ArrayList<Order> orderList, VendorOrdersViewModel viewModel) {
        this.orderList = orderList;
        this viewModel = viewModel;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(names.get(position));
        holder.image.setImageResource(imageIds.get(position));
    }


    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
        }
    }
}
