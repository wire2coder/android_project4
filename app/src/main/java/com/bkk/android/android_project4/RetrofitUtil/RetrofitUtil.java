package com.bkk.android.android_project4.RetrofitUtil;


import android.util.Log;

import com.bkk.android.android_project4.Model.GitHubRepo;
import com.bkk.android.android_project4.Model.Recipe;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class RetrofitUtil {


    private static String RECIPE_BASE_URL= "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    private static String GITHUB_BASE_URL= "https://api.github.com/";


    public static
    Call< ArrayList<Recipe> >
    method1() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(RECIPE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        IRetrofit interface1 = retrofit.create( IRetrofit.class );

        Call<ArrayList<Recipe>> retro_client2 = interface1.imethod_recipe();


        return retro_client2;

    }


    /*
    * Interface
    * */
    public interface IRetrofit {

        @GET ("baking.json")
        Call<ArrayList<Recipe>>
        imethod_recipe();


    }

} // class

