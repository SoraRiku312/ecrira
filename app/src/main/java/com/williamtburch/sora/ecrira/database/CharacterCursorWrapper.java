package com.williamtburch.sora.ecrira.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.williamtburch.sora.ecrira.Character;

import java.util.UUID;

public class CharacterCursorWrapper extends CursorWrapper {
    public CharacterCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Character getCharacter(){
        String uuidString = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.UUID));
        String firstName = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.FNAME));
        String lastName = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.LNAME));
        String age = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.AGE));
        String nickName = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.NNAME));
        String desc = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.DESC));
        String image = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.IMAGE));
        String gender = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.GENDER));
        String bio = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.BIO));
        String isNewForInterviews = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ISNEWFORINTERVIEWS));
        String question0 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION0));
        String answer0 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER0));
        String question1 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION1));
        String answer1 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER1));
        String question2 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION2));
        String answer2 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER2));
        String question3 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION3));
        String answer3 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER3));
        String question4 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION4));
        String answer4 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER4));
        String question5 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION5));
        String answer5 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER5));
        String question6 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION6));
        String answer6 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER6));
        String question7 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION7));
        String answer7 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER7));
        String question8 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION8));
        String answer8 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER08));
        String question9 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION9));
        String answer9 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER9));
        String question10 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION10));
        String answer10 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER10));
        String question11 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION11));
        String answer11 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER11));
        String question12 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION12));
        String answer12 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER12));
        String question13 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.QUESTION13));
        String answer13 = getString(getColumnIndex(CharacterDbSchema.CharacterTable.Cols.ANSWER13));

        Character character = new Character(UUID.fromString(uuidString));
        character.setFirstName(firstName);
        character.setLastName(lastName);
        character.setAge(Integer.valueOf(age));
        character.setNickName(nickName);
        character.setDescription(desc);
        character.setImageURI(image);
        character.setGender(gender);
        character.setBiography(bio);
        if(isNewForInterviews != null) {
            if (isNewForInterviews.equals("1")) {
                character.setNewForInterviews(true);
            } else {
                character.setNewForInterviews(false);
            }
        }
        character.addInterview(0, question0, answer0);
        character.addInterview(1, question1, answer1);
        character.addInterview(2, question2, answer2);
        character.addInterview(3, question3, answer3);
        character.addInterview(4, question4, answer4);
        character.addInterview(5, question5, answer5);
        character.addInterview(6, question6, answer6);
        character.addInterview(7, question7, answer7);
        character.addInterview(8, question8, answer8);
        character.addInterview(9, question9, answer9);
        character.addInterview(10, question10, answer10);
        character.addInterview(11, question11, answer11);
        character.addInterview(12, question12, answer12);
        character.addInterview(13, question13, answer13);
        return character;
    }
}
