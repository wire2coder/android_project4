package com.bkk.android.android_project4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bkk.android.android_project4.Model.GitHubRepo;
import com.bkk.android.android_project4.Model.Recipe;
import com.bkk.android.android_project4.RetrofitUtil.RetrofitUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    /*
    * variables
    * */

    private Recipe mRecipe;

    private ArrayList<Recipe> mRecipe_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Call<ArrayList<Recipe>>
                retro_async_network_call = RetrofitUtil.method1();


        retro_async_network_call.enqueue(new Callback<ArrayList<Recipe>>() {

            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {

                mRecipe_list = response.body();
//                 Log.v("tag", String.valueOf(mRecipe_list.size() ) );

                /*
                for (int i = 0; i < mRecipe_list.size(); i++) {

                    String asdf1 = mRecipe_list.get(i).getName();
                    String asdf2 = mRecipe_list.get(i).getIngredients().get(i).getMeasure();
                    String asdf3 = mRecipe_list.get(i).getSteps().get(i).getShortDescription();

                   Log.v("tag name: ", asdf1);
                   Log.v("tag measure: ", asdf2);
                   Log.v("tag short description: ", asdf3);

                }
                */

                // TODO: make an 'Adapter' for the RecyclerView

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                 Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }

        });




        // Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();

    } // onCreate


} // MainActivity
