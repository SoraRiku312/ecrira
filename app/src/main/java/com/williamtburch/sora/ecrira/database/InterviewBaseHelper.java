//package com.williamtburch.sora.ecrira.database;
//
//        import android.content.Context;
//        import android.database.sqlite.SQLiteDatabase;
//        import android.database.sqlite.SQLiteOpenHelper;
//
//public class InterviewBaseHelper extends SQLiteOpenHelper {
//    private static final int VERSION = 1;
//    private static final String DATABASE_NAME = "interviewBase.db";
//
//    public InterviewBaseHelper(Context context){
//        super(context, DATABASE_NAME, null, VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db){
//        db.execSQL("create table " + InterviewDbSchema.InterviewTable.NAME + "(" +
//                "_id integer primary key autoincrement, " +
//                InterviewDbSchema.InterviewTable.Cols.QUESTION + ", " +
//                InterviewDbSchema.InterviewTable.Cols.ANSWER +
//                ")"
//        );
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
//
//    }
//}
