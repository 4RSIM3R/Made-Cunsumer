package com.studio.suku.made_consumer.LocalDb;

import android.database.Cursor;

import java.util.ArrayList;

import static com.studio.suku.made_consumer.LocalDb.DatabaseContract.Entry.COLUMN_NAME;

public class MapHelper {
    public static ArrayList<FavoriteItem> mapCursorToArrayList(Cursor favoriteCursor) {
        ArrayList<FavoriteItem> notesList = new ArrayList<>();
        while (favoriteCursor.moveToNext()) {
            FavoriteItem favoriteItem = new FavoriteItem();
            favoriteItem.setName(favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(COLUMN_NAME)));
        }
        return notesList;
    }
}
