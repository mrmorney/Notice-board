package com.store.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.signal.DialogPropertiesSignal;
import org.imaginativeworld.oopsnointernet.dialogs.signal.NoInternetDialogSignal;
import org.imaginativeworld.oopsnointernet.snackbars.fire.NoInternetSnackbarFire;
import org.imaginativeworld.oopsnointernet.snackbars.fire.SnackbarPropertiesFire;

public class Main2Activity extends AppCompatActivity {
RelativeLayout rr;
    public static final String SHARED_PREFS = "sharedPrefs";


    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rr=findViewById(R.id.rr);


        logout = findViewById(R.id.logout);

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
                NoInternetDialog();
            }
        });
    }

    private void NoInternetDialog() {
        Object binding;
        NoInternetSnackbarFire.Builder builder = new NoInternetSnackbarFire.Builder(
                rr,
                getLifecycle()
        );

        SnackbarPropertiesFire properties = builder.getSnackbarProperties();

        properties.setConnectionCallback(new ConnectionCallback() { // Optional
            @Override
            public void hasActiveConnection(boolean hasActiveConnection) {
                // ...
            }
        });

        properties.setDuration(Snackbar.LENGTH_INDEFINITE); // Optional
        properties.setNoInternetConnectionMessage("No active Internet connection!"); // Optional
        properties.setOnAirplaneModeMessage("You have turned on the airplane mode!"); // Optional
        properties.setSnackbarActionText("Settings"); // Optional
        properties.setShowActionToDismiss(false); // Optional
        properties.setSnackbarDismissActionText("OK"); // Optional

        builder.build();
    }
}