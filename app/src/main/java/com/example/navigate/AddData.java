package com.example.navigate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.navigate.R;
import com.example.navigate.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AddData extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    DataBaseHelper myDB;
    EditText foodName, ing, price, restaurant;
    String food_type, food_name, food_ing, food_price, food_restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        myDB = new DataBaseHelper(this);

        foodName =(EditText) findViewById(R.id.editTextTextFoodName);
        ing = (EditText) findViewById(R.id.editTextTextIngName);
        price = (EditText) findViewById(R.id.editTextFoodPrice);
        //restaurant = (EditText) findViewById(R.id.editTextTextRestName);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner_category);
        final Spinner spinner2 = (Spinner) findViewById(R.id.editTextTextRestName);
        final Button button = (Button) findViewById(R.id.add_button);
        final Button delete = (Button) findViewById(R.id.delete_button);

        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Meal");
        categories.add("Dessert");
        categories.add("Drink");
        categories.add("Salad");

        List<String> restaurants = new ArrayList<String>();
        restaurants.add("Dinner in the Sky");
        restaurants.add("The Pasta House");
        restaurants.add("Red Bakery House");
        restaurants.add("Flotsam and Jetsam");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, restaurants);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!foodName.getText().toString().equals("") && !price.getText().toString().equals("") ) {
                    food_type = spinner.getSelectedItem().toString();
                    food_name = foodName.getText().toString();
                    food_ing = ing.getText().toString();
                    food_price = price.getText().toString();
                    food_restaurant = spinner2.getSelectedItem().toString();

                    boolean check = myDB.insertData(food_restaurant ,food_name, food_ing, food_type, food_price);

                    if (check) {
                        Toast.makeText(AddData.this, "Food added", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AddData.this, "Food did not add", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(AddData.this, "Food did not add", Toast.LENGTH_LONG).show();
                }

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food_restaurant = spinner2.getSelectedItem().toString();
                food_name = foodName.getText().toString();

                boolean check = myDB.deleteData(food_restaurant, food_name);

                if (check) {
                    Toast.makeText(AddData.this, "Food deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddData.this, "Food did not delete", Toast.LENGTH_LONG).show();
                }
            }
        });





    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}