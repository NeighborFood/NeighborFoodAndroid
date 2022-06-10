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
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.VendorOrdersViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Adapter class for vendor's orders made
 */
public class VendorOrderListAdapter extends RecyclerView.Adapter<VendorOrderListAdapter.ViewHolder> {
    private static final String TAG = "OrderListAdapter";

    private final List<Order> orderList;
    private final VendorOrdersViewModel viewModel;
    private final Context context;

    /**
     * @param context
     * @param orderList list of orders made by vendor
     * @param viewModel viewModel for vendor orders
     */
    public VendorOrderListAdapter(Context context, List<Order> orderList, VendorOrdersViewModel viewModel) {
        this.orderList = orderList;
        this.viewModel = viewModel;
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
        viewModel.getMealById(orderList.get(position).getMealId()).addOnSuccessListener(meal -> {
            holder.name.setText(meal.getName());
            holder.status.setText(orderList.get(position).orderShortStatusDes());
            Picasso.get().load(meal.getImageUri()).into(holder.image);
        });
    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final CircleImageView image;
        final TextView name;
        final TextView status;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.orderStatus);
        }
    }
}
