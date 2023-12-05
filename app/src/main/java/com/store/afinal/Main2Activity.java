package com.store.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class Main2Activity extends AppCompatActivity {
    BroadcastReceiver broadcastReceiver;
    public static final String SHARED_PREFS = "sharedPrefs";



    Button logout;
    Button butto;
    ListView listView;
    Context context;
    ImageView no;
    int count = 0;
    Handler handler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        broadcastReceiver = new ConnectionReceiver();
        registorNetworkBroadcast();

        logout = findViewById(R.id.logout);
        listView = findViewById(R.id.listview);
        no = (ImageView) findViewById(R.id.no);
        handler = new Handler();
        butto = findViewById(R.id.butto);



        butto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNextActivity();
            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name","");
                editor.apply();


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this ,R.layout.list_item , list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("message");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list.clear();
                for (DataSnapshot snapshot : datasnapshot.getChildren()){
                    list.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (isConnected()){
            no.setVisibility(View.GONE);
            Toast.makeText(Main2Activity.this, "",Toast.LENGTH_SHORT).show();
        }
        else  {
            no.setVisibility(View.VISIBLE);
            Toast.makeText(Main2Activity.this, "No internet access",Toast.LENGTH_SHORT).show();
        }



    }

    private void no() {

    }
    /*protected void onDestroy(){
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }*/



    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    private void openNextActivity(){
        Intent intent = new Intent(this, AdminShowActivity.class);
        startActivity(intent);
    }
    protected void registorNetworkBroadcast(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver,new IntentFilter((ConnectivityManager.CONNECTIVITY_ACTION)));
        }
    }
    protected void unregistorNetwork(){
        try {
            unregisterReceiver(broadcastReceiver);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregistorNetwork();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit")
                .setCancelable(false)
                .setNegativeButton(android.R.string.no,null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Main2Activity.super.onBackPressed();
                    }
                }).create().show();

    }
}