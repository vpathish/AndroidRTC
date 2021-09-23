package fr.pchab.androidrtc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


    }


    public void NxtActivity(View v)
    {
//        Intent myIntent = new Intent(MainActivity.this, RtcActivity.class);
        Intent myIntent = new Intent(MainActivity.this, Preview_activity.class);

        MainActivity.this.startActivity(myIntent);
    }
}