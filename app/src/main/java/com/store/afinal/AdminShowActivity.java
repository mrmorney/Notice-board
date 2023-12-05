package com.store.afinal;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AdminShowActivity extends AppCompatActivity implements AdminMyAdapter.OnItemClickListener {
    BroadcastReceiver broadcastReceiver;
    private RecyclerView  recyclerView;
    private ArrayList<Model> list;
    private AdminMyAdapter adapter;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("images/");
    private ValueEventListener mDBListener;
    private FirebaseStorage mStorage;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show);
        broadcastReceiver = new ConnectionReceiver();
        registorNetworkBroadcast();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new AdminMyAdapter(this,list);
        recyclerView.setAdapter(adapter);
        mStorage = FirebaseStorage.getInstance();

        mDBListener = root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Model model = dataSnapshot.getValue(Model.class);
                    model.setKey(dataSnapshot.getKey());
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(AdminShowActivity.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void onItemClick(int position) {

    }


    public void onWhatEverclick(int position) {

    }


    public void onDeleteClick(int position) {


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

}