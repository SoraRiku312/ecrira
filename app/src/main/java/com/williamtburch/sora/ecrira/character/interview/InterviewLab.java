//package com.williamtburch.sora.ecrira.character.interview;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.williamtburch.sora.ecrira.database.InterviewBaseHelper;
//import com.williamtburch.sora.ecrira.database.InterviewCursorWrapper;
//import com.williamtburch.sora.ecrira.database.InterviewDbSchema;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class InterviewLab {
//
//
//        private static InterviewLab sInterviewLab;
//
//        private Context mContext;
//        private SQLiteDatabase mDatabase;
//
//        public static InterviewLab get(Context context){
//            if(sInterviewLab == null){
//                sInterviewLab = new InterviewLab(context);
//            }
//            return sInterviewLab;
//        }
//
//        private InterviewLab(Context context){
//            mContext = context.getApplicationContext();
//            mDatabase = new InterviewBaseHelper(mContext)
//                    .getWritableDatabase();
//        }
//
//
//        public void addInterview(Interview i){
//            ContentValues values = getBasicContentValues(i);
//
//            mDatabase.insert(InterviewDbSchema.InterviewTable.NAME, null, values);
//        }
//
//        public void deleteInterview(Interview i){
//
//        }
//    public List<Interview> getInterviews(){
//        List<Interview>interviews = new ArrayList<>();
//
//        InterviewCursorWrapper cursor = queryInterviews(null, null);
//
//        try{
//            cursor.moveToFirst();
//            while(!cursor.isAfterLast()){
//                interviews.add(cursor.getInterview());
//                cursor.moveToNext();
//            }
//        } finally{
//            cursor.close();
//        }
//        return interviews;
//    }
//
//    private InterviewCursorWrapper queryInterviews(String whereClause, String[] whereArgs){
//            Cursor cursor = mDatabase.query(
//                    InterviewDbSchema.InterviewTable.NAME,
//                    null,
//                    whereClause,
//                    whereArgs,
//                    null,
//                    null,
//                    null
//                    );
//            return new InterviewCursorWrapper(cursor);
//    }
//
//    private static ContentValues getBasicContentValues(Interview interview){
//            ContentValues values = new ContentValues();
//            values.put(InterviewDbSchema.InterviewTable.Cols.QUESTION, interview.getQuestion());
//            values.put(InterviewDbSchema.InterviewTable.Cols.ANSWER, interview.getAnswer());
//
//            return values;
//    }
//
//}
