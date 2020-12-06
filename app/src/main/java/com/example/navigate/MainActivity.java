package com.example.navigate;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private int shortAnimationDuration;

    public static ArrayList<String> list  = new ArrayList<String>();

    public static ArrayList<Integer> price = new ArrayList<Integer>();

    public static int totalPrice = 0;

    public void addList(String item)
    {
        list.add(item);
     }


     private static int SPLASH_TIME_OUT = 4000;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        getSupportActionBar().setHomeButtonEnabled(true);




        Button close = findViewById(R.id.button2);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View fr = findViewById(R.id.frameLayout);
                View sc = findViewById(R.id.sc);

                slideToBottom(fr);
                fr.setVisibility(fr.GONE);
                sc.setVisibility(sc.GONE);

            }
        });


        Button goPay = findViewById(R.id.button_pay);
        goPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalPrice == 0){
                    Toast.makeText(getApplicationContext(), "There is not any order to pay", Toast.LENGTH_SHORT).show();
                }else{
                Intent Intent_go = new Intent(MainActivity.this, Payment.class);
                startActivity(Intent_go);
                overridePendingTransition( R.anim.left_slide_in,R.anim.left_slide_out );
                }
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Snackbar.make(view, "Text box", Snackbar.LENGTH_LONG)
            //            .setAction("Action", null).show();
                View fr = findViewById(R.id.frameLayout);
                View sc = findViewById(R.id.sc);
                if (fr.getVisibility() != VISIBLE) {
                    fr.setVisibility(VISIBLE);
                    sc.setVisibility(VISIBLE);
                    slideToTop(fr);

                    TextView textView = findViewById(R.id.textView3);
                    TextView priceText = findViewById(R.id.textView4);
                    TextView total_priceText = findViewById(R.id.total_price);

                    textView.setText("");
                    priceText.setText("");
                    total_priceText.setText("Total  0$");
                    totalPrice = 0;

                    for (int i=0; i < list.size(); i++){
                        textView.append(list.get(i));
                        textView.append("\n");
                        priceText.append(String.format("%d",price.get(i)));
                        priceText.append("$");
                        priceText.append("\n");
                        Log.i("suma", String.format("%d",price.get(i)));
                        totalPrice = totalPrice + price.get(i);
                    }

                    total_priceText.setText("Total  " +String.format("%d",totalPrice)  + "$");
                    Log.i("sum", String.valueOf(totalPrice));

                }
                else{
                    slideToBottom(fr);
                    fr.setVisibility(fr.GONE);
                    sc.setVisibility(sc.GONE);

                }
            }

        });




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.desertFragment, R.id.nav_slideshow, R.id.mealFragment )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);






    }




    public void slideToBottom(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, 0,0, view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    public void slideToTop(View view){
        TranslateAnimation animate = new TranslateAnimation(0,0,view.getHeight(),0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Intent intent_setting = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent_setting);
                overridePendingTransition( R.anim.slide_left, R.anim.slide_right );
                return true;

            case R.id.about:
                Intent intent_about = new Intent(MainActivity.this, About.class);
                startActivity(intent_about);
                overridePendingTransition( R.anim.slide_left, R.anim.slide_right );
                return true;

            case R.id.camera:
                Intent intent_camera = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent_camera);
                overridePendingTransition( R.anim.slide_in_up,R.anim.slide_out_up );
                return true;

            case R.id.home_button:
                Intent intent_home = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent_home);
                overridePendingTransition( R.anim.nav_default_pop_enter_anim,R.anim.nav_default_exit_anim );
                return true;

            case R.id.some_thing:
                list.clear();
                price.clear();
                totalPrice = 0;
                Toast.makeText(getApplicationContext(),"Shop List Cleared",Toast.LENGTH_LONG).show();
                return true;

            case R.id.log_out:
                list.clear();
                price.clear();
                totalPrice = 0;
                Intent intent_login = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent_login);
                overridePendingTransition( R.anim.down_in,R.anim.down_out );
                return true;

        }


        return super.onOptionsItemSelected(item);
    }




}