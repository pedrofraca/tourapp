package io.github.pedrofraca.tourapp.classification

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pedrofraca.tour.framework.datasource.classification.ClassificationApiDataSource
import com.pedrofraca.tour.framework.datasource.classification.ClassificationDbDataSource
import io.github.pedrofraca.data.datasource.classification.ClassificationRepository
import io.github.pedrofraca.data.datasource.classification.ClassificationRepositoryImpl
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.parcelize.Parcelize

class ClassificationViewModel(private val repo: ClassificationRepository) : ViewModel() {

    private val mutableStageLiveData = MutableLiveData<StageClassificationModelParcelable>()
    private val compositeDisposable = CompositeDisposable()

    fun getClassificationForStage(stage: String): LiveData<StageClassificationModelParcelable> {
        val observable = Observable.concat(Observable.fromCallable { repo.getClassificationForStage(stage) },
                Observable.fromCallable { repo.refreshForStage(stage) })
                .filter { it.general.isNotEmpty() }
                .firstElement()
                .subscribeOn(Schedulers.io())
                .subscribe {
                    mutableStageLiveData.postValue(
                            StageClassificationModelParcelable(
                                    it.mountain.map { classification -> ClassificationModelParcelable(classification.time, classification.country, classification.team, classification.pos, classification.rider) },
                                    it.team.map { classification -> ClassificationModelParcelable(classification.time, classification.country, classification.team, classification.pos, classification.rider) },
                                    it.general.map { classification -> ClassificationModelParcelable(classification.time, classification.country, classification.team, classification.pos, classification.rider) },
                                    it.regularity.map { classification -> ClassificationModelParcelable(classification.time, classification.country, classification.team, classification.pos, classification.rider) },
                                    it.stageClassification.map { classification -> ClassificationModelParcelable(classification.time, classification.country, classification.team, classification.pos, classification.rider) }
                            ))
                }

        compositeDisposable.add(observable)

        return mutableStageLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}

@Parcelize
data class ClassificationModelParcelable(var time: String?,
                                         var country: String?,
                                         var team: String?,
                                         var pos: String?,
                                         var rider: String?) : Parcelable

@Parcelize
data class StageClassificationModelParcelable(
        var mountain: List<ClassificationModelParcelable>,
        var team: List<ClassificationModelParcelable>,
        var general: List<ClassificationModelParcelable>,
        var regularity: List<ClassificationModelParcelable>,
        var stage: List<ClassificationModelParcelable>) : Parcelable

class ClassificationViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = ClassificationRepositoryImpl(ClassificationApiDataSource(),
                ClassificationDbDataSource(com.pedrofraca.tour.framework.database.TourDatabaseFactory.getDatabase(context)))
        return ClassificationViewModel(repository) as T
    }
}