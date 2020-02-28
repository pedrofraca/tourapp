package io.github.pedrofraca.tourapp.stage;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.api.ServiceFactory;
import com.example.api.TourScrappingService;
import com.example.data.StagesRepositoryImplementation;
import com.example.data.database.StageDatabaseFactory;
import com.example.data.model.Resource;
import com.example.data.model.Stage;

import java.util.List;

public class StagesViewModel extends ViewModel {

    private StagesRepositoryImplementation repo;
    private MutableLiveData<Throwable> error = new MutableLiveData<>();

    public StagesViewModel(StagesRepositoryImplementation repo) {
        this.repo = repo;
    }

    public LiveData<List<Stage>> stages() {
        return Transformations.map(repo.getStages(), input -> {
            if(input.getStatus()== Resource.Status.ERROR) {
                error.postValue(input.getError());
            }
            return input.getData();
        });
    }

    public LiveData<Throwable> error(){
        return error;
    }

    public static class StagesViewModelFactory implements ViewModelProvider.Factory {

        private Context mContext;

        public StagesViewModelFactory(Context context) {
            mContext = context;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            StagesRepositoryImplementation repository = new StagesRepositoryImplementation(new ServiceFactory().build(TourScrappingService.class), StageDatabaseFactory.getDatabase(mContext).getStageDao());
            return (T) new StagesViewModel(repository);
        }
    }
}


