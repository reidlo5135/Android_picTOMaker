package kr.co.pictomaker.common.model

import androidx.lifecycle.LiveData

class Repository(mDataBase: AppDataBase) {

    private val dao = mDataBase.dao()
    val allUsers: LiveData<List<Entity>> = dao.getAll()
    companion object {
        private var sInstance: Repository? = null
        fun getInstance(dataBase: AppDataBase): Repository {
            return sInstance
                ?: synchronized(this) {
                    val instance = Repository(dataBase)
                    sInstance = instance
                    instance
                }
        }
    }

    suspend fun insert(entity: Entity) {
        println("repo insert entity : $entity")
        dao.insert(entity)
    }

    suspend fun delete(entity: Entity) {
        dao.delete(entity)
    }
}