package com.store.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
    Button tree;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);

       getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        editTextEmail = findViewById(R.id.studentname);
        editText = findViewById(R.id.studentid);
        login = (MaterialButton) findViewById(R.id.login);
        tree = findViewById(R.id.tree);

        checkBox();

        tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNextActivity();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                editor.putString("name","true");
                editor.apply();



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

    private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString("name","");
        if(check.equals("true")){
            Toast.makeText(MainActivity.this, "auto login successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
            finish();
        }
    }

    private void openNextActivity(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}

