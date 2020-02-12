package com.example.porori;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ProfileEdit extends Activity {

    ImageButton clo,nex,hom,plu,peo;
    Button del;

    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileedit);

        clo = (ImageButton) findViewById(R.id.close);
        nex = (ImageButton) findViewById(R.id.next);
        hom = (ImageButton) findViewById(R.id.home);
        plu = (ImageButton) findViewById(R.id.plus);
        peo = (ImageButton) findViewById(R.id.peple);
        del = (Button) findViewById(R.id.delete);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.close :
                        break;

                    case R.id.next :
                        break;

                    case R.id.home :
                        Intent i2 = new Intent(getApplicationContext(), SNS_Main.class);
                        startActivity(i2);
                        break;

                    case R.id.plus :
                        break;

                    case R.id.peple :
                        break;

                    case R.id.delete :
                        break;
                }

            }
        };
        clo.setOnClickListener(cl);
        nex.setOnClickListener(cl);
        hom.setOnClickListener(cl);
        plu.setOnClickListener(cl);
        peo.setOnClickListener(cl);
        del.setOnClickListener(cl);

    }
}
