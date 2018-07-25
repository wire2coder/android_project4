package com.bkk.android.android_project4;


import com.bkk.android.android_project4.Fragment.IngredientFragment;
import com.bkk.android.android_project4.Fragment.StepsFragment;
import com.bkk.android.android_project4.Fragment.VideoAndLongDesFragment;
import com.bkk.android.android_project4.KeyUtil.KeyFile;
import com.bkk.android.android_project4.Model.Recipe;
import com.bkk.android.android_project4.Model.Step;
import com.bkk.android.android_project4.SharedViewModel.DetailViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {


    private static final String TAG = DetailActivity.class.getSimpleName();
    private DetailViewModel mDetailViewModel;
    boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        // get data out of the 'Intent' object
        Recipe recipe_object = getIntent().getExtras().getParcelable(MainActivity.RECIPE_KEY);

        // ShareViewModel Logic
        mDetailViewModel = ViewModelProviders.of(DetailActivity.this).get(DetailViewModel.class);
        mDetailViewModel.setRecipe_object(recipe_object);

        Recipe recipe_object_viewmodel = mDetailViewModel.getRecipe_object();
//            Log.v(TAG, String.valueOf( recipe_object_viewmodel )  );



        // Landscape mode, 3 fragments
        if ( findViewById(R.id.detailactivity_land) != null ) {

            // use import android.support.v4.app.FragmentManager;
            FragmentManager fragmentManager = getSupportFragmentManager();

            // make a new 'Bundle' and put the 'INGREDIENTS' in it
            Bundle bundleForFragments = new Bundle();
            bundleForFragments.putParcelable(KeyFile.INGREDIENT_KEY, recipe_object_viewmodel);

            // TEST THIS VALUE
            bundleForFragments.putBoolean( KeyFile.MTWOPANE, true);


            IngredientFragment ingredientFragment = new IngredientFragment();
            ingredientFragment.setArguments( bundleForFragments );

            // Ingredient Fragment, 1
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_ingredient, ingredientFragment)
                    .commit();



            StepsFragment stepsFragment = new StepsFragment();
            stepsFragment.setArguments( bundleForFragments );

            // Steps Fragment, 2
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_steps, stepsFragment)
                    .commit();



            List l_steps = recipe_object_viewmodel.getSteps();
            Bundle step_bundle = new Bundle();


//          'casting' ArrayList into List
            /* step_bundle.putParcelableArrayList( "step_arraylist", (ArrayList<Step>) l_steps);

            VideoAndLongDesFragment vald_fragment = new VideoAndLongDesFragment();

            vald_fragment.setArguments( step_bundle );


            fragmentManager.beginTransaction()
                    .add(R.id.fragment_video_and_desc, vald_fragment)
                    .commit();
            */


        } else { // Portrait Mode, 2 fragments

            // use import android.support.v4.app.FragmentManager;
            FragmentManager fragmentManager = getSupportFragmentManager();



            Bundle bundleForFragments = new Bundle();
            bundleForFragments.putParcelable(KeyFile.INGREDIENT_KEY, recipe_object_viewmodel);

            // TEST THIS VALUE
            bundleForFragments.putBoolean( KeyFile.MTWOPANE, false);


            // Ingredient Fragment, 1
            IngredientFragment ingredientFragment = new IngredientFragment();
            ingredientFragment.setArguments( bundleForFragments );


            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_ingredient, ingredientFragment)
                    .commit();


            // Step Fragment, 2
            StepsFragment stepsFragment = new StepsFragment();
            stepsFragment.setArguments( bundleForFragments );

            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_steps, stepsFragment)
                    .commit();



        } // else


    } // onCreate



} // DetailActivity
