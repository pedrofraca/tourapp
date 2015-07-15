package io.github.pedrofraca.tourapp.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.github.pedrofraca.tourapp.network.GetStagesListener;
import io.github.pedrofraca.tourapp.R;
import io.github.pedrofraca.tourapp.adapter.TourStageAdapter;
import io.github.pedrofraca.tourapp.network.GetStagesAsyncTask;
import io.github.pedrofraca.tourapp.model.TourStage;

public class MainActivityFragment extends Fragment implements GetStagesListener {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private TourStageAdapter mAdapter;
    private List<TourStage> mStages;
    private static final String ATTR_DATASET = "ATTR_DATASET";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        new GetStagesAsyncTask(this).execute();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<TourStage> tourStages = new ArrayList<>();
        mAdapter = new TourStageAdapter(tourStages,getActivity());
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onStagesReceived(List<TourStage> stages) {
        mStages = stages;
        mAdapter = new TourStageAdapter(stages,getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ATTR_DATASET, (ArrayList<? extends Parcelable>) mStages);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null){
            mStages=savedInstanceState.getParcelableArrayList(ATTR_DATASET);
            mAdapter = new TourStageAdapter(mStages,getActivity());
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onStagesError(Exception error) {

    }
}
