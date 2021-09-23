package fr.pchab.androidrtc;


import android.app.Activity;
import android.Manifest;

import android.content.Intent;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.controls.Flash;

public class Preview_activity extends AppCompatActivity {

    private static final String[] RequiredPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
    protected PermissionChecker permissionChecker = new PermissionChecker();
    CameraView cameraView;
    ImageView switch_cam, flash_btn;
    int flashoptn, camoptn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(
                LayoutParams.FLAG_FULLSCREEN
                        | LayoutParams.FLAG_KEEP_SCREEN_ON
                        | LayoutParams.FLAG_DISMISS_KEYGUARD
                        | LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_preview);

        checkPermissions();
        cameraView = findViewById(R.id.camera);
        cameraView.setLifecycleOwner(this);
        cameraView.setFlash(Flash.OFF);
        cameraView.setFacing(Facing.BACK);
        flashoptn=0;
        camoptn=0;

        flash_btn = findViewById(R.id.flash);
        switch_cam = findViewById(R.id.switch_cam);

        flash_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flash_fn();
            }
        });

        switch_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cam_btn();
            }
        });
    }


    private void flash_fn()
    {
       Log.d("flash_btn", cameraView.getFlash().toString());

       if(cameraView.getFlash().equals(Flash.OFF)) {
           cameraView.setFlash(Flash.TORCH);
           flash_btn.setBackgroundResource(R.drawable.ic_baseline_flash_off_24);
           flashoptn =1;
       }


        else
       {
           cameraView.setFlash(Flash.OFF);
           flash_btn.setBackgroundResource(R.drawable.ic_baseline_flash_on_24);
           flashoptn = 0;
       }
    }


    private void cam_btn()
    {
        Log.d("flash_btn", cameraView.getFacing().toString());

        if(cameraView.getFacing().equals(Facing.FRONT)) {
            cameraView.setFacing(Facing.BACK);
            camoptn = 0;
        }

        else {
            cameraView.setFacing(Facing.FRONT);
            camoptn = 1;
        }
    }

    public void FnlActivity(View v)
    {
//        Intent myIntent = new Intent(MainActivity.this, RtcActivity.class);
        Intent myIntent = new Intent(Preview_activity.this, RtcActivity.class);
        myIntent.putExtra("flash", flashoptn);
        myIntent.putExtra("camera", camoptn);

        Preview_activity.this.startActivity(myIntent);
    }

    private void checkPermissions() {
        permissionChecker.verifyPermissions(this, RequiredPermissions, new PermissionChecker.VerifyPermissionsCallback() {

            @Override
            public void onPermissionAllGranted() {

            }

            @Override
            public void onPermissionDeny(String[] permissions) {
                Toast.makeText(Preview_activity.this, "Please grant required permissions.", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        cameraView.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraView.destroy();
    }

}