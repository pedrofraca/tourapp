package com.example.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.example.api.TourScrappingService;
import com.example.api.model.TourStage;
import com.example.data.database.StageDataSource;
import com.example.data.database.StageDbModel;
import com.example.data.model.Resource;
import com.example.data.model.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class StagesRepositoryImplementation implements StagesRepository {

    private TourScrappingService mApi;
    private StageDataSource mDatabase;

    private MutableLiveData<Resource<List<Stage>>> objectMutableLiveData = new MutableLiveData<>();

    public StagesRepositoryImplementation(TourScrappingService api, StageDataSource database) {
        mApi = api;
        mDatabase = database;
    }

    public LiveData<Resource<List<Stage>>> getStages() {

        mDatabase.getStages().observeForever(new Observer<List<StageDbModel>>() {
            @Override
            public void onChanged(List<StageDbModel> stageDbModels) {
                List<Stage> stages = Stream.of(stageDbModels).map(new Function<StageDbModel, Stage>() {
                    @Override
                    public Stage apply(StageDbModel stageDbModel) {
                        return new Stage(stageDbModel.getName(),
                                stageDbModel.getKms(),
                                stageDbModel.getWinner(),
                                stageDbModel.getLeader(),
                                stageDbModel.isCompleted());
                    }
                }).toList();
                Resource<List<Stage>> result = new Resource<>(Resource.Status.CACHE, stages);
                objectMutableLiveData.postValue(result);
            }
        });

        mApi.getStages().enqueue(new retrofit2.Callback<List<TourStage>>() {
            @Override
            public void onResponse(Call<List<TourStage>> call, Response<List<TourStage>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<StageDbModel> stages = Stream.of(response.body()).map(new Function<TourStage, StageDbModel>() {
                            @Override
                            public StageDbModel apply(TourStage tourStage) {
                                StageDbModel stageDbModel = new StageDbModel();
                                stageDbModel.setName(tourStage.getDescription());
                                stageDbModel.setKms(tourStage.getKm());
                                stageDbModel.setWinner(tourStage.getWinner());
                                stageDbModel.setCompleted(!tourStage.getWinner().isEmpty());
                                stageDbModel.setLeader(tourStage.getLeader());
                                return stageDbModel;
                            }
                        }).toList();
                        StageDbModel[] objects = stages.toArray(new StageDbModel[0]);
                        mDatabase.insert(objects);
                    }
                } else {
                    Resource<List<Stage>> resource = new Resource<List<Stage>>(Resource.Status.ERROR, new ArrayList<Stage>());
                    try {
                        resource.setError(new Throwable(response.errorBody().string()));
                    } catch (IOException e) {
                        resource.setError(e);
                    }
                    objectMutableLiveData.postValue(resource);
                }
            }

            @Override
            public void onFailure(Call<List<TourStage>> call, Throwable t) {
                Resource<List<Stage>> resource = new Resource<List<Stage>>(Resource.Status.ERROR, new ArrayList<Stage>());
                resource.setError(t);
                objectMutableLiveData.postValue(resource);
            }
        });

        return objectMutableLiveData;
    }
}
