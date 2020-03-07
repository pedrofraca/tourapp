package io.github.pedrofraca.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.pedrofraca.data.datasource.model.Resource
import io.github.pedrofraca.domain.model.StageModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class StagesRepositoryImplementation(private val apiDataSource: StageDataSource) : StagesRepository {
    private val objectMutableLiveData = MutableLiveData<Resource<List<StageModel>>>()
    override val stages: LiveData<Resource<List<StageModel>>>
        get() {
            apiDataSource.get("patata")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).doOnSuccess {
                        objectMutableLiveData.postValue(Resource(Resource.Status.OK, it))
                    }.subscribe()
            return objectMutableLiveData
        }
}