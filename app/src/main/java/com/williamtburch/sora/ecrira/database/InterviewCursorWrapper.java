//package com.williamtburch.sora.ecrira.database;
//
//import android.database.Cursor;
//import android.database.CursorWrapper;
//
//import com.williamtburch.sora.ecrira.Interview;
//
//public class InterviewCursorWrapper extends CursorWrapper {
//    public InterviewCursorWrapper(Cursor cursor){
//        super(cursor);
//    }
//
//    public Interview getInterview(){
//        String question = getString(getColumnIndex(InterviewDbSchema.InterviewTable.Cols.QUESTION));
//        String answer = getString(getColumnIndex(InterviewDbSchema.InterviewTable.Cols.ANSWER));
//
//
//
//        Interview interview = new Interview();
//        interview.setQuestion(question);
//        interview.setAnswer(answer);
//
//
//        return interview;
//    }
//
//}
