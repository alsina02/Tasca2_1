package com.example.tasca2_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "db_kanye", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Al crear-se la BBDD, crea la taula incidencies
        db.execSQL("CREATE TABLE user (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "musica boolean NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    private void crearUser(boolean musica) {
        SQLiteDatabase db = getWritableDatabase();
        crearUsers(db, musica);
    }

    private void crearUsers(SQLiteDatabase db, boolean musica) {
        ContentValues cv = new ContentValues();
        cv.put("musica", musica);
        db.insertOrThrow("user", null, cv);
        System.out.println("Usuari creat");
    }

    public void cargarDades() {
        if (!hasUser()) {
            crearUser(true);
        }
    }

    public boolean hasUser() {
        SQLiteDatabase db = this.getReadableDatabase();

        // Genera consulta i la guarda en un cursor
        Cursor cursorCourses = db.rawQuery("SELECT * FROM user", null);

        // Mou el curso a la primera posició
        if (cursorCourses.moveToFirst()) {
            do {
                System.out.println("Musica: " + cursorCourses.getInt(1));
                return true;
            } while (cursorCourses.moveToNext());
        } else {
            System.out.println("Tamo fuera: ");
            cursorCourses.close();
            return false;
        }
    }

    public boolean isMusicaEnabled() {
        SQLiteDatabase db = this.getReadableDatabase();

        // Genera consulta i la guarda en un cursor
        Cursor cursorCourses = db.rawQuery("SELECT * FROM user", null);

        cursorCourses.moveToFirst();
        // Mou el curso a la primera posició
        do {
            System.out.println("Musica: " + cursorCourses.getInt(1));
            if (cursorCourses.getInt(1) == 1) {
                return true;
            } else {
                return false;
            }
        } while (cursorCourses.moveToNext());
    }

    public void enableMusica() {
        SQLiteDatabase db = this.getWritableDatabase();

        int p = 1;
        System.out.println("Musica: " + p);
        ContentValues values = new ContentValues();
        values.put("musica", p);

        db.update("user", values, "id=1", null);
        db.close();
    }

    public void disableMusica() {
        SQLiteDatabase db = this.getWritableDatabase();

        int p = 0;
        System.out.println("Musica: " + p);
        ContentValues values = new ContentValues();
        values.put("musica", p);

        db.update("user", values, "id=1", null);
        db.close();
    }
}