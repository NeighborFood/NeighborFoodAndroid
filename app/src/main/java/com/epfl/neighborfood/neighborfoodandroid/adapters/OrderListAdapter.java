package com.epfl.neighborfood.neighborfoodandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.epfl.neighborfood.neighborfoodandroid.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Adapter class for a list of ongoing orders
 */
public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {
    private static final String TAG = "OrderListAdapter";

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> imageIds = new ArrayList<>();
    private Context context;

    /**
     *
     * @param context context for order
     * @param names names of orders
     * @param ids ids of orders
     */
    public OrderListAdapter(Context context, ArrayList<String> names, ArrayList<Integer> ids) {
        this.names = names;
        imageIds = ids;
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
