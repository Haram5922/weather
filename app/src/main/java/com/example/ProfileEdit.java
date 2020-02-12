package com.example;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class ProfileEdit extends AppCompatActivity {

    ImageView load;
    ImageButton clo,nex,hom,plu,peo;
    Button del;
    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileedit);


        load = (ImageView) findViewById(R.id.load);
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
                        Intent i = new Intent (ProfileEdit.this,MyActivity.class);
                        startActivity(i);
                        finish();
                        break;

                    case R.id.next :
                        Intent i2 = new Intent(ProfileEdit.this, MyActivity.class);
                        startActivity(i2);
                        finish();
                        Toast.makeText(ProfileEdit.this, "저장되었습니다.", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.home :
                        Intent i3 = new Intent(getApplicationContext(), SNS_Main.class);
                        startActivity(i3);
                        finish();
                        break;

                    case R.id.plus :
                        Intent i4 = new Intent(getApplicationContext(), PostingUpload.class);
                        startActivity(i4);
                        finish();
                        break;

                    case R.id.peple :
                        Intent i5 = new Intent(getApplicationContext(), MyActivity.class);
                        startActivity(i5);
                        finish();
                        break;

                    case R.id.delete :
                        i = new Intent();
                        i.setType("image/*");
                        i.setAction(Intent.ACTION_GET_CONTENT);
                        i.putExtra("load", 1);
                        startActivityForResult(i, 1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지 표시
                    load.setImageBitmap(img);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            load.setImageURI(data.getData());
        }
    }
}
