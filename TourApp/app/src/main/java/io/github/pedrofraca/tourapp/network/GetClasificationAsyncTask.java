package io.github.pedrofraca.tourapp.network;

import android.os.AsyncTask;

import io.github.pedrofraca.tourapp.model.TourStageClasification;

public class GetClasificationAsyncTask extends AsyncTask<Void,Void,Exception>{

    private GetClasificationStageListener mListener;
    private String mStage;
    private TourStageClasification mClasification;

    public GetClasificationAsyncTask(GetClasificationStageListener listener,String stage){
        mListener = listener;
        mStage = stage;
    }

    @Override
    protected Exception doInBackground(Void... voids) {
        try {
            mClasification= new TourScrappingServiceFactory().
                    build().
                    getClasificationForStage(mStage);
        } catch (Exception e){
            return e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Exception ex) {
        if(ex!=null){
            if(mListener!=null){
                mListener.onClasificationError(ex);
            }
        } else {
            if(mListener!=null){
                mListener.onClasificationReceived(mClasification);
            }
        }
        super.onPostExecute(ex);
    }
}
