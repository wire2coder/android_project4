package com.bkk.android.android_project4.SharedViewModel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bkk.android.android_project4.Model.Recipe;

public class DetailViewModel extends ViewModel {

    // class variables
    private static final String TAG = DetailViewModel.class.getSimpleName();
    private  Recipe recipe_object;


    // constructor
    public DetailViewModel() {
         Log.d(TAG, "Actively retrieving  the recipe_object:");
    }

    public Recipe getRecipe_object() {
        return recipe_object;
    }

    public void setRecipe_object(Recipe recipe_object) {
        this.recipe_object = recipe_object;
    }


} // class SharedViewModel
