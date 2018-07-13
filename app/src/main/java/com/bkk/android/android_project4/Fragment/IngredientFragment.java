package com.bkk.android.android_project4.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bkk.android.android_project4.Adapter.IngredientAdapter;
import com.bkk.android.android_project4.DetailActivity;
import com.bkk.android.android_project4.KeyUtil.KeyFile;
import com.bkk.android.android_project4.Model.Ingredient;
import com.bkk.android.android_project4.Model.Recipe;
import com.bkk.android.android_project4.R;

import java.util.ArrayList;
import java.util.List;


// fragment needs to be 'embedded' into the 'host' activity
public class IngredientFragment extends Fragment {


    // empty constructor for making fragment
    public IngredientFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // make a 'view'
        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);

        // extracting data from the 'Bundle'
        Recipe recipe_object = getArguments().getParcelable(KeyFile.INGREDIENT_KEY);


        // make a new array list
        List<Ingredient> ingredientList = recipe_object.getIngredients();
//        Toast.makeText(getContext(), ingredientList.get(1).getIngredient(), Toast.LENGTH_SHORT).show();
//        ArrayList<Ingredient> ingredient_arraylist = new ArrayList<>(ingredientList);


        // getting reference to RecyclerView in XML file
         RecyclerView ingredientRecyclerView = rootView.findViewById(R.id.rv_ingredientss);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
        ingredientRecyclerView.setLayoutManager( linearLayoutManager );

        IngredientAdapter ingredientAdapter = new IngredientAdapter( getContext() );
        ingredientRecyclerView.setAdapter( ingredientAdapter );


        // insert data into the 'adapter'
        ingredientAdapter.swapData(ingredientList);


        // TODO: need to add Widget code here

        return rootView;
    } // onCreateView


} // class IngredientFragment
