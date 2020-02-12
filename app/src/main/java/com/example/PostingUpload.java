package com.example;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class PostingUpload extends AppCompatActivity {
    ImageView load;
    ImageButton ne, can;
    Button ga, ca;
    Intent i;
    View.OnClickListener cl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postingupload);


        load = (ImageView) findViewById(R.id.load);
        ne = (ImageButton) findViewById(R.id.next);
        can = (ImageButton) findViewById(R.id.cancel);
        ca = (Button) findViewById(R.id.camera);
        ga = (Button) findViewById(R.id.gallery);


        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.cancel:
                        i = new Intent(PostingUpload.this, SNS_Main.class);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.next:
                        i = new Intent(PostingUpload.this, PostingUpload2.class);
                        startActivity(i);
                        finish();
                        break;
                }
            }
        };
        ne.setOnClickListener(cl);
        can.setOnClickListener(cl);

        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
                if(permissionCheck == PackageManager.PERMISSION_DENIED) { // 권한 없음
                    ActivityCompat.requestPermissions(PostingUpload.this, new String[]{Manifest.permission.CAMERA},0);
                } else { // 권한 있음
                    i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(i, 1);
                }
            }
        });

        ga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(PostingUpload.this,PostingUpload2.class);
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.putExtra("load", 1);



                startActivityForResult(i, 1);
            }
        });

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==0) {
            if(grantResults[0]==0) {
                Toast.makeText(this,"카메라 권한 승인 완료!",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"카메라 권한 승인 실패, 권한 승낙 요청 바람",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
