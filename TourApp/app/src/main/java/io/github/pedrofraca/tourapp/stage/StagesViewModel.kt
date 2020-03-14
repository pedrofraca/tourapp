package io.github.pedrofraca.tourapp.stage

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.*
import io.github.pedrofraca.data.datasource.api.StagesApiDataSource
import io.github.pedrofraca.data.datasource.database.StageDatabaseFactory.getDatabase
import io.github.pedrofraca.data.datasource.model.Resource
import io.github.pedrofraca.data.datasource.stage.StageDbDataSource
import io.github.pedrofraca.data.datasource.stage.StagesRepositoryImplementation
import io.github.pedrofraca.domain.model.StageModel
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

class StagesViewModel(private val repo: StagesRepositoryImplementation) : ViewModel() {
    private val error = MutableLiveData<Throwable?>()
    fun stages(): LiveData<List<StageParcelable>> {
        repo.refresh()
        return Transformations.map(repo.stages) { input: Resource<List<StageModel>> ->
            if (input.status === Resource.Status.ERROR) {
                error.postValue(input.error)
            }
            input.getData<List<StageModel>>()?.map {
                StageParcelable(it.name, it.winner, it.leader, it.images, it.description, it.km, it.imgUrl, it.date)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        repo.clear()
    }

    fun error(): LiveData<Throwable?> {
        return error
    }

    class StagesViewModelFactory(private val mContext: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val repository = StagesRepositoryImplementation(StagesApiDataSource(),
                    StageDbDataSource(getDatabase(mContext)))
            return StagesViewModel(repository) as T
        }

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
        val stage: String? = null,
        val averageSpeed: String? = null,
        val startFinish: String? = null) : Parcelable {
    fun completed(): Boolean {
        return winner?.isNotEmpty()?:false
    }
}