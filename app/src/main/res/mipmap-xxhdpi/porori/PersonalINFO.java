package com.example.porori;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PersonalINFO extends Activity {

    ImageButton ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalinfo);

        ba = (ImageButton) findViewById(R.id.back);
        ba.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), DataPolicy.class);
                        startActivity(i);
                    }
                }

        );
    }
}
