package com.dimetris.roomdatabasetest;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dimetris.roomdatabasetest.Models.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}