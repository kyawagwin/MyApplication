package com.passioncreativestudio.kyawagwin.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
        Intent pickImageIntent = new Intent();
        pickImageIntent.setType("image/*");
        pickImageIntent.setAction(Intent.ACTION_GET_CONTENT);
        pickImageIntent.addCategory(Intent.CATEGORY_OPENABLE);

        startActivityForResult(pickImageIntent, IMAGE_PICK_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream steam = null;
        if(IMAGE_PICK_REQUEST_CODE == requestCode && resultCode == RESULT_OK) {
            try {
                if(mBitmap != null) {
                    mBitmap.recycle();
                }

                steam = getContentResolver().openInputStream(data.getData());

                mBitmap = BitmapFactory.decodeStream(steam);

                mImageView.setImageBitmap(mBitmap);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                if(steam != null) {
                    try {
                        steam.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
