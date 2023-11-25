package com.store.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Admin extends AppCompatActivity {
    Button logins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        logins = findViewById(R.id.logins);

        logins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNextActivity();
            }
        });
    }

    private void openNextActivity(){
        Intent intent = new Intent(this, AdminAnnounPage.class);
        startActivity(intent);
    }

}