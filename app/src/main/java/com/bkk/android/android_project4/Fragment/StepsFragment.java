package com.bkk.android.android_project4.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bkk.android.android_project4.Adapter.StepsAdapter;
import com.bkk.android.android_project4.KeyUtil.KeyFile;
import com.bkk.android.android_project4.Model.Recipe;
import com.bkk.android.android_project4.Model.Step;
import com.bkk.android.android_project4.R;

import java.util.List;

// TODO: 7/13 add ClickHandler here, when click open, open other activity to play a video with ExoPlayer
public class StepsFragment extends Fragment {

    // empty constructor for the Fragment
    public StepsFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        // get the 'Bundle'
        Recipe recipe_object = getArguments().getParcelable( KeyFile.INGREDIENT_KEY );

        // make a new List from the 'Bundle'
        List<Step> step_list = recipe_object.getSteps();

        // make a new RecyclerView
        RecyclerView stepsRecyclerView = rootView.findViewById(R.id.rv_steps_to_do);


        // make a new LinearLayout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
        stepsRecyclerView.setLayoutManager( linearLayoutManager );
        stepsRecyclerView.setHasFixedSize(false);

        // make a new Adapter/data for RecyclerView, getActivity() ??????
        StepsAdapter stepsAdapter = new StepsAdapter( getActivity()  );

        // insert data into the RecyclerView
        stepsRecyclerView.setAdapter( stepsAdapter );

        stepsAdapter.swapData( step_list );

        return rootView;
    }


} // class StepsFragment
