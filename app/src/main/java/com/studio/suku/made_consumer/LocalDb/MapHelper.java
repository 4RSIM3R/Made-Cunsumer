package com.studio.suku.made_consumer.LocalDb;

import android.database.Cursor;

import java.util.ArrayList;

import static com.studio.suku.made_consumer.LocalDb.DatabaseContract.Entry.COLUMN_NAME;

public class MapHelper {
    public static ArrayList<FavoriteItem> mapCursorToArrayList(Cursor cursor) {
        ArrayList<FavoriteItem> notesList = new ArrayList<>();
        FavoriteItem favorite;
        do {

            favorite = new FavoriteItem();
            favorite.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Entry.COLUMN_NAME)));
            favorite.setImage(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Entry.COLUMN_IMAGE)));
            favorite.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Entry.COLUMN_OVERVIEW)));
            favorite.setRate(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.Entry.COLUMN_RATE)));
            favorite.setType(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Entry.COLUMN_TYPE)));
            notesList.add(favorite);
            cursor.moveToNext();
        }while (!cursor.isAfterLast());

        return notesList;
    }
}
