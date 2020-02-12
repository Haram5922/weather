package com.example.porori;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MyActivity extends Activity {

    ImageButton cam,men,hom,plu,peo;
    Button del;

    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);

        cam = (ImageButton) findViewById(R.id.camera);
        men = (ImageButton) findViewById(R.id.menubar);
        hom = (ImageButton) findViewById(R.id.home);
        plu = (ImageButton) findViewById(R.id.plus);
        peo = (ImageButton) findViewById(R.id.peple);
        del = (Button) findViewById(R.id.delete);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.camera :
                        break;
                    case R.id.menubar :
                        break;
                    case R.id.home :
                        Intent i = new Intent(getApplicationContext(), SNS_Main.class);
                        startActivity(i);
                        break;
                    case R.id.plus :
                        break;
                    case R.id.peple :
                        break;

                }

            }
        };

        cam.setOnClickListener(cl);
        men.setOnClickListener(cl);
        hom.setOnClickListener(cl);
        plu.setOnClickListener(cl);
        peo.setOnClickListener(cl);
        del.setOnClickListener(cl);

    }
}
