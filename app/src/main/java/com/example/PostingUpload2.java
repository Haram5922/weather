package com.example;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class PostingUpload2 extends AppCompatActivity {

    ImageButton pr, co;
    TextView te2;
    ImageView lo;
    EditText wr;
    View.OnClickListener cl;
    Intent i;
    //private final int m_nMaxLengthOfText = 1000;
    PostingUpload pt = new PostingUpload();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postingupload2);

        pr = (ImageButton)findViewById(R.id.previous);
        co = (ImageButton)findViewById(R.id.complete);
        te2 = (TextView)findViewById(R.id.text2); //장소 DB받아오기
        lo = (ImageView)findViewById(R.id.load);
        wr = (EditText)findViewById(R.id.write);

        final Intent intent = getIntent();
        final String a = intent.getStringExtra("load");
        //wr.setFilters(new InputFilter[] { new InputFilter.LengthFilter(m_nMaxLengthOfText) });


        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.previous :
                        i = new Intent(PostingUpload2.this,PostingUpload.class);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.complete :

                        //업로드 후에 (성공하면 업로드 완료 토스트 띄우기)
                        i = new Intent(PostingUpload2.this,SNS_Main.class);
                        startActivity(i);
                        finish();
                        break;

                }

            }
        };
        pr.setOnClickListener(cl);
        co.setOnClickListener(cl);
    }
}