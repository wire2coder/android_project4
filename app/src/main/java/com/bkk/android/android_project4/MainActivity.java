package com.bkk.android.android_project4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.bkk.android.android_project4.Adapter.RecipeAdapter;
import com.bkk.android.android_project4.Model.Recipe;
import com.bkk.android.android_project4.RetrofitUtil.RetrofitUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    /*
    * variables
    * */
    private Recipe mRecipe;

    private ArrayList<Recipe> mRecipe_list;
    private RecyclerView rv_recipes;
    private RecipeAdapter recipeAdapter;

    public static String RECIPE_KEY = "recipe_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);

        rv_recipes = findViewById(R.id.rv_recipes);
        recipeAdapter = new RecipeAdapter(MainActivity.this);

        rv_recipes.setLayoutManager(linearLayoutManager); // >> set Linear Layout
        rv_recipes.setAdapter(recipeAdapter); // >> empty data at this line


        Call<ArrayList<Recipe>>
                retro_async_network_call = RetrofitUtil.method1();

        retro_async_network_call.enqueue(new Callback<ArrayList<Recipe>>() {

            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {

                mRecipe_list = response.body();
//                 Log.v("tag", String.valueOf(mRecipe_list.size() ) );

                recipeAdapter.swapData(MainActivity.this, mRecipe_list);

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                 Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }

        });


    } // onCreate


} // MainActivity
