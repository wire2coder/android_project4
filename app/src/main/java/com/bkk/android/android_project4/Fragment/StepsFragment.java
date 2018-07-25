package com.bkk.android.android_project4.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bkk.android.android_project4.Adapter.StepsAdapter;
import com.bkk.android.android_project4.KeyUtil.KeyFile;
import com.bkk.android.android_project4.Model.Recipe;
import com.bkk.android.android_project4.Model.Step;
import com.bkk.android.android_project4.R;
import com.bkk.android.android_project4.StepsWithVideoActivity;

import java.util.ArrayList;
import java.util.List;


public class StepsFragment extends Fragment
    implements StepsAdapter.step_click_interface {


    List<Step> step_list;
    boolean mTwoPane;

    // empty constructor for the Fragment
    public StepsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);


        // get the 'Bundle'
        Recipe recipe_object = getArguments().getParcelable( KeyFile.INGREDIENT_KEY );
        mTwoPane = getArguments().getBoolean( KeyFile.MTWOPANE );
            Log.v("tag  St Frag Lands ? ", String.valueOf(mTwoPane) );


        // make a new List from the 'Bundle'
        step_list = recipe_object.getSteps();


            // make a new RecyclerView
            RecyclerView stepsRecyclerView = rootView.findViewById(R.id.rv_steps_to_do);


            // make a new LinearLayout
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
            stepsRecyclerView.setLayoutManager( linearLayoutManager );
            stepsRecyclerView.setHasFixedSize(false);

            // make a new Adapter/data for RecyclerView, getActivity() ??????
            StepsAdapter stepsAdapter = new StepsAdapter( getActivity(), this  );

            // insert data into the RecyclerView
            stepsRecyclerView.setAdapter( stepsAdapter );

            stepsAdapter.swapData( step_list );


        return rootView;
    }



    @Override
    // the method inside the 'Adapter' is what is running
    // data is COMING OUT OF the 'Adapter' INTO this method
    public void step_on_click( List<Step> list_in, int adapterPosition ) {
//        Log.v("StepsFragment.java: ", String.valueOf( adapterPosition )  );

        /*
        * Logic for data extraction
        * */
        Intent intent1 = new Intent( getContext(), StepsWithVideoActivity.class);

        // 'position' is coming out of the getAdapterPosition() from inside StepsAdapter.java
//        Step step_object = list_in.get( position);
//        intent1.putExtra("step_object", step_object);

        // Get an ArrayList from List
        ArrayList<Step> asdf_step = new ArrayList<>(step_list);
        intent1.putParcelableArrayListExtra("step_arraylist", asdf_step);
        intent1.putExtra("step_arraylist_position2", adapterPosition);
        intent1.putExtra( KeyFile.MTWOPANE, false);



            // start the Activity
            startActivity(intent1);




    } // step_on_click()

} // class StepsFragment
