package kr.co.pictomaker.common.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAO {
    @Query("SELECT * FROM user_table")
    fun getAll(): LiveData<List<Entity>>

    @Insert
    fun insert(entity: Entity)

    // 데이터 전체 삭제
    @Query("DELETE FROM user_table")
    fun deleteAll()

    // 데이터 업데이트
    @Update
    fun update(entity: Entity)

    // 데이터 삭제
    @Delete
    fun delete(entity: Entity)
}