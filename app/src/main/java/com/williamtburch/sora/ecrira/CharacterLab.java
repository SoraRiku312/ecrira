package com.williamtburch.sora.ecrira;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.williamtburch.sora.ecrira.database.CharacterBaseHelper;
import com.williamtburch.sora.ecrira.database.CharacterCursorWrapper;
import com.williamtburch.sora.ecrira.database.CharacterDbSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CharacterLab {

    private static CharacterLab sCharacterLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CharacterLab get(Context context){
        if(sCharacterLab == null){
            sCharacterLab = new CharacterLab(context);
        }
        return sCharacterLab;
    }

    private CharacterLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CharacterBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addCharacter(Character c){
        ContentValues values = getBasicContentValues(c);

        mDatabase.insert(CharacterDbSchema.CharacterTable.NAME, null, values);
    }

    public void deleteCharacter(Character c){
        mDatabase.delete(CharacterDbSchema.CharacterTable.NAME,
                CharacterDbSchema.CharacterTable.Cols.UUID + " = ?",
                new String []{c.getID().toString()}
                );
    }

    public List<Character> getCharacters(){
        List<Character>characters = new ArrayList<>();

        CharacterCursorWrapper cursor = queryCharacters(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                characters.add(cursor.getCharacter());
                cursor.moveToNext();
            }
        } finally{
            cursor.close();
        }
        return characters;
    }

    public Character getCharacter(UUID id){
        CharacterCursorWrapper cursor = queryCharacters(
                CharacterDbSchema.CharacterTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );
        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCharacter();
        } finally{
            cursor.close();
        }
    }

    public File getPhotoFile(Character character){
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, character.getPhotoFileName());
    }

    public void updateCharacterBasics(Character character){
        String uuidString = character.getID().toString();
        ContentValues values = getBasicContentValues(character);

        mDatabase.update(CharacterDbSchema.CharacterTable.NAME, values,
                CharacterDbSchema.CharacterTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public void updateCharacterBio(Character character){
        String uuidString = character.getID().toString();
        ContentValues values = getBioContentValues(character);

        mDatabase.update(CharacterDbSchema.CharacterTable.NAME, values,
                CharacterDbSchema.CharacterTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private static ContentValues getBioContentValues(Character character){
        ContentValues values = new ContentValues();
        values.put(CharacterDbSchema.CharacterTable.Cols.BIO, character.getBiography());

        return values;

    }

    public void updateCharacterInterview(Character character){
        String uuidString = character.getID().toString();

        ContentValues values = getInterviewContentValues(character);

        mDatabase.update(CharacterDbSchema.CharacterTable.NAME, values,
                CharacterDbSchema.CharacterTable.Cols.UUID + " =?",
                new String[]{uuidString});
    }

    private static ContentValues getInterviewContentValues(Character character){
        ContentValues values = new ContentValues();



        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION0, character.getInterview(0).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER0, character.getInterview(0).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION1, character.getInterview(1).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER1, character.getInterview(1).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION2, character.getInterview(2).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER2, character.getInterview(2).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION3, character.getInterview(3).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER3, character.getInterview(3).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION4, character.getInterview(4).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER4, character.getInterview(4).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION5, character.getInterview(5).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER5, character.getInterview(5).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION6, character.getInterview(6).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER6, character.getInterview(6).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION7, character.getInterview(7).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER7, character.getInterview(7).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION8, character.getInterview(8).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER08, character.getInterview(8).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION9, character.getInterview(9).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER9, character.getInterview(9).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION10, character.getInterview(10).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER10, character.getInterview(10).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION10, character.getInterview(11).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER11, character.getInterview(11).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION12, character.getInterview(12).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER12, character.getInterview(12).getAnswer());
        values.put(CharacterDbSchema.CharacterTable.Cols.QUESTION13, character.getInterview(13).getQuestion());
        values.put(CharacterDbSchema.CharacterTable.Cols.ANSWER13, character.getInterview(13).getAnswer());
        return values;
    }

    public void updateCharacterIsNewForInterview(Character character){
        String uuidString = character.getID().toString();

        ContentValues values = getIsNewForInterviewContentValues(character);

        mDatabase.update(CharacterDbSchema.CharacterTable.NAME, values,
                CharacterDbSchema.CharacterTable.Cols.UUID + " =?",
                new String[]{uuidString});
    }
    private static ContentValues getIsNewForInterviewContentValues(Character character){
        ContentValues values = new ContentValues();

        values.put(CharacterDbSchema.CharacterTable.Cols.ISNEWFORINTERVIEWS, character.isNewForInterviews());

        return values;
    }



    //todo updateCharacterBio(), updateCharacterAppear(), getBioContentValues(), getAppearContentValues(), etc for each tab;
    private CharacterCursorWrapper queryCharacters(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CharacterDbSchema.CharacterTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CharacterCursorWrapper(cursor);
    }

    private static ContentValues getBasicContentValues(Character character){
        ContentValues values = new ContentValues();
        values.put(CharacterDbSchema.CharacterTable.Cols.UUID, character.getID().toString());
        values.put(CharacterDbSchema.CharacterTable.Cols.FNAME, character.getFirstName());
        values.put(CharacterDbSchema.CharacterTable.Cols.LNAME, character.getLastName());
        values.put(CharacterDbSchema.CharacterTable.Cols.AGE, character.getAgeAsString());
        values.put(CharacterDbSchema.CharacterTable.Cols.DESC, character.getDescription());
        values.put(CharacterDbSchema.CharacterTable.Cols.NNAME, character.getNickName());
        values.put(CharacterDbSchema.CharacterTable.Cols.GENDER, character.getGender());
        values.put(CharacterDbSchema.CharacterTable.Cols.IMAGE, character.getImageURI());

        values.put(CharacterDbSchema.CharacterTable.Cols.ISNEWFORINTERVIEWS, character.isNewForInterviews());

        return values;
    }
}
