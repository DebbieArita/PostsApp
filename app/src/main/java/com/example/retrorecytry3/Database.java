package com.example.retrorecytry3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Users";
    private static final String TABLE_NAME = "Users";
    private static final String COLUMN_USERID = "userId";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_BODY = "text";

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USERS_TABLE = "CREATE TABLE "
                + TABLE_NAME + "("
                + COLUMN_USERID + "INTEGER PRIMARY KEY, "
                + COLUMN_ID + "INTEGER, "
                + COLUMN_TITLE + "TEXT, "
                + COLUMN_BODY + "TEXT "
                + ")";
        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public List<Post> postList(){
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Post> postingList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.moveToFirst()) {
            do{
                int userId = Integer.parseInt(cursor.getString(0));
                int id = Integer.parseInt(cursor.getString(1));
                String title = cursor.getString(2);
                String body = cursor.getString(3);
                postingList.add(new Post(userId, id, title, body));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return postingList;
    }

    public void addUser (Post post){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERID, post.getUserId());
        values.put(COLUMN_ID, post.getId());
        values.put(COLUMN_TITLE, post.getTitle());
        values.put(COLUMN_BODY, post.getText());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    public void updateUser (Post post){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERID, post.getUserId());
        values.put(COLUMN_ID, post.getId());
        values.put(COLUMN_TITLE, post.getTitle());
        values.put(COLUMN_BODY, post.getText());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME, values, COLUMN_USERID + "=?", new String[]{String.valueOf(post.getUserId())});
    }

    public void deleteUser(int userId){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, COLUMN_USERID + "=?", new String[]{String.valueOf(userId)});
    }

    public void insertPostList(List<Post> postList){
        for (Post post: postList){
            addUser(post);
        }
    }

//    public List<Post> getPostList(){
//
//    }
}
