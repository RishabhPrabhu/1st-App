package com.example.f1.picado.Model;


    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    import com.example.f1.picado.Data.TaskData;

    import java.util.ArrayList;

    /**
     * Created by shadab on 6/16/2016.
     */
    public class Tasks extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "tasks.db";
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_ONE = "id";
        public static final String COLUMN_TWO = "title";
        public static final String COLUMN_THREE= "description";
        public static final String COLUMN_FOUR = "contact_number";
        public static final String COLUMN_FIVE = "image_path";
        public static final String COLUMN_SIX = "is_alarm";
        public static final String COLUMN_SEVEN= "alarm_time";
        public static final String COLUMN_EIGHT = "alarm_date";
        public static final String COLUMN_NINE = "repeat_status";
        public static final String COLUMN_TEN = "type";

        public SQLiteDatabase db;

        public Tasks(Context context)
        {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            this.db = db;
            db.execSQL(
                    "create table  " +TABLE_NAME +
                            "("+ COLUMN_ONE +" id, "+COLUMN_TWO+" title ,"+COLUMN_THREE+" description, "+COLUMN_FOUR+" contact_number, "+COLUMN_FIVE+" image_path, "+COLUMN_SIX+" is_alarm, "+COLUMN_SEVEN+" alarm_time, "+COLUMN_EIGHT+" alarm_date, "+COLUMN_NINE+" repeat_status, "+COLUMN_TEN+" type, )"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }

        public boolean insertData  ( String column_two,String column_three, String column_four, String column_five, String column_six, String column_seven, String column_eight, String column_nine, String column_ten )
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(COLUMN_TWO, column_two);
            contentValues.put(COLUMN_THREE, column_three);
            contentValues.put(COLUMN_FOUR, column_four);
            contentValues.put(COLUMN_FIVE, column_five);
            contentValues.put(COLUMN_SIX, column_six);
            contentValues.put(COLUMN_SEVEN, column_seven);
            contentValues.put(COLUMN_EIGHT, column_eight);
            contentValues.put(COLUMN_NINE, column_nine);
            contentValues.put(COLUMN_TEN, column_ten);

            db.insert(TABLE_NAME, null, contentValues);

            return true;
        }


    /*public void cleanData(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(
                "create table  if not exists " +TABLE_NAME +
                        "("+ COLUMN_ONE +" integer primary key, "+COLUMN_TWO+" text )"
        );

        db.execSQL( "delete from "+TABLE_NAME );
        db.close();
    }*/

        public ArrayList<TaskData> getAllData()
        {
            SQLiteDatabase db = this.getReadableDatabase();

            db.execSQL(
                    "create table  if not exists " +TABLE_NAME +
                            "("+ COLUMN_ONE +" id, "+COLUMN_TWO+" title ,"+COLUMN_THREE+" description, "+COLUMN_FOUR+" contact_number, "+COLUMN_FIVE+" image_path, "+COLUMN_SIX+" is_alarm, "+COLUMN_SEVEN+" alarm_time, "+COLUMN_EIGHT+" alarm_date, "+COLUMN_NINE+" repeat_status, "+COLUMN_TEN+" type, )"
            );

            Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
            res.moveToFirst();
            String linesText="";

            ArrayList<TaskData> data = new ArrayList<>();

            while(res.isAfterLast() == false){
                TaskData taskData = new TaskData();
                taskData.setTitle(res.getString(res.getColumnIndex(COLUMN_TWO)));
                taskData.setDescription(res.getString(res.getColumnIndex(COLUMN_THREE)));
                taskData.setContactNumber(res.getString(res.getColumnIndex(COLUMN_FOUR)));
                taskData.setImage_path(res.getString(res.getColumnIndex(COLUMN_FIVE)));
                taskData.setIs_alarm(res.getString(res.getColumnIndex(COLUMN_SIX)));
                taskData.setAlarm_time(res.getString(res.getColumnIndex(COLUMN_SEVEN)));
                taskData.setAlarm_date(res.getString(res.getColumnIndex(COLUMN_EIGHT)));
                taskData.setRepeat_status(res.getString(res.getColumnIndex(COLUMN_NINE)));
                taskData.setType(res.getString(res.getColumnIndex(COLUMN_TEN)));




                data.add(taskData);
                res.moveToNext();
            }

            return data;
        }

    }
