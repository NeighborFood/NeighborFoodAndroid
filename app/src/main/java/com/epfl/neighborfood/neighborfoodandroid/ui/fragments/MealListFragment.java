package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.MealListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.databinding.FragmentMealListBinding;
import com.epfl.neighborfood.neighborfoodandroid.databinding.PopupsortingdialogBinding;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.MealActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.MealListViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.NeighborFoodViewModelFactory;

import java.util.ArrayList;
import java.util.Objects;


public class MealListFragment extends Fragment {

    private FragmentMealListBinding binding;
    private MealListViewModel viewModel;
    private MealListAdapter listAdapter;
    private final ArrayList<Order> orderMealList = new ArrayList<Order>();
    private AlertDialog sortingDialog;
    private final static int DEFAULT_SORTING_INDEX = 0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMealListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, new NeighborFoodViewModelFactory((NeighborFoodApplication) this.getActivity().getApplication())).get(MealListViewModel.class);
        listAdapter = new MealListAdapter(getActivity(), orderMealList, viewModel);
        viewModel.getAllUnassignedOrders().observe(getViewLifecycleOwner(),orders->{

            listAdapter.clear();
            for (Order order: orders) {
                if (!order.getVendorId().equals(viewModel.getCurrentUser().getId()))
                    listAdapter.add(order);
            }
        });
        binding.mealListView.setAdapter(listAdapter);
        binding.mealListView.setClickable(true);
        binding.mealListView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent i = new Intent(getActivity(), MealActivity.class);

            i.putExtra("orderId", orderMealList.get(position).getOrderId());
            i.putExtra("mealId", orderMealList.get(position).getMealId());
            startActivity(i);
        });
        createNewSortingDialog();
        binding.mealListSortingButton.setOnClickListener(v->{
            if(sortingDialog != null){
                sortingDialog.show();
            }
        });
    }
    public void createNewSortingDialog(){
        PopupsortingdialogBinding binding = PopupsortingdialogBinding.inflate(getLayoutInflater());
        AlertDialog.Builder sortingDialogBuilder = new AlertDialog.Builder(requireContext());
        sortingDialogBuilder.setView(binding.getRoot());
        sortingDialog = sortingDialogBuilder.create();
        binding.radioGroup.check(((RadioButton)binding.radioGroup.getChildAt(DEFAULT_SORTING_INDEX)).getId());
        viewModel.setOrdering(DEFAULT_SORTING_INDEX);
        binding.saveButtonPopup.setOnClickListener(bv->{
            int index = binding.radioGroup.indexOfChild(sortingDialog.findViewById(binding.radioGroup.getCheckedRadioButtonId()));
            viewModel.setOrdering(index);
            sortingDialog.dismiss();
        });
        binding.cancelButtonPopup.setOnClickListener(bv->{
            sortingDialog.dismiss();
        });

    }


}
