package io.github.pedrofraca.tourapp.model;

import android.os.AsyncTask;

import java.util.List;

import io.github.pedrofraca.tourapp.GetStagesListener;

public class GetStagesAsyncTask extends AsyncTask<Void,Void,Exception>{

    private GetStagesListener mListener;
    private List<TourStage> mStages;

    public GetStagesAsyncTask(GetStagesListener listener){
        mListener = listener;
    }

    @Override
    protected Exception doInBackground(Void... voids) {
        try {
            mStages= new TourScrappingServiceFactory().build().getStages();
        } catch (Exception e){
            return e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Exception ex) {
        if(ex!=null){
            if(mListener!=null){
                mListener.onStagesError(ex);
            }
        } else {
            if(mListener!=null){
                mListener.onStagesReceived(mStages);
            }
        }
        super.onPostExecute(ex);
    }
}
