package com.bkk.android.android_project4;

import com.bkk.android.android_project4.Adapter.IngredientAdapter;
import com.bkk.android.android_project4.Fragment.IngredientFragment;
import com.bkk.android.android_project4.Fragment.StepsFragment;
import com.bkk.android.android_project4.KeyUtil.KeyFile;
import com.bkk.android.android_project4.Model.Ingredient;
import com.bkk.android.android_project4.Model.Recipe;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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


    } // onCreate


} // DetailActivity