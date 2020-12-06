package com.example.navigate.database;

import android.provider.BaseColumns;

public class DataBaseInfo {

    private DataBaseInfo(){

    }

    public static final class DataEntry implements BaseColumns {

        public static final String Table_NAME = "food_table";
        public static final String COL_1 = "Restaurant";
        public static final String COL_2 = "FoodName";
        public static final String COL_3 = "Ingredients";
        public static final String COL_4 = "Category";
        public static final String COL_5 = "Price";
    }
}
