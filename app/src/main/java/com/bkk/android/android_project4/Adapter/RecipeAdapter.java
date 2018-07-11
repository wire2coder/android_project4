package com.bkk.android.android_project4.Adapter;

import android.content.Context;
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

import com.bkk.android.android_project4.Model.Recipe;
import com.bkk.android.android_project4.R;
import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter< RecipeAdapter.RecyclerViewHolder  > {


    private Context mContext;

    private IRecipeAdapterClick mIRecipeAdapterClick;

    private ArrayList<Recipe> mRecipelist;


    public RecipeAdapter(Context mContext, IRecipeAdapterClick clickListener) {
        this.mContext = mContext;
        this.mIRecipeAdapterClick = clickListener;
    }


    public void swapData(Context context_in, ArrayList<Recipe> list_in ) {

        if (list_in.size() != 0) {
            this.mRecipelist = list_in;
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

        Recipe recipe_object = mRecipelist.get( position );
        String recipe_name = recipe_object.getName();

        holder.tv_recipe_name.setText(recipe_name);
        holder.tv_recipe_name_label.setText( R.string.recipe_name_label );

    }


    @Override
    public int getItemCount() {
        return mRecipelist != null ? mRecipelist.size() : 0 ;
    }


    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_recipe_name;
        TextView tv_recipe_name_label;


        public RecyclerViewHolder(View itemView) {
            super(itemView);

            tv_recipe_name = itemView.findViewById(R.id.tv_recipe_name);
            tv_recipe_name_label = itemView.findViewById(R.id.tv_recipe_name_label);

            // implements View.OnClickListener
            itemView.setOnClickListener(this);
        }

        // implements View.OnClickListener
        @Override
        public void onClick(View v) {
            int row_position = getAdapterPosition();

            mIRecipeAdapterClick.onRecipeClick( row_position );

        }


    } // class RecyclerViewHolder




    // use an Interface to get data from MainActivity
    public interface IRecipeAdapterClick {

        void onRecipeClick(int i);

    } // interface

} // class RecipeAdapter
