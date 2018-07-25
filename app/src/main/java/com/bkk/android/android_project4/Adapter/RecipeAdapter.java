package com.bkk.android.android_project4.Adapter;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bkk.android.android_project4.DetailActivity;
import com.bkk.android.android_project4.KeyUtil.KeyFile;
import com.bkk.android.android_project4.Model.Recipe;
import com.bkk.android.android_project4.R;


import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter< RecipeAdapter.RecyclerViewHolder  > {


    // class variables
    private Context mContext;
    private ArrayList<Recipe> al_recipe;


    public RecipeAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public void swapData(Context context_in, ArrayList<Recipe> list_in ) {

        if (list_in.size() != 0) {
            this.al_recipe = list_in;
            notifyDataSetChanged();
        } else {
            Toast.makeText(context_in, "list_in is empty! ", Toast.LENGTH_SHORT).show();
        }

    }


    @NonNull
    @Override
    public RecipeAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // get context
        Context context = parent.getContext();

        // get the number for the xml file
        int layoutIdForListItem = R.layout.row_recipe_item;

        // make a new 'layout inflater'
        LayoutInflater inflater = LayoutInflater.from(context);

        // extra options
        boolean shouldAttachToParentImmediately = false;

        // make a 'view'
        View view =inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        // make a 'view holder'
        RecyclerViewHolder rvh = new RecyclerViewHolder(view);

        return  rvh;
    }


    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.RecyclerViewHolder holder, int position) {

        Recipe recipe_object = al_recipe.get( position );

        String recipe_name = recipe_object.getName();
        String ingredient_counts =  String.valueOf( recipe_object.getIngredients().size() );
        String steps_counts = String.valueOf( recipe_object.getSteps().size() );

        holder.tv_recipe_name.setText(recipe_name);
        holder.tv_main_ingredients_count.setText(ingredient_counts);
        holder.tv_main_steps_count.setText(steps_counts);

    }


    @Override
    public int getItemCount() {
        return al_recipe != null ? al_recipe.size() : 0 ;
    }


    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tv_recipe_name;
        TextView tv_recipe_name_label;
        TextView tv_main_ingredients_count;
        TextView tv_main_steps_count;


        public RecyclerViewHolder(View itemView) {
            super(itemView);

            tv_recipe_name = itemView.findViewById(R.id.tv_recipe_name);
            tv_main_ingredients_count = itemView.findViewById(R.id.tv_main_ingredients_count);
            tv_main_steps_count = itemView.findViewById(R.id.tv_main_steps_count);


            tv_recipe_name.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int adapterPosition = getAdapterPosition();

                    Context context =  v.getContext();

                    Recipe recipe_object = al_recipe.get( adapterPosition );


                    Intent intent = new Intent( context, DetailActivity.class);
                    intent.putExtra(KeyFile.RECIPE_KEY, recipe_object);
                    context.startActivity(intent);

                }
            } );


        }


    } // class RecyclerViewHolder


} // class RecipeAdapter
