package com.passioncreativestudio.kyawagwin.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TakeImageActivity extends AppCompatActivity implements View.OnClickListener {
    static final int REQUEST_IMAGE_CAPTURE = 200;

    ImageView mImageView;
    Button mTakeImageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_take_image);

        mImageView = (ImageView) findViewById(R.id.activity_take_image_imageView);

        mTakeImageButton = (Button) findViewById(R.id.activity_take_image_takeImageButton);
        mTakeImageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePhotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(REQUEST_IMAGE_CAPTURE == requestCode && resultCode == RESULT_OK) {
            Bundle extra = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extra.get("data");

            mImageView.setImageBitmap(imageBitmap);
        }
    }
}
