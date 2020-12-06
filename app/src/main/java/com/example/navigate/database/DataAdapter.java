package com.example.navigate.database;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.navigate.MainActivity;
import com.example.navigate.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.LinkedList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolder> {
    private Context mContext;
    private Cursor mCursor;


    public DataAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }
    public class DataHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView ingText;
        public TextView priceText;

        public DataHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.text_food_title);
            ingText = itemView.findViewById(R.id.text_food_ing);
            priceText = itemView.findViewById(R.id.text_food_price);
        }
    }
    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recycler_food_show, parent, false);
        return new DataHolder(view);
    }




    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        String name = mCursor.getString(mCursor.getColumnIndex(DataBaseInfo.DataEntry.COL_2));
        String ingredient = mCursor.getString(mCursor.getColumnIndex(DataBaseInfo.DataEntry.COL_3));
        String price = mCursor.getString(mCursor.getColumnIndex(DataBaseInfo.DataEntry.COL_5)) + "$";
        holder.nameText.setText(name);
        holder.ingText.setText(ingredient);
        holder.priceText.setText(price);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(mContext, "Recycle Click " + holder.nameText.getText(), Toast.LENGTH_SHORT).show();
                Snackbar.make(v, holder.nameText.getText() + " Ordered", Snackbar.LENGTH_LONG).show();
                MainActivity.list.add(holder.nameText.getText().toString());
                MainActivity.price.add(mCursor.getInt(mCursor.getColumnIndex(DataBaseInfo.DataEntry.COL_5)));
            }
        });


    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }



}
