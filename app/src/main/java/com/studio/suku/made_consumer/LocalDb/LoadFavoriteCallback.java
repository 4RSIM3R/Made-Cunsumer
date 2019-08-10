package com.studio.suku.made_consumer.LocalDb;

import android.database.Cursor;

public interface LoadFavoriteCallback {
    void postExecute(Cursor favorites);
}
