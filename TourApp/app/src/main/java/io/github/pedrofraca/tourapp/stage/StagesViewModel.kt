package io.github.pedrofraca.tourapp.stage

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.*
import com.pedrofraca.tour.framework.datasource.stage.StagesApiDataSource
import com.pedrofraca.tour.framework.database.TourDatabaseFactory.getDatabase
import io.github.pedrofraca.data.datasource.stage.StageRepository
import com.pedrofraca.tour.framework.datasource.stage.StageDbDataSource
import io.github.pedrofraca.data.datasource.stage.StagesRepositoryImpl
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.parcel.Parcelize

class StagesViewModel(private val repo: StageRepository) : ViewModel() {
    private val error = MutableLiveData<Throwable>()
    private val stagesLiveData = MutableLiveData<List<StageParcelable>>()
    private val compositeDisposable = CompositeDisposable()

    fun stages(): LiveData<List<StageParcelable>> {
        val disposable = Observable.concat(Observable.fromCallable { repo.stages }, Observable.fromCallable { repo.refresh() })
                .filter { it.isNotEmpty() }
                .firstElement()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { stagesList ->
                    val list = ArrayList<StageParcelable>()
                    stagesList.forEach { stage ->
                        list.add(StageParcelable(stage.name,
                                stage.winner,
                                stage.leader,
                                stage.images,
                                stage.description,
                                stage.km,
                                stage.imgUrl,
                                stage.date,
                                averageSpeed = stage.averageSpeed,
                                stage = stage.stage.toInt()?:-1))
                    }
                    list
                }
                .doOnError { error.postValue(it) }
                .subscribe({ t -> stagesLiveData.postValue(t) }, { t -> error.postValue(t) })
        compositeDisposable.add(disposable)
        return stagesLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun error(): LiveData<Throwable> {
        return error
    }
}

class StagesViewModelFactory(private val mContext: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = StagesRepositoryImpl(StagesApiDataSource(),
                StageDbDataSource(getDatabase(mContext)))
        return StagesViewModel(repository) as T
    }
}

@Parcelize
data class StageParcelable(
        val name: String,
        val winner: String? = null,
        val leader: String? = null,
        val images: List<String>? = null,
        val description: String? = null,
        val km: String? = null,
        val imgUrl: String? = null,
        val date: String? = null,
        val stage: Int? = null,
        val averageSpeed: String? = null,
        val startFinish: String? = null) : Parcelable {
    fun completed(): Boolean {
        return winner?.isNotEmpty() ?: false
    }
}