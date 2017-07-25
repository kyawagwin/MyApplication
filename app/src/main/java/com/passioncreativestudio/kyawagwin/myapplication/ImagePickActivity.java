package com.passioncreativestudio.kyawagwin.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImagePickActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int IMAGE_PICK_REQUEST_CODE = 100;
    private Bitmap mBitmap;
    private ImageView mImageView;
    private Button mImagePickButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_pick);

        mImageView = (ImageView) findViewById(R.id.activity_image_pick_imageView);
        mImagePickButton = (Button) findViewById(R.id.activity_image_pick_imagePickButton);

        mImagePickButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, IMAGE_PICK_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO: 7/25/2017 capture choose photo
        super.onActivityResult(requestCode, resultCode, data);
    }
}
