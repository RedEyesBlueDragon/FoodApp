package com.example.navigate.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.navigate.MainActivity;
import com.example.navigate.R;
import com.example.navigate.database.DataAdapter;
import com.example.navigate.database.DataBaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Show_Data#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Show_Data extends Fragment {

    private SQLiteDatabase myDB;
    private DataAdapter mAdapter;

    private TextView titleRest;
    private TextView titleData;
    private ImageView back_button;

    public static String restaurant_name_data;


    public Show_Data() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Show_Data newInstance(String param1, String param2) {
        Show_Data fragment = new Show_Data();
        Bundle args = new Bundle();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
        myDB = dbHelper.getWritableDatabase();


        View rootView = inflater.inflate(R.layout.fragment_show__data, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        titleRest = (TextView) rootView.findViewById(R.id.RestTitle);
        titleData = (TextView) rootView.findViewById(R.id.fragment_data_show_title);
        titleData.setText(restaurant_name_data);
        back_button = (ImageView) rootView.findViewById(R.id.back_image);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(getActivity(), MainActivity.class);
                startActivity(intent_home);
            }
        });




        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new DataAdapter(getActivity() ,DataBaseHelper.getRestaurantData(myDB, restaurant_name_data));
        recyclerView.setAdapter(mAdapter);

        CardView card = rootView.findViewById(R.id.rec_cell);





        return rootView;
    }


    @Override
    public void onDestroy() {
        titleData.setText("");

        super.onDestroy();
    }
}