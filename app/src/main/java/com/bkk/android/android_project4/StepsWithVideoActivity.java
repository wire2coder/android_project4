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
import com.bkk.android.android_project4.KeyUtil.KeyFile;
import com.bkk.android.android_project4.Model.Recipe;
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
            Recipe recipe_object = selectedRecipeBundle.getParcelable(KeyFile.INGREDIENT_KEY);
//                Log.v("tag", recipe_object.getName() );

            Boolean mTwoPane = selectedRecipeBundle.getBoolean(KeyFile.MTWOPANE);
                Log.v("tag StepsWVA", String.valueOf(mTwoPane) );

            FragmentManager fragmentManager = getSupportFragmentManager(); // use import android.support.v4.app.FragmentManager;

            VideoAndLongDesFragment vald_fragment = new VideoAndLongDesFragment();
            vald_fragment.setArguments(selectedRecipeBundle);

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_video_and_desc, vald_fragment)
                    .commit();


    } // onCreate



    // for implements VideoAndLongDesFragment.ClickInterface
    @Override
    public void clickInterfaceMethod1(ArrayList<Step> step_arraylist, int step_arraylist_position2) {
        Log.v("tag clickMethod1", String.valueOf( step_arraylist_position2 )  );


        /*
        * Logic for making new starting new Fragment
        * */
        FragmentManager fragmentManager = getSupportFragmentManager(); // use import android.support.v4.app.FragmentManager;
        VideoAndLongDesFragment vald_fragment2 = new VideoAndLongDesFragment();


        Bundle onClickBundleObject = new Bundle();
        onClickBundleObject.putParcelableArrayList("step_arraylist", step_arraylist);
        onClickBundleObject.putInt("step_arraylist_position2", step_arraylist_position2);


        vald_fragment2.setArguments(onClickBundleObject);


        fragmentManager.beginTransaction()
                // .replace() instad of .add() so you make a new VideoPlayer
                .replace(R.id.fragment_video_and_desc, vald_fragment2)
                .commit();

    } // clickInterfaceMethod1()


} // class StepsWithVideoActivity
