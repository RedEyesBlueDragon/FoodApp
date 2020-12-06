package com.example.navigate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().setTitle("Payment");


        CardForm cardForm = (CardForm) findViewById(R.id.cardForm);
        TextView paymentAmount = (TextView) findViewById(R.id.payment_amount);
        Button pay = (Button) findViewById(R.id.btn_pay);

        paymentAmount.setText(String.format("%d",MainActivity.totalPrice)  + "$");
        pay.setText("Pay " + String.format("%d",MainActivity.totalPrice)  + "$");

        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                //Your code here!! use card.getXXX() for get any card property
                //for instance card.getName();
                showAlertDialog();

            }
        });
    }

    public void showAlertDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Food App Payment");
        alert.setMessage("Do you want to make this payment?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Payment.this,"Payment has completed", Toast.LENGTH_LONG).show();
                MainActivity.totalPrice = 0;
                MainActivity.list.clear();
                MainActivity.price.clear();

                Intent home_go = new Intent(Payment.this, MainActivity.class);
                startActivity(home_go);
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Payment.this,"Payment has cancelled", Toast.LENGTH_LONG).show();
            }
        });

        alert.create().show();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition( R.anim.slide_left, R.anim.slide_right );

        return true;
    }



}