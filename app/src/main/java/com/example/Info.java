package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Info extends AppCompatActivity {

    ImageButton ba,hom,plu,peo;
    Button da,term;

    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        ba = (ImageButton) findViewById(R.id.back);
        hom = (ImageButton) findViewById(R.id.home);
        plu = (ImageButton) findViewById(R.id.plus);
        peo = (ImageButton) findViewById(R.id.peple);
        da = (Button) findViewById(R.id.data);
        term = (Button) findViewById(R.id.terms);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.back :
                        Intent i = new Intent(getApplicationContext(), MyActivity.class);
                        startActivity(i);
                        finish();
                        break;

                    case R.id.home :
                        Intent i2 = new Intent(getApplicationContext(), SNS_Main.class);
                        startActivity(i2);
                        finish();
                        break;

                    case R.id.plus :
                        Intent i3 = new Intent(getApplicationContext(), PostingUpload.class);
                        startActivity(i3);
                        finish();
                        break;

                    case R.id.peple :
                        Intent i4 = new Intent(getApplicationContext(), MyActivity.class);
                        startActivity(i4);
                        finish();
                        break;

                    case R.id.data :
                        Intent i5 = new Intent(getApplicationContext(), DataPolicy.class);
                        startActivity(i5);
                        finish();
                        break;

                    case R.id.terms :
                        Intent i6 = new Intent(getApplicationContext(), UseInfo.class);
                        startActivity(i6);
                        finish();
                        break;

                }

            }
        };
        ba.setOnClickListener(cl);
        hom.setOnClickListener(cl);
        plu.setOnClickListener(cl);
        peo.setOnClickListener(cl);
        da.setOnClickListener(cl);
        term.setOnClickListener(cl);
    }
}
