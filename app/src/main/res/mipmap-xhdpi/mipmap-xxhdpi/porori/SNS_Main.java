package com.example.porori;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SNS_Main extends AppCompatActivity {

    ImageView lg,un,ba;
    ImageButton ca,lo,pr,pr2,pr3,me,st,co,pl,ho;
    EditText te;
    TextView te1,te2,te3,te4,to,he,no;
    View di,di2;
    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sns_main);

        lg = (ImageView) findViewById(R.id.logobar);
        un = (ImageView) findViewById(R.id.underbar);
        ba = (ImageView) findViewById(R.id.back);

        ca = (ImageButton) findViewById(R.id.camera);
        lo = (ImageButton) findViewById(R.id.location);
        pr = (ImageButton) findViewById(R.id.profile);
        pr2 = (ImageButton) findViewById(R.id.profile2);
        pr3 = (ImageButton) findViewById(R.id.profile3);
        me = (ImageButton) findViewById(R.id.menu);
        st = (ImageButton) findViewById(R.id.star);
        co = (ImageButton) findViewById(R.id.comu);
        pl = (ImageButton) findViewById(R.id.plus);
        ho = (ImageButton) findViewById(R.id.home);

        te = (EditText) findViewById(R.id.text);

        te1 = (TextView) findViewById(R.id.text1);
        te2 = (TextView) findViewById(R.id.text2);
        te3 = (TextView) findViewById(R.id.text3);
        te4 = (TextView) findViewById(R.id.text4);
        to = (TextView) findViewById(R.id.today);
        he = (TextView) findViewById(R.id.here);
        no = (TextView) findViewById(R.id.now);

        di = (View) findViewById(R.id.divider);
        di2 = (View) findViewById(R.id.divider2);



        lg.setImageResource(R.drawable.icon_theme2);
        ba.setImageResource(R.drawable.icon_background);


        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        ca.setOnClickListener(cl);
        lo.setOnClickListener(cl);
    }
}
