package io.github.pedrofraca.tourapp.classification

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.pedrofraca.data.datasource.classification.ClassificationRepository
import io.github.pedrofraca.data.datasource.classification.ClassificationRepositoryImpl
import io.github.pedrofraca.domain.model.ClassificationModel
import io.github.pedrofraca.domain.model.StageClassificationModel
import io.github.pedrofraca.tourapp.framework.datasource.ClassificationApiDataSource
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.parcel.Parcelize

class ClassificationViewModel(private val repo : ClassificationRepository) : ViewModel() {

    private val mutableStageLiveData = MutableLiveData<StageClassificationModelParcelable>()
    private val compositeDisposable = CompositeDisposable()

    fun getClassificationForStage(stage: String) : LiveData<StageClassificationModelParcelable> {
        compositeDisposable.add(Observable.fromCallable { repo.getClassificationForStage(stage) }
                .subscribeOn(Schedulers.io())
                .subscribe { mutableStageLiveData.postValue(
                        StageClassificationModelParcelable(
                                it.mountain.map {  classification -> ClassificationModelParcelable(classification.time, classification.country, classification.team, classification.pos, classification.rider)  },
                                it.team.map {  classification -> ClassificationModelParcelable(classification.time, classification.country, classification.team, classification.pos, classification.rider)  },
                                it.general.map {  classification -> ClassificationModelParcelable(classification.time, classification.country, classification.team, classification.pos, classification.rider)  },
                                it.regularity.map { classification -> ClassificationModelParcelable(classification.time, classification.country, classification.team, classification.pos, classification.rider) },
                                it.stage.map { classification -> ClassificationModelParcelable(classification.time, classification.country, classification.team, classification.pos, classification.rider) }
                        )) })

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

class ClassificationViewModelFactory(private val mContext: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = ClassificationRepositoryImpl(ClassificationApiDataSource(), null)
        return ClassificationViewModel(repository) as T
    }
}