package com.iqbal.mvvmexample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iqbal.mvvmexample.data.db.dao.UserInfoDao
import com.iqbal.mvvmexample.data.db.entity.UserInfo


@Database(
    entities = [UserInfo::class], version = 1
)
abstract class AppDb : RoomDatabase() {

    abstract fun userInfoDao(): UserInfoDao

    companion object {
        @Volatile
        private var instance: AppDb? = null
        private var LOCK = Any()

        operator fun invoke(ctx: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(ctx).also { instance = it }
        }

        private fun buildDatabase(ctx: Context) =
            Room.databaseBuilder(ctx, AppDb::class.java, "mvvm_app.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}