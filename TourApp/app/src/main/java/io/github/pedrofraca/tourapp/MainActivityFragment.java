package io.github.pedrofraca.tourapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.github.pedrofraca.tourapp.model.GetStagesAsyncTask;
import io.github.pedrofraca.tourapp.model.TourStage;

public class MainActivityFragment extends Fragment implements GetStagesListener {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private TourStageAdapter mAdapter;

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
        mAdapter = new TourStageAdapter(stages,getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStagesError(Exception error) {

    }
}
