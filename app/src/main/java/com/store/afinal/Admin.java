package com.store.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Admin extends AppCompatActivity {
    Button logins;
    EditText name;
    EditText password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        logins = findViewById(R.id.logins);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);


        logins.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               if (name.getText().toString().equals("happy") && password.getText().toString().equals("happy")){
                   Toast.makeText(Admin.this,"login sucessfully",Toast.LENGTH_SHORT).show();

               }else {
                   Toast.makeText(Admin.this,"incorect password or usermane",Toast.LENGTH_SHORT).show();
                   return;
               }
                openNextActivity();
            }
        });
    }

    private void openNextActivity(){
        Intent intent = new Intent(this, AdminAnnounPage.class);
        startActivity(intent);
    }

}