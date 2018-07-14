package com.bkk.android.android_project4;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bkk.android.android_project4.Fragment.VideoAndLongDesFragment;
import com.bkk.android.android_project4.Model.Step;

import java.util.ArrayList;

public class StepsWithVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_with_video);

        Intent intentThatStartedThisActivity = getIntent();

        // just one step object
        Step step_object = intentThatStartedThisActivity.getExtras().getParcelable("step_object");

        // TODO: HERE
        ArrayList<Step> asdf = getIntent().getExtras().getParcelableArrayList("step_arraylist");
//        Log.v("tag", String.valueOf( asdf.size() )  );



        // the video player and the 'long description' needs to be in the fragment
        // because 'they' need to change when you click the 'next' and 'previous' button

        // make a new fragment

        Bundle bundle1 = new Bundle();
        bundle1.putParcelable("step_object", step_object);

        VideoAndLongDesFragment vald_fragment = new VideoAndLongDesFragment();
        vald_fragment.setArguments(bundle1); // data going into the 'Fragment'

        // use import android.support.v4.app.FragmentManager;
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_video_and_desc, vald_fragment)
                .commit(); // run the Fragment



    } // onCreate

} // class StepsWithVideoActivity
