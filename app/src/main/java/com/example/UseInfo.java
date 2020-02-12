package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class UseInfo extends AppCompatActivity {

    ImageButton ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useinfo);

        ba = (ImageButton) findViewById(R.id.back);
        ba.setOnClickListener(new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(UseInfo.this, Info.class);
                        startActivity(i);
                        finish();
                    }
                }

        );
    }
}
