package io.github.pedrofraca.tourapp.stage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.github.pedrofraca.tourapp.R;

public class StagesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private StageAdapter mAdapter;
    private StagesViewModel viewModel;
    static final String TAG = "STAGES";
    private View mLoadingProgressBar;
    private TextView mErrorTextView;
    private View mErrorLayout;

    public StagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stages, container, false);

        viewModel = ViewModelProviders.of(this,
                new StagesViewModel.StagesViewModelFactory(requireContext())).get(StagesViewModel.class);

        mRecyclerView = rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mLoadingProgressBar = rootView.findViewById(R.id.stages_loading);
        mErrorTextView = rootView.findViewById(R.id.activity_main_text_message);
        mErrorLayout = rootView.findViewById(R.id.stages_error_message);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.stages().observe(getViewLifecycleOwner(), stages -> {
            mAdapter = new StageAdapter(stages, requireActivity());
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            mLoadingProgressBar.setVisibility(View.GONE);
        });

        viewModel.error().observe(getViewLifecycleOwner(), throwable -> showErrorMessage(throwable.getMessage()));
    }

    private void showErrorMessage(String error) {
        mErrorLayout.setVisibility(View.VISIBLE);
        mErrorTextView.setText(error);
    }
}
