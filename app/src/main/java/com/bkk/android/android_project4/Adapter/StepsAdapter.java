package com.bkk.android.android_project4.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bkk.android.android_project4.Model.Step;
import com.bkk.android.android_project4.R;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.RecyclerViewHolder> {


    private Context mContext;
    private List<Step> mStepsList;

    private step_click_interface mStep_click_interface;


    // Interface
    public interface step_click_interface {
        void step_on_click( List<Step> list_in, int position );
    }


    public StepsAdapter(Context context, step_click_interface click_interface_in) {
        this.mContext = context;

        // data flow OUT of this adapter
        this.mStep_click_interface = click_interface_in;
    }


    public void swapData( List<Step> list_in ) {

        if (list_in.size() != 0) {
            this.mStepsList = list_in;
            notifyDataSetChanged();
        } else {
            Log.v("tag: " , " error in swapData");
        }

    }



    @Override
    public RecyclerViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        // get context
        Context context = parent.getContext();

        // get the number for the xml file
        int layoutIdForListItem = R.layout.row_steps_item;

        // make a new 'layout inflater'
        LayoutInflater inflater = LayoutInflater.from(context);

        // extra options
        boolean shouldAttachToParentImmediately = false;

        // make a 'view'
        View view =inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        // make a 'view holder'
        StepsAdapter.RecyclerViewHolder rvh = new StepsAdapter.RecyclerViewHolder(view);


        return rvh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        Step step_object = mStepsList.get( position );

        int id = step_object.getId();
//        String step_number = String.valueOf( id+1 );
        String short_description = step_object.getShortDescription();
//        String long_description = step_object.getDescription();

//        holder.tv_step_number.setText(step_number);
        holder.tv_step_short_description.setText(short_description);
//        holder.tv_long_description.setText(long_description);


    }

    @Override
    public int getItemCount() {
        return mStepsList != null ? mStepsList.size() : 0 ;
    }



    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

//        TextView tv_step_number;
        TextView tv_step_short_description;
//        TextView tv_long_description;


        public RecyclerViewHolder(View itemView) {
            super(itemView);

//            tv_step_number = itemView.findViewById(R.id.tv_step_number);
            tv_step_short_description = itemView.findViewById(R.id.tv_step_short_description);
//            tv_long_description = itemView.findViewById(R.id.tv_long_description);


            // implements View.OnClickListener
            itemView.setOnClickListener(this);
        }


        // implements View.OnClickListener
        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), String.valueOf( getAdapterPosition() ), Toast.LENGTH_SHORT).show();

            // data flow OUT of this adapter
             mStep_click_interface.step_on_click( mStepsList, getAdapterPosition() );


        }

    } // class RecyclerViewHolder

} // class StepsAdapter
