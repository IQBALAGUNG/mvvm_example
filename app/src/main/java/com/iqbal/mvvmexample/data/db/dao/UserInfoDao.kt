package com.iqbal.mvvmexample.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iqbal.mvvmexample.data.db.entity.USER_ID
import com.iqbal.mvvmexample.data.db.entity.UserInfo

@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upSert(userInfo: UserInfo)

    @Query("SELECT * FROM user_table WHERE id = $USER_ID")
    fun getUser(): LiveData<UserInfo>

    @Query("DELETE FROM user_table WHERE id = $USER_ID")
    fun deleteUser()

}