package com.example.navigate.ui;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.navigate.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.navigate.MainActivity;
import com.example.navigate.R;
import com.example.navigate.database.DataAdapter;
import com.example.navigate.database.DataBaseHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MealFragment extends Fragment {

    private MealViewModel mViewModel;
    private SQLiteDatabase myDB;
    private DataAdapter mAdapter;



    public static MealFragment newInstance() {
        return new MealFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
        myDB = dbHelper.getWritableDatabase();

        View rootView = inflater.inflate(R.layout.fragment_show__data, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new DataAdapter(getActivity() ,DataBaseHelper.getCategoryData(myDB,"Meal"));
        recyclerView.setAdapter(mAdapter);

        ImageView back_button = (ImageView) rootView.findViewById(R.id.back_image);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(getActivity(), MainActivity.class);
                startActivity(intent_home);
            }
        });

        return rootView;




    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MealViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}