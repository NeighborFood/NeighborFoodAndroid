package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.BuyerOrdersActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.PlacePinActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.ProfileEditingActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;

public class AccountFragment extends Fragment {
    public AccountFragment(){
        super(R.layout.fragment_account);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getView().findViewById(R.id.profilePageButton).setOnClickListener((View v)->{
            Intent intent = new Intent(getActivity(),ProfileEditingActivity.class);
            startActivity(intent);
        });
        getView().findViewById(R.id.logoutButton).setOnClickListener((View v) -> {
            Intent intent = new Intent(getActivity(), SignUpActivity.class);
            startActivity(intent);
        });
        getView().findViewById(R.id.PastOrdersButton).setOnClickListener((View v) -> {
            Intent intent = new Intent(getActivity(), BuyerOrdersActivity.class);
            startActivity(intent);
        });

    }

}
