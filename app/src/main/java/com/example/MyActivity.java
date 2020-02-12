package com.example;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.InputStream;

public class MyActivity extends AppCompatActivity {

    ImageButton cam,men,hom,plu,peo;
    Button del,con,star,info,log;
    Intent i;

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
        con = (Button) findViewById(R.id.confirm_);
        star = (Button) findViewById(R.id.star_);
        info = (Button) findViewById(R.id.info_);
        log = (Button) findViewById(R.id.logout_);
        del = (Button) findViewById(R.id.delete);

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        final View drawerView = (View) findViewById(R.id.sidemenu);

        ImageButton btnOpenDrawer = (ImageButton) findViewById(R.id.menubar);

        btnOpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.camera :
                        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
                        if(permissionCheck == PackageManager.PERMISSION_DENIED) { // 권한 없음
                            ActivityCompat.requestPermissions(MyActivity.this, new String[]{Manifest.permission.CAMERA},0);
                        } else { // 권한 있음
                            i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(i, 1);
                        }
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
                        Intent i10 = new Intent(getApplicationContext(), ProfileEdit.class);
                        startActivity(i10);
                        finish();
                        break;
                    case R.id.confirm_ :
                        Intent i6 = new Intent(getApplicationContext(), Notice.class);
                        startActivity(i6);
                        finish();
                        break;
                    case R.id.star_ :
                        Intent i7 = new Intent(getApplicationContext(), PostingStar.class);
                        startActivity(i7);
                        finish();
                        break;
                    case R.id.info_ :
                        Intent i8 = new Intent(getApplicationContext(), Info.class);
                        startActivity(i8);
                        finish();
                        break;
                    case R.id.logout_ :
                        Intent i9 = new Intent(getApplicationContext(), LoginMain.class);
                        startActivity(i9);
                        finish();
                        break;

                }

            }
        };

        cam.setOnClickListener(cl);
        hom.setOnClickListener(cl);
        plu.setOnClickListener(cl);
        peo.setOnClickListener(cl);
        del.setOnClickListener(cl);
        con.setOnClickListener(cl);
        star.setOnClickListener(cl);
        info.setOnClickListener(cl);
        log.setOnClickListener(cl);

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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // Check which request we're responding to
//        if (requestCode == 1) {
//            // Make sure the request was successful
//            if (resultCode == RESULT_OK) {
//                try {
//                    // 선택한 이미지에서 비트맵 생성
//                    InputStream in = getContentResolver().openInputStream(data.getData());
//                    Bitmap img = BitmapFactory.decodeStream(in);
//                    in.close();
//                    // 이미지 표시
//                    load.setImageBitmap(img);
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            load.setImageURI(data.getData());
//        }
//    }
}
