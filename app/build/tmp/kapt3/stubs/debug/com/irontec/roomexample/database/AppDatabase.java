package com.irontec.roomexample.database;

import java.lang.System;

/**
 * * Created by axier on 7/2/18.
 */
@android.arch.persistence.room.Database(entities = {com.irontec.roomexample.database.entities.Course.class}, version = 1)
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/irontec/roomexample/database/AppDatabase;", "Landroid/arch/persistence/room/RoomDatabase;", "()V", "courseDao", "Lcom/irontec/roomexample/database/daos/CourseDao;", "Companion", "app_debug"})
public abstract class AppDatabase extends android.arch.persistence.room.RoomDatabase {
    
    /**
     * * The only instance
     */
    private static com.irontec.roomexample.database.AppDatabase sInstance;
    public static final com.irontec.roomexample.database.AppDatabase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.irontec.roomexample.database.daos.CourseDao courseDao();
    
    public AppDatabase() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/irontec/roomexample/database/AppDatabase$Companion;", "", "()V", "sInstance", "Lcom/irontec/roomexample/database/AppDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        /**
         * * Gets the singleton instance of SampleDatabase.
         *         *
         *         * @param context The context.
         *         * @return The singleton instance of SampleDatabase.
         */
        @org.jetbrains.annotations.NotNull()
        public final synchronized com.irontec.roomexample.database.AppDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}