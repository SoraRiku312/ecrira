package com.williamtburch.sora.ecrira.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WorldBaseHelper extends SQLiteOpenHelper {
        private static final int VERSION = 2;
        private static final String DATABASE_NAME = "worldBase.db";

        public WorldBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + WorldDbSchema.WorldTable.NAME + "(" +
            "_id integer primary key autoincrement, " +
            WorldDbSchema.WorldTable.Cols.UUID + ", " +
                            WorldDbSchema.WorldTable.Cols.WORLDNAME + ", " +
                    WorldDbSchema.WorldTable.Cols.WORLDTYPE + ")"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (newVersion > oldVersion) {
                switch (oldVersion) {
                    case 1:

                        break;
                }
            }
        }
}



