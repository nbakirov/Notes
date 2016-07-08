package kg.magnit.notes2016;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class NotesDatabase extends SQLiteOpenHelper{


    private static final String DATABASE_NAME = "noteApp";
    final String LOG_TAG = "nb_log";
    private static final int DATABASE_VERSION = 3;


    public NotesDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(LOG_TAG, "Создание БД");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sQuery = "CREATE TABLE noteDatabase (id integer primary key autoincrement,notes_text text, noteDate text, longtd text, latitd text);";
        db.execSQL(sQuery);

    }

    public void addNote (Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("notes_text", note.getNote_text());
        cv.put("noteDate", note.getNote_date());
        cv.put("longtd", note.getLongt());
        cv.put("latitd", note.getLatit());
        db.insert("noteDatabase", null, cv);
        Log.d(LOG_TAG, note.toString());

        db.close();
    }

    public void deleteAllNotes (){
        Log.d(LOG_TAG, "вызвана функция deleteAllNotes");
        SQLiteDatabase db = this.getWritableDatabase();
        String dan = "DROP TABLE noteDatabase;";
        db.execSQL(dan);
        Log.d(LOG_TAG, "очищена база deleteAllNotes");
        onCreate(db);
        Log.d(LOG_TAG, " база повторно создана ");


    }

    public List<Note> getAllNotes() {
        Log.d(LOG_TAG, "вход в функцию getAllNote - список");
        List<Note> noteList = new ArrayList<Note>();

        String selectQuery = "SELECT *  FROM noteDatabase ORDER BY id DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

         if (cursor.moveToFirst()) {

            do {
                Note note = new Note();
                note.setId(Integer.valueOf(cursor.getString(0)));
                note.setNote_text(cursor.getString(1));
                note.setNote_date(cursor.getString(2));

             //   Log.d(LOG_TAG, " note toString" + note.toString());
                noteList.add(note);


            } while (cursor.moveToNext());

        }
        db.close();
        return noteList;

    }
    public List<Note> getAllNotesForMap() {
        Log.d(LOG_TAG, "вход в функцию getAllNote - список");
        List<Note> noteList = new ArrayList<Note>();

        String selectQuery = "SELECT *  FROM noteDatabase ORDER BY id DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {
                Note note = new Note();
                note.setId(Integer.valueOf(cursor.getString(0)));
                note.setNote_text(cursor.getString(1));
                note.setNote_date(cursor.getString(2));
                note.setLongt(Double.valueOf(cursor.getString(3)));
                note.setLatit(Double.valueOf(cursor.getString(4)));

                //   Log.d(LOG_TAG, " note toString" + note.toString());
                noteList.add(note);


            } while (cursor.moveToNext());

        }
        db.close();
        return noteList;

    }
    public void updateNoteByDate(String date, String updated_note_text) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("notes_text", updated_note_text);
        //"UPDATE notes set note_text = Новый_ТЕКСТ WHERE date = ДАТА_ЗАМЕТКИ"
        String where = "noteDate=?";
        String[] whereArgs = new String[]{date};
        //whereArgs = "date= МОЯ_ДАТА_ПЕРЕДАВАЕМАЯ_В_ФУНКЦИЮ"
        db.update("noteDatabase", cv, where, whereArgs);
        Log.d(LOG_TAG, "Note: " + updated_note_text.toString());
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
