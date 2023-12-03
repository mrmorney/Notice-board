package com.store.afinal;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionBarPolicy;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;


public class AdminAnnounPage extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";



    Button logout;
    Button butto;
    ListView listView;
    Context context;
    ImageView no;
    int count = 0;
    Handler handler;
    Button add;
    EditText edit;
    int id = 0;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_announ_page);



        logout = findViewById(R.id.logout);
        listView = findViewById(R.id.listview);
        no = (ImageView) findViewById(R.id.no);
        handler = new Handler();
        butto = findViewById(R.id.butto);
        add = findViewById(R.id.add);
        edit = findViewById(R.id.edit);


        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference("message");




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edit.getText().toString();

                String key = firebaseRef.push().getKey();

                firebaseRef.child(key).setValue(text);

                edit.setText("");

            }
        });

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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final String selectedItem = (String) parent .getItemAtPosition(position);

                new AlertDialog.Builder(AdminAnnounPage.this)
                        .setTitle("Confirm Deletion")
                        .setMessage("Are you sure you want to delete this item")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               FirebaseDatabase.getInstance().getReference().child("message").removeValue();

                               adapter.remove("adapter");
                               adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(android.R.string.no,null)
                        .show();

                return true;
            }
        });




        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("message");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    id = (int) datasnapshot.getChildrenCount();
                }

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
            Toast.makeText(AdminAnnounPage.this, "",Toast.LENGTH_SHORT).show();
        }
        else  {
            no.setVisibility(View.VISIBLE);
            Toast.makeText(AdminAnnounPage.this, "No internet access",Toast.LENGTH_SHORT).show();
        }



    }

    private void no() {

    }
    protected void onDestroy(){
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }



    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    private void openNextActivity(){
        Intent intent = new Intent(this, AdminEventPage.class);
        startActivity(intent);
    }

}