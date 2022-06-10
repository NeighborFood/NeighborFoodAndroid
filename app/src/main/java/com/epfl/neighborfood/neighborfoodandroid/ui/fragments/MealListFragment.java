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
    private PopupsortingdialogBinding dialogBinding;
    private MealListViewModel viewModel;
    private MealListAdapter listAdapter;
    private final ArrayList<Order> orderMealList = new ArrayList<Order>();
    private AlertDialog sortingDialog;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMealListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ((NeighborFoodApplication)getActivity().getApplication()).getAppContainer().getLocationService().requestLocationPermission(getActivity());
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
                dialogBinding.radioGroup.check((dialogBinding.radioGroup.getChildAt(viewModel.getOrderingIndex())).getId());
                sortingDialog.show();
            }
        });
    }
    public void createNewSortingDialog(){
        dialogBinding = PopupsortingdialogBinding.inflate(getLayoutInflater());
        AlertDialog.Builder sortingDialogBuilder = new AlertDialog.Builder(requireContext());
        sortingDialogBuilder.setView(dialogBinding.getRoot());
        sortingDialog = sortingDialogBuilder.create();
        dialogBinding.radioGroup.check((dialogBinding.radioGroup.getChildAt(viewModel.getOrderingIndex())).getId());
        dialogBinding.saveButtonPopup.setOnClickListener(bv->{
            int index = dialogBinding.radioGroup.indexOfChild(sortingDialog.findViewById(dialogBinding.radioGroup.getCheckedRadioButtonId()));
            viewModel.setOrdering(index);
            sortingDialog.dismiss();
        });
        dialogBinding.cancelButtonPopup.setOnClickListener(bv->{
            sortingDialog.dismiss();
        });

    }


}
