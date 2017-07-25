package com.passioncreativestudio.kyawagwin.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeImageActivity extends AppCompatActivity implements View.OnClickListener {
    static final int REQUEST_IMAGE_CAPTURE = 200;

    ImageView mImageView;
    Button mTakeImageButton;

    String mCurrentPhotoPath;

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
        dispatchTakePictureIntent();
    }


    // TODO: 25/7/17 Save capture image to public image folder
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(REQUEST_IMAGE_CAPTURE == requestCode && resultCode == RESULT_OK) {
            //Bundle extra = data.getExtras();

            //Bitmap imageBitmap = (Bitmap) extra.get("data");

            //mImageView.setImageBitmap(imageBitmap);

            galleryAddPic();
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takeImageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takeImageIntent.resolveActivity(getPackageManager()) != null) {
            File imageFile = null;
            try {
                imageFile = createImageFile();
            } catch (IOException ex) {
                ex.fillInStackTrace();
            }

            if (imageFile != null) {
                Uri imageURI = FileProvider.getUriForFile(this, "com.passioncreativestudio.kyawagwin.myapplication.fileprovider", imageFile);
                takeImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI);
                startActivityForResult(takeImageIntent, REQUEST_IMAGE_CAPTURE);
            }

        }
    }

    // Create an image file name
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        mCurrentPhotoPath = image.getAbsolutePath();

        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

}
