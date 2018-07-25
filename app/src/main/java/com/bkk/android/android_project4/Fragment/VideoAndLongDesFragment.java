package com.bkk.android.android_project4.Fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bkk.android.android_project4.DetailActivity;
import com.bkk.android.android_project4.KeyUtil.KeyFile;
import com.bkk.android.android_project4.Model.Recipe;
import com.bkk.android.android_project4.Model.Step;
import com.bkk.android.android_project4.R;
import com.bkk.android.android_project4.SharedViewModel.DetailViewModel;
import com.bkk.android.android_project4.StepsWithVideoActivity;
import com.google.android.exoplayer2.DefaultLoadControl;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;

import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;


public class VideoAndLongDesFragment extends Fragment {

    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;
    private Long mVideoPosition;
    private boolean mPlayBackReady;

    private BandwidthMeter bandwidthMeter;
    private Handler mainHandler;

//    private Button but_next;
//    private Button but_previous;
    private TextView tv_long_description;


    private int step_arraylist_position2;
    private ArrayList<Step> mStepArrayList;
    private Step mStepObject;

    private Toast mToastObject;

    int lastIndexOfTheArrayList = 0;
    boolean mTwoPane;


    // empty constructor for Fragment
    public VideoAndLongDesFragment() {
    }



    // Interface logic for sharing data between fragments
    private ClickInterface m_click_interface;

    // make a 'click listener interface' for those buttons.
    public interface ClickInterface {

        // methods
        void clickInterfaceMethod1( ArrayList<Step> steps_arrayList, int step_arraylist_position );

    } // interface




    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        // logic for initializing data
        View rootView = inflater.inflate(R.layout.fragment_video_and_desc, container, false);


        Button but_next = rootView.findViewById(R.id.but_next);
        Button but_previous = rootView.findViewById(R.id.but_previous);
        tv_long_description = rootView.findViewById(R.id.tv_long_description);

        mainHandler = new Handler();
        bandwidthMeter = new DefaultBandwidthMeter();
        simpleExoPlayerView = rootView.findViewById(R.id.video_player1);
        simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);



        /*
         * Logic for extracting data coming from
         * public void step_on_click(List<Step> list_in, int step_arraylist_position) {
         * */
        mStepArrayList = getArguments().getParcelableArrayList("step_arraylist");
//            Log.v("tag mStepArrayList ", mStepArrayList.toString()  );

        step_arraylist_position2 = getArguments().getInt("step_arraylist_position2");
//            Log.v("tag position2 ", String.valueOf( step_arraylist_position2 )  );

        mStepObject = mStepArrayList.get(step_arraylist_position2);

        mTwoPane = getArguments().getBoolean(KeyFile.MTWOPANE);
            Log.v("tag VideoAndLongDesFra", String.valueOf(mTwoPane) );


        // TRUE for Landscape, FALSE for Portrait
        // Landscape mode, don't make new activity when buttons are click
        // TODO: default value is false
        // TODO: BUG if we change the value to false
//            if ( getView().findViewById(R.id.fvdl_sw600_land) != null ) {




            // initializing click_listener, make sure do 'IMPLEMENT' the VideoAndLongDesFragment.ClickInterface
            // TODO: for Portrait, not for Landscape or you will get 'casting error'
            m_click_interface = (StepsWithVideoActivity) container.getContext();

            String link_to_video = mStepObject.getVideoURL();
//            Log.v("tag link_to_video ", link_to_video  );


            tv_long_description.setText(mStepObject.getDescription());



             // Logic for how Video Player should behavior when this 'Fragment' is created.
            if (!link_to_video.isEmpty()) {
                Uri uri1 = Uri.parse(link_to_video);
                makeNewPlayer(uri1);
            } else {
                player = null;
                simpleExoPlayerView.setForeground(
                        ContextCompat.getDrawable(
                                getContext(), R.drawable.ic_visibility_off_white_36dp)
                );
            }




            // BUTTON CLICK HANDLER
            but_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    lastIndexOfTheArrayList = mStepArrayList.size() - 1; // size >> 7, size - 1 >> 6
//                    Log.v("tag currentIndex ", String.valueOf( step_arraylist_position2 )  );
//                    Log.v("tag lastIndex ", String.valueOf( lastIndexOfTheArrayList )  );

                    if (mStepArrayList.get(step_arraylist_position2).getId() < lastIndexOfTheArrayList) {

                        if (player != null) {
                            player.stop();
                        }

                        m_click_interface.clickInterfaceMethod1(mStepArrayList,
                                mStepArrayList.get(step_arraylist_position2).getId() + 1);

                    } else {

                        // clear old 'Toast' message
                        if (mToastObject != null) {
                            mToastObject.cancel();
                        }

                        mToastObject = Toast.makeText(getContext(), "You Are at the Last Step", Toast.LENGTH_SHORT);
                        mToastObject.show();
                    }

                } // onClick
            }); // setOnClickListener


            // BUTTON CLICK HANDLER
            but_previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int firstIndexOfTheArrayList = 0;

                    if (mStepArrayList.get(step_arraylist_position2).getId() > firstIndexOfTheArrayList) {

                        if (player != null) {
                            player.stop();
                        }


                        m_click_interface.clickInterfaceMethod1(mStepArrayList,
                                mStepArrayList.get(step_arraylist_position2).getId() - 1);

                    } else {

                        // clear old 'Toast' message
                        if (mToastObject != null) {
                            mToastObject.cancel();
                        }

                        mToastObject = Toast.makeText(getContext(), "You Are at the First Step", Toast.LENGTH_SHORT);
                        mToastObject.show();

                    }

                } // onClick
            }); // setOnClickListener


        return rootView;
    } // onCreateView




    private void releasePlayer() {
        mVideoPosition = player.getCurrentPosition();
        mPlayBackReady = player.getPlayWhenReady();
        player.stop();
        player.release();

        if(player !=null){
            player = null;
        }
    }


    // create a new VideoPlayer
    private void makeNewPlayer(Uri mediaUri) {

            if (player == null) {

                TrackSelection.Factory videoTrackSelectionFactory =
                        new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);

                DefaultTrackSelector trackSelector =
                        new DefaultTrackSelector(mainHandler, videoTrackSelectionFactory);

                LoadControl loadControl = new DefaultLoadControl();

                player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
                simpleExoPlayerView.setPlayer(player);

                String userAgent = Util.getUserAgent(getContext(), "Baking Cookie App");

                MediaSource mediaSource = new ExtractorMediaSource(
                        mediaUri
                        , new DefaultDataSourceFactory(getContext(), userAgent)
                        , new DefaultExtractorsFactory()
                        , null
                        , null
                );


                player.prepare(mediaSource);
                player.setPlayWhenReady(true);


            } // if

    } // makePlayer()


    @Override
    public void onDetach() {
        super.onDetach();

        if (player!=null) {
            player.stop();
            player.release();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (player!=null) {
            player.stop();
            player.release();
            player=null;
        }
    }


    @Override
    public void onStop() {
        super.onStop();

        if (player!=null) {
            player.stop();
            player.release();
        }
    }


    @Override
    public void onPause() {
        super.onPause();

        if (player!=null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle currentDataBundle) {
        super.onSaveInstanceState(currentDataBundle);
//        currentDataBundle.putInt("step_arraylist_position", step_arraylist_position);
    } // onSaveInstanceState


} // class VideoAndLongDesFragment
