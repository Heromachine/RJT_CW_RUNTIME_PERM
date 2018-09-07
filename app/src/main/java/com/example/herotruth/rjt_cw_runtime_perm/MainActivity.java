package com.example.herotruth.rjt_cw_runtime_perm;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {


                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP){
                    Toast.makeText(MainActivity.this, "Version " + android.os.Build.VERSION.SDK_INT + "> "+android.os.Build.VERSION_CODES.LOLLIPOP, Toast.LENGTH_LONG).show();


                    Toast.makeText(MainActivity.this, "Permission Granted Due To Old Verison", Toast.LENGTH_LONG).show();
                } else
                    {
                        Toast.makeText(MainActivity.this, "Your Version " + android.os.Build.VERSION.SDK_INT + "> "+android.os.Build.VERSION_CODES.LOLLIPOP, Toast.LENGTH_LONG).show();

                        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    }
                    else
                        requestSMSPermission();
                }
            }
        });

    }

    private void requestSMSPermission() {



        if(ActivityCompat.shouldShowRequestPermissionRationale
                (MainActivity.this, Manifest.permission.READ_SMS)) {


            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Permission");
            builder.setMessage("If you dont accept permission you wont receive SMS for this application ");
            builder.setPositiveButton("Set Permission", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions
                            (MainActivity.this, new String[]{Manifest.permission.READ_SMS}, 1234);

                }
            });



            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.cancel();

                }
            });

            builder.show();

        }
        else
        {
            //SINCE USER HAS ACCEPTED THE PERMISSIONS IT IS TIME TO
            ActivityCompat.requestPermissions
                    (MainActivity.this, new String[]{Manifest.permission.READ_SMS}, 1234);
        }
    }
}
