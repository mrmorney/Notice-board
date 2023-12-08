package com.store.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Admin extends AppCompatActivity {
    Button logins;
    EditText name;
    EditText password;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

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

                String names,passwords;
                names = String.valueOf(name.getText());
                passwords = String.valueOf(password.getText());

               if (TextUtils.isEmpty(names)){
                   Toast.makeText(Admin.this,"incorrect username or password",Toast.LENGTH_SHORT).show();
                          return;
               }
               if (TextUtils.isEmpty(passwords)){
                   Toast.makeText(Admin.this, "incorrect username or password", Toast.LENGTH_SHORT).show();
                   return;
               }

               firebaseAuth.signInWithEmailAndPassword(names,passwords)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()){
                                   Toast.makeText(Admin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(Admin.this, AdminAnnounPage.class);
                                   startActivity(intent);
                                   finish();
                               }
                               else {
                                   Toast.makeText(Admin.this, "incorrect username or password", Toast.LENGTH_SHORT).show();
                               }
                           }
                       });


            }
        });
    }

    private void openNextActivity(){
        Intent intent = new Intent(this, AdminAnnounPage.class);
        startActivity(intent);
    }

}