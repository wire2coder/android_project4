package com.bkk.android.android_project4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bkk.android.android_project4.Adapter.IngredientAdapter;
import com.bkk.android.android_project4.Model.Ingredient;
import com.bkk.android.android_project4.Model.Recipe;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {


    private RecyclerView rv_ingredient;
    private IngredientAdapter ingredientAdapter;
    private List<Ingredient> mIngredientList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        // get data out of the 'Intent' object
        Recipe recipe_object = getIntent().getExtras().getParcelable(MainActivity.RECIPE_KEY);
        //        String recipe_name = recipe_object.getName();

        mIngredientList = recipe_object.getIngredients();
        ArrayList<Ingredient> arrayList1 = new ArrayList<>(mIngredientList);


        rv_ingredient = findViewById(R.id.rv_ingredient);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailActivity.this);

//        ingredientAdapter = new IngredientAdapter(DetailActivity.this, arrayList1);
        ingredientAdapter = new IngredientAdapter(DetailActivity.this);

        rv_ingredient.setLayoutManager(linearLayoutManager);
        rv_ingredient.setAdapter(ingredientAdapter);

        ingredientAdapter.swapData(arrayList1);

        // TODO: finish filling IngredientAdapter.java with data




//        TextView tv_recipe_name2 = findViewById(R.id.tv_recipe_name2);
//        TextView tv_ingredient_count = findViewById(R.id.tv_ingredient_count);
//        TextView tv_step_count = findViewById(R.id.tv_step_count);

//        tv_recipe_name2.setText(recipe_name);

//        tv_ingredient_count.setText( String.valueOf( recipe_object.getIngredients().size() ) );
//        tv_step_count.setText( String.valueOf( recipe_object.getSteps().size() ) );




    } // onCreate


} // DetailActivity
