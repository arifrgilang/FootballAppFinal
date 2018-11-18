package com.rz.rz.footballappfinal.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx,
    "FavTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance =
                        MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables if not exists
        db.createTable(
            FavMatch.TABLE_FAVORITE, true,
            FavMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavMatch.TEAM_ID to TEXT + UNIQUE,
            FavMatch.DATE_EVENT to TEXT,
            FavMatch.HOME_NAME to TEXT,
            FavMatch.HOME_SCORE to TEXT,
            FavMatch.HOME_GOAL to TEXT,
            FavMatch.HOME_LINEUP_GK to TEXT,
            FavMatch.HOME_LINEUP_DEF to TEXT,
            FavMatch.HOME_LINEUP_MID to TEXT,
            FavMatch.HOME_LINEUP_FW to TEXT,
            FavMatch.AWAY_NAME to TEXT,
            FavMatch.AWAY_SCORE to TEXT,
            FavMatch.AWAY_GOAL to TEXT,
            FavMatch.AWAY_LINEUP_GK to TEXT,
            FavMatch.AWAY_LINEUP_DEF to TEXT,
            FavMatch.AWAY_LINEUP_MID to TEXT,
            FavMatch.AWAY_LINEUP_FW to TEXT
        )

        db.createTable(FavTeam.TABLE_FAVORITE, true,
            FavTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavTeam.TEAM_ID to TEXT + UNIQUE,
            FavTeam.TEAM_NAME to TEXT,
            FavTeam.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavMatch.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)

