package com.store.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText editText,editTextEmail;

    MaterialButton login;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextEmail = findViewById(R.id.studentname);
        editText = findViewById(R.id.studentid);
        login = (MaterialButton) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);


                 String studentname, studentid;
                 studentname = String .valueOf(editTextEmail.getText());

                 studentid = String.valueOf(editText.getText());
                 int desired = 8;




                 if(TextUtils.isEmpty(studentname)){
                     Toast.makeText(MainActivity.this, "Enter your name", Toast.LENGTH_SHORT).show();
                     return;
                 }

                if(TextUtils.isEmpty(studentid)){
                    Toast.makeText(MainActivity.this, "Enter correct id", Toast.LENGTH_SHORT).show();


                  }else if (TextUtils.isDigitsOnly(studentid)){
                    Toast.makeText(MainActivity.this, "invalid id", Toast.LENGTH_SHORT).show();
                   return;

                }if (studentid.length() != desired){
                    Toast.makeText(MainActivity.this, "invalid id", Toast.LENGTH_SHORT).show();
                    return;

                }if (!studentid.matches(".*[A-Z0-9].*")) {

                    Toast.makeText(MainActivity.this, "invalid credentail", Toast.LENGTH_SHORT).show();
                    return;
                }


                startActivity(intent);

                finish();
            }
        });


    }

}

