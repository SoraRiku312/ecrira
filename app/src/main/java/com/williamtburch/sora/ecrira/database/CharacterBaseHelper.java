package com.williamtburch.sora.ecrira.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CharacterBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 6;
    private static final String DATABASE_NAME = "characterBase.db";

    public CharacterBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CharacterDbSchema.CharacterTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                CharacterDbSchema.CharacterTable.Cols.UUID + ", " +
                CharacterDbSchema.CharacterTable.Cols.FNAME + ", " +
                CharacterDbSchema.CharacterTable.Cols.LNAME + ", " +
                CharacterDbSchema.CharacterTable.Cols.AGE + ", " +
                CharacterDbSchema.CharacterTable.Cols.NNAME + ", " +
                CharacterDbSchema.CharacterTable.Cols.DESC + ", " +
                CharacterDbSchema.CharacterTable.Cols.IMAGE + ", " +
                CharacterDbSchema.CharacterTable.Cols.GENDER + ", " +
                CharacterDbSchema.CharacterTable.Cols.BIO + ", " +
                CharacterDbSchema.CharacterTable.Cols.BDAY + ", " +
                CharacterDbSchema.CharacterTable.Cols.ISNEWFORINTERVIEWS + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION0 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER0 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION1 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER1 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION2 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER2 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION3 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER3 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION4 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER4 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION5 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER5 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION6 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER6 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION7 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER7 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION8 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER08 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION9 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER9 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION10 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER10 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION11 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER11 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION12 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER12 + ", " +
                CharacterDbSchema.CharacterTable.Cols.QUESTION13 + ", " +
                CharacterDbSchema.CharacterTable.Cols.ANSWER13 +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            switch (oldVersion) {
                case 1:
                    //        db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                    db.execSQL("DROP TABLE IF EXISTS " + CharacterDbSchema.CharacterTable.NAME);
                    onCreate(db);

                case 2:
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.BIO + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.BDAY + ";");

                case 3:
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION0 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER0 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION1 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER1 + ";");

                case 4:
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION2 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER2 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION3 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER3 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION4 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER4 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION5 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER5 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION6 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER6 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION7 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER7 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION8 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER08 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION9 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER9 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION10 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER10 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION11 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER11 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION12 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER12 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.QUESTION13 + ";");
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ANSWER13 + ";");

                    break;
                case 5:
                    db.execSQL("ALTER TABLE " + CharacterDbSchema.CharacterTable.NAME + " ADD COLUMN " +
                            CharacterDbSchema.CharacterTable.Cols.ISNEWFORINTERVIEWS + ";");
            }
        }
    }
}

