package com.bkk.android.android_project4;

import com.bkk.android.android_project4.Adapter.IngredientAdapter;
import com.bkk.android.android_project4.Fragment.IngredientFragment;
import com.bkk.android.android_project4.Fragment.StepsFragment;
import com.bkk.android.android_project4.Fragment.VideoAndLongDesFragment;
import com.bkk.android.android_project4.KeyUtil.KeyFile;
import com.bkk.android.android_project4.Model.Ingredient;
import com.bkk.android.android_project4.Model.Recipe;
import com.bkk.android.android_project4.Model.Step;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {


    boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        // Landscape mode
        if ( findViewById(R.id.ll_detailactivity_land) != null ) {
            mTwoPane = true; // why do we need this?
//            Toast.makeText(getApplicationContext(), "Landscape Mode", Toast.LENGTH_SHORT).show();


            // get data out of the 'Intent' object
            Recipe recipe_object = getIntent().getExtras().getParcelable(MainActivity.RECIPE_KEY);


            // make a new 'Bundle' and put the 'INGREDIENTS' in it
            Bundle bundleForFragments = new Bundle();
            bundleForFragments.putParcelable(KeyFile.INGREDIENT_KEY, recipe_object);

            // TEST THIS VALUE
            bundleForFragments.putBoolean( KeyFile.MTWOPANE, mTwoPane);



            // use import android.support.v4.app.FragmentManager;
            FragmentManager fragmentManager = getSupportFragmentManager();


            IngredientFragment ingredientFragment = new IngredientFragment();
            ingredientFragment.setArguments( bundleForFragments );


            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_ingredient, ingredientFragment)
                    .commit();


            // COMPLETED: make a new fragment, StepsFragment.java
            StepsFragment stepsFragment = new StepsFragment();
            stepsFragment.setArguments( bundleForFragments );

            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_steps, stepsFragment)
                    .commit();


//            Bundle selectedRecipeBundle = intentThatStartedThisActivity.getExtras();


            List steps_list = recipe_object.getSteps();
            Bundle step_bundle = new Bundle();

//          List<Step> stepsOut
//          'casting' ArrayList into List
            step_bundle.putParcelableArrayList( "step_arraylist", (ArrayList<Step>) steps_list);

            VideoAndLongDesFragment vald_fragment = new VideoAndLongDesFragment();

            vald_fragment.setArguments( step_bundle );

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_video_and_desc, vald_fragment)
                    .commit();



        } else { // Portrait Mode
            mTwoPane = false;


            // get references to 'views' in activity_detail.xml
            TextView tv_recipe_name2 = findViewById(R.id.tv_recipe_name2);


            // get data out of the 'Intent' object
            Recipe recipe_object = getIntent().getExtras().getParcelable(MainActivity.RECIPE_KEY);


            // make a new 'Bundle' and put the 'INGREDIENTS' in it
            Bundle bundleForFragments = new Bundle();
            bundleForFragments.putParcelable(KeyFile.INGREDIENT_KEY, recipe_object);


            // use import android.support.v4.app.FragmentManager;
            FragmentManager fragmentManager = getSupportFragmentManager();


            // COMPLETED: make a new fragment, IngredientFragment.java
            IngredientFragment ingredientFragment = new IngredientFragment();
            ingredientFragment.setArguments( bundleForFragments );


            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_ingredient, ingredientFragment)
                    .commit();


            // COMPLETED: make a new fragment, StepsFragment.java
            StepsFragment stepsFragment = new StepsFragment();
            stepsFragment.setArguments( bundleForFragments );

            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_steps, stepsFragment)
                    .commit();


            // Setting Recipe name
            tv_recipe_name2.setText( recipe_object.getName() );

        } // else


    } // onCreate


} // DetailActivity
