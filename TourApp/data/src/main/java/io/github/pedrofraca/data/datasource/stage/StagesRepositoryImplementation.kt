package io.github.pedrofraca.data.datasource.stage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.pedrofraca.data.datasource.ReadOnlyDataSource
import io.github.pedrofraca.data.datasource.WriteDataSource
import io.github.pedrofraca.data.datasource.model.Resource
import io.github.pedrofraca.domain.model.StageModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class StagesRepositoryImplementation(private val apiDataSource: ReadOnlyDataSource<StageModel>,
                                     private val databaseDataSource: WriteDataSource<StageModel>) : StagesRepository {
    private val objectMutableLiveData = MutableLiveData<Resource<List<StageModel>>>()
    private val compositeDisposable = CompositeDisposable()
    override val stages: LiveData<Resource<List<StageModel>>>
        get() {
            return objectMutableLiveData
        }

    fun refresh() {
        compositeDisposable.add(apiDataSource.get()
                .subscribeOn(Schedulers.io())
                .subscribe { it ->
                    it.forEach {
                        databaseDataSource.save(it)
                    }
                })

        compositeDisposable.add(databaseDataSource.get()
                .subscribeOn(Schedulers.io())
                .subscribe { t -> objectMutableLiveData.postValue(Resource(Resource.Status.OK, t)) })
    }

    fun clear() {
        compositeDisposable.clear()
    }
}