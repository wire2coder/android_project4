package com.bkk.android.android_project4;

import android.app.IntentService;
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

public class StepsWithVideoActivity extends AppCompatActivity implements VideoAndLongDesFragment.ClickInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_with_video);


        // Logic for passing DATA to the VideoAndLongDesFragment
            Intent intentThatStartedThisActivity = getIntent();

            Bundle selectedRecipeBundle = intentThatStartedThisActivity.getExtras();

            FragmentManager fragmentManager = getSupportFragmentManager(); // use import android.support.v4.app.FragmentManager;

            VideoAndLongDesFragment vald_fragment = new VideoAndLongDesFragment();
            vald_fragment.setArguments(selectedRecipeBundle);

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_video_and_desc, vald_fragment)
                    .commit();




        // just one step object
//        Step step_object = intentThatStartedThisActivity.getExtras().getParcelable("step_object");
//        ArrayList<Step> step_arraylist = intentThatStartedThisActivity.getExtras().getParcelableArrayList("step_arraylist");
//        int step_arraylist_position = intentThatStartedThisActivity.getExtras().getInt("step_arraylist_position");
//        Log.v("tag", String.valueOf( step_arraylist_position )  );



        // Data insertion logic
//        Bundle bundle1 = new Bundle();
//        bundle1.putParcelable("step_object", step_object);
//        bundle1.putParcelableArrayList("step_arraylist", step_arraylist );
//        bundle1.putInt("step_arraylist_position", step_arraylist_position);

        // make a new fragment
//        VideoAndLongDesFragment vald_fragment = new VideoAndLongDesFragment();
        // data going into the 'Fragment'
//        vald_fragment.setArguments(bundle1);

        // use import android.support.v4.app.FragmentManager;
//        FragmentManager fragmentManager = getSupportFragmentManager();

//        fragmentManager.beginTransaction()
//                .add(R.id.fragment_video_and_desc, vald_fragment)
//                .commit(); // run the Fragment



    } // onCreate



    // for implements VideoAndLongDesFragment.ClickInterface
    @Override
    public void clickInterfaceMethod1(ArrayList<Step> step_arraylist, int step_arraylist_position2) {
//        Log.v("tag >>>", String.valueOf( step_arraylist_position2 )  );

        int next_step = step_arraylist_position2 + 1;
//        Log.v("tag clickMethod1 ", String.valueOf( next_step )  );

        /*
        * Logic for making new starting new Fragment
        * */
        FragmentManager fragmentManager = getSupportFragmentManager(); // use import android.support.v4.app.FragmentManager;
        VideoAndLongDesFragment vald_fragment2 = new VideoAndLongDesFragment();

        Step nextStep = step_arraylist.get(next_step);

        Bundle onClickBundleObject = new Bundle();
        onClickBundleObject.putParcelableArrayList("step_arraylist", step_arraylist);
        onClickBundleObject.putInt("step_arraylist_position2", next_step);
        onClickBundleObject.putParcelable("next_step", nextStep);


        vald_fragment2.setArguments(onClickBundleObject);

        fragmentManager.beginTransaction()
                .add(R.id.fragment_video_and_desc, vald_fragment2)
                .commit();




    }


} // class StepsWithVideoActivity
