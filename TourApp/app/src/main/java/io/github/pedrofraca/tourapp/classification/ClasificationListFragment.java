package io.github.pedrofraca.tourapp.classification;


import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.github.pedrofraca.tourapp.R;
import io.github.pedrofraca.tourapp.adapter.TourClasificationAdapter;

/**
 * Created by pedrofraca on 14/07/15.
 */
public class ClasificationListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private TourClasificationAdapter mAdapter;

    private static final String ATTR_DATASET="ATTR_DATASET";

    public ClasificationListFragment newInstance(ArrayList<? extends Parcelable> dataSet){
        ClasificationListFragment fragment = new ClasificationListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ATTR_DATASET, dataSet);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_clasification, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        Bundle args = getArguments();
        if(args!=null){
            ArrayList<Parcelable> parcelableArrayList = args.getParcelableArrayList(ATTR_DATASET);

            mAdapter = new TourClasificationAdapter(parcelableArrayList,getActivity());
            mRecyclerView.setAdapter(mAdapter);
        }

//        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        return rootView;
    }
}
