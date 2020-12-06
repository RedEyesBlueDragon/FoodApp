package com.example.navigate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.navigate.database.DataBaseInfo.*;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "FoodTable.db";
    public static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_FOOD_TABLE = "CREATE TABLE " +
                DataEntry.Table_NAME + " (" +
                DataEntry.COL_1 + " TEXT NOT NULL, " +
                DataEntry.COL_2 + " TEXT NOT NULL, " +
                DataEntry.COL_3 + " TEXT , " +
                DataEntry.COL_4 + " TEXT NOT NULL, " +
                DataEntry.COL_5 + " INTEGER NOT NULL, " +
                "PRIMARY KEY (" + DataEntry.COL_1 + ", " + DataEntry.COL_2 + ") );";
        db.execSQL(SQL_CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DataEntry.Table_NAME);
        onCreate(db);
    }



    public boolean insertData(String restaurant_name, String food_name, String ingredients, String category, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataEntry.COL_1, restaurant_name);
        contentValues.put(DataEntry.COL_2, food_name);
        contentValues.put(DataEntry.COL_3, ingredients);
        contentValues.put(DataEntry.COL_4, category);
        contentValues.put(DataEntry.COL_5, price);

        long result = db.insert(DataEntry.Table_NAME, null , contentValues);

        if (result == -1){
            return false;  }
        else{
            return  true;  }

    }


    public boolean deleteData(String restaurant_name, String food_name){
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClauseWithoutTheWhere = "Restaurant =? AND FoodName =?" ;
        String[] params =new String[]{restaurant_name,food_name};
        boolean succeeded = db.delete(DataEntry.Table_NAME,whereClauseWithoutTheWhere,params)>0;
        return succeeded;
    }



    public static Cursor getAllData(SQLiteDatabase db){
        Cursor res = db.rawQuery("Select * from " + DataEntry.Table_NAME, null);
        return res;
    }

    public static Cursor getCategoryData(SQLiteDatabase db, String category){
        //Cursor res = db.rawQuery("Select * from " + DataEntry.Table_NAME + " WHERE " + DataEntry.COL_4 + " = ' " + category + "'", null);
        Cursor res = db.rawQuery("select * " +
                "FROM " + DataEntry.Table_NAME + " WHERE Category like '"+ category +"%' order by " + DataEntry.COL_4 + " DESC ;", null);
        return res;
    }


    public static Cursor getRestaurantData(SQLiteDatabase db, String restaurant){
        Cursor res = db.rawQuery("select * " +
                "FROM " + DataEntry.Table_NAME + " WHERE Restaurant like '"+ restaurant +"%' order by " + DataEntry.COL_4 + " DESC ;", null);
        return res;
    }


}
