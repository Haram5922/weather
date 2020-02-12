package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;


public class Notice extends AppCompatActivity {

    ImageButton ba,hom,plu,peo;
    Switch sw1,sw2;

    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);

        ba = (ImageButton) findViewById(R.id.back);
        hom = (ImageButton) findViewById(R.id.home);
        plu = (ImageButton) findViewById(R.id.plus);
        peo = (ImageButton) findViewById(R.id.peple);
        sw1 = (Switch) findViewById(R.id.switch1);
        sw2 = (Switch) findViewById(R.id.switch2);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.back :
                        Intent i = new Intent(getApplicationContext(), MyActivity.class);
                        startActivity(i);
                        break;

                    case R.id.home :
                        Intent i2 = new Intent(getApplicationContext(), SNS_Main.class);
                        startActivity(i2);
                        break;

                    case R.id.plus :
                        Intent i3 = new Intent(getApplicationContext(), PostingUpload.class);
                        startActivity(i3);
                        break;

                    case R.id.peple :
                        Intent i4 = new Intent(getApplicationContext(), MyActivity.class);
                        startActivity(i4);
                        break;
                }
            }
        };
        ba.setOnClickListener(cl);
        hom.setOnClickListener(cl);
        plu.setOnClickListener(cl);
        peo.setOnClickListener(cl);

    }
}
