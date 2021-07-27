package com.example.androidpermission_r_11;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.canhub.cropper.CropImage;
import com.canhub.cropper.CropImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                String filePath = result.getUriFilePath(MainActivity.this, true);
                File fileDisplayPicture = new File(filePath);
                ImageView imageView = findViewById(R.id.iv_image);
                imageView.setImageURI(Uri.fromFile(fileDisplayPicture));
            } else if (requestCode == 525) {
                ImageView imageView = findViewById(R.id.iv_image);
                imageView.setImageBitmap(data.getParcelableExtra("BitmapImage"));
            }
        }
    }

    public void pickImage(View view) {
        Intent intentLicenseBack = CropImage.activity(null)
                .setGuidelines(CropImageView.Guidelines.ON)
                .getIntent(this);
        startActivityForResult(intentLicenseBack, 200);
    }

    public void pickImageFromCamera(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivityForResult(intent, 525);
    }
}