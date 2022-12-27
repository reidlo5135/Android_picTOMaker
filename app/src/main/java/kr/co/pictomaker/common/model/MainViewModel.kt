package kr.co.pictomaker.common.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val Repository: Repository = Repository(AppDataBase.getDatabase(application, viewModelScope))

    var allUsers: LiveData<List<Entity>> = Repository.allUsers


    fun insert(entity: Entity) = viewModelScope.launch(Dispatchers.IO) {
        println("mainViewModel insert entity : $entity")
        Repository.insert(entity)
    }


    fun deleteAll(entity: Entity) = viewModelScope.launch(Dispatchers.IO) {
        Repository.delete(entity)
    }

    fun getAll(): LiveData<List<Entity>> {
        return allUsers
    }

}