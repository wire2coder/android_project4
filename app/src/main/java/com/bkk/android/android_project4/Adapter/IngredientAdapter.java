package com.bkk.android.android_project4.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bkk.android.android_project4.Model.Ingredient;
import com.bkk.android.android_project4.Model.Recipe;
import com.bkk.android.android_project4.R;

import java.util.ArrayList;
import java.util.List;


public class IngredientAdapter extends RecyclerView.Adapter< IngredientAdapter.RecyclerViewHolder > {


    private Context mContext;
    private List<Ingredient> mIngredientList;


    public IngredientAdapter(Context mContext) {
        this.mContext = mContext;
//        , List<Ingredient> list_in
//        this.mIngredientList = list_in;
    }

    public void swapData(List<Ingredient> list_in ) {

        if (list_in.size() != 0) {
            this.mIngredientList = list_in;
            notifyDataSetChanged();
        } else {
            Log.v("tag: " , " error in swapData");
        }

    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // get context
        Context context = parent.getContext();

        // get the number for the xml file
        int layoutIdForListItem = R.layout.row_ingredient_item;

        // make a new 'layout inflater'
        LayoutInflater inflater = LayoutInflater.from(context);

        // extra options
        boolean shouldAttachToParentImmediately = false;

        // make a 'view'
        View view =inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        // make a 'view holder'
        IngredientAdapter.RecyclerViewHolder rvh = new IngredientAdapter.RecyclerViewHolder(view);

        return  rvh;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        Ingredient ingredient_object = mIngredientList.get( position );

        holder.tv_ingredient_name.setText( ingredient_object.getIngredient() );


    }

    @Override
    public int getItemCount() {
        return mIngredientList != null ? mIngredientList.size() : 0 ;
    }



    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView tv_ingredient_name;
        TextView tv_ingredient_quantity;
        TextView tv_ingredient_measurement;


        public RecyclerViewHolder(View itemView) {
            super(itemView);


            tv_ingredient_name = itemView.findViewById(R.id.tv_ingredient_name);
            tv_ingredient_quantity = itemView.findViewById(R.id.tv_ingredient_quantity);
            tv_ingredient_measurement = itemView.findViewById(R.id.tv_ingredient_measurement);


            // implements View.OnClickListener
            itemView.setOnClickListener(this);
        }

        // implements View.OnClickListener
        @Override
        public void onClick(View view) {

        }

    } // class RecyclerViewHolder


} // class IngredientAdapter
