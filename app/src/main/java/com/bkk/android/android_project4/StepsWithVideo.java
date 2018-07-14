package com.bkk.android.android_project4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bkk.android.android_project4.Model.Step;

import java.util.ArrayList;

public class StepsWithVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_with_video);

        Intent intentThatStartedThisActivity = getIntent();
        Step step_object = intentThatStartedThisActivity.getExtras().getParcelable("step_object");


        TextView tv_test1 = findViewById(R.id.tv_test1);
        tv_test1.setText( step_object.getDescription() );

        // TODO: 7/14, put a video player here


    } // onCreate

} // class StepsWithVideo
