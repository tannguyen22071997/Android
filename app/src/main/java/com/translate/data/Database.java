package com.translate.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.translate.model.Group_class;
import com.translate.model.Part;
import com.translate.model.Vocabulary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlGC = "CREATE TABLE IF NOT EXISTS Group_class(Id INTEGER PRIMARY KEY ," +
                "Name VARCHAR(200)," +
                "Type VARCHAR(200))";
        String sqlP = "CREATE TABLE IF NOT EXISTS Part(Id INTEGER PRIMARY KEY ," +
                "Name VARCHAR(200)," +
                "IdClass INTEGER REFERENCES Group_class(Id))";
        String sqlV = "CREATE TABLE IF NOT EXISTS Vocabulary(Id INTEGER PRIMARY KEY ," +
                "word VARCHAR(100)," +
                "mean VARCHAR(100)," +
                "spelling VARCHAR(100)," +
                "type VARCHAR(100)," +
                "audioFile VARCHAR(100)," +
                "IdP INTEGER REFERENCES Part(Id))";
        db.execSQL(sqlGC);
        db.execSQL(sqlP);
        db.execSQL(sqlV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteTable() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("drop table Group_class");
        sqLiteDatabase.execSQL("drop table Part");
        sqLiteDatabase.execSQL("drop table Vocabulary");
        onCreate(sqLiteDatabase);
        sqLiteDatabase.close();

    }

    public void insertGroup_class(Group_class group_class) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id", group_class.getId());
        values.put("Name", group_class.getName());
        values.put("Type", group_class.getType());
        database.insert("Group_class", null, values);
        database.close();

    }

    public void insertPart(Part part) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id", part.getId());
        values.put("Name", part.getName());
        values.put("IdClass", part.getIdClass());
        database.insert("Part", null, values);
        database.close();
    }

    public void insertVocub(Vocabulary vocabulary) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id", vocabulary.getId());
        values.put("word", vocabulary.getWord());
        values.put("mean", vocabulary.getMean());
        values.put("spelling", vocabulary.getSpelling());
        values.put("type", vocabulary.getType());
        values.put("audioFile", vocabulary.getAudioFile());
        values.put("IdP",vocabulary.getIdP());
        database.insert("Vocabulary", null, values);
        database.close();
    }

    public boolean checknull() {
        String sql = "select * from Group_class";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        return !cursor.moveToNext();
    }

    public List<Group_class> getListClass(String type) {
        List<Group_class> group_classList = new ArrayList<>();
        String sql = "select * from Group_class where Type='" + type + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            group_classList.add(new Group_class(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
        }
        return group_classList;

    }

    public List<Part> getListPart(int id) {
        List<Part> partList = new ArrayList<>();
        String sql = "select * from Part where IdClass=" + id + "";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            partList.add(new Part(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2)
            ));
        }
        return partList;

    }

    public List<Vocabulary> getListVocabu(int id) {
        List<Vocabulary> vocabularyList = new ArrayList<>();
        String sql = "select * from Vocabulary where Idp="+id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            vocabularyList.add(new Vocabulary(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6)
            ));
        }
        return vocabularyList;

    }


}
