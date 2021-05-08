package com.mehmetsaltan.harcamatakipbymehmetsaltan.veritabani

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mehmetsaltan.harcamatakipbymehmetsaltan.BitmapDonustur
import com.mehmetsaltan.harcamatakipbymehmetsaltan.Harcama

@Database(entities = [Harcama::class], version = 23, exportSchema = false)
@TypeConverters(BitmapDonustur::class)
abstract class VeriTabanim : RoomDatabase() {

    abstract fun myDao(): MyDao

    companion object {
        @Volatile
        private var INSTANCE: VeriTabanim? = null

        fun getDatabase(context: Context): VeriTabanim =
            INSTANCE
                ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    )
                        .also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                VeriTabanim::class.java, "my_database"
            ).fallbackToDestructiveMigration ()
                .build()
    }

}