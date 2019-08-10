package com.studio.suku.made_consumer;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.studio.suku.made_consumer.Adapter.ConsumerAdapter;
import com.studio.suku.made_consumer.LocalDb.DatabaseContract;
import com.studio.suku.made_consumer.LocalDb.Favorite;
import com.studio.suku.made_consumer.LocalDb.FavoriteItem;
import com.studio.suku.made_consumer.LocalDb.LoadFavoriteCallback;
import com.studio.suku.made_consumer.LocalDb.MapHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.studio.suku.made_consumer.LocalDb.DatabaseContract.Entry.*;

public class MainActivity extends AppCompatActivity implements LoadFavoriteCallback {

    private ConsumerAdapter consumerAdapter;
    private MainActivity.DataObserver myObserver;
    RecyclerView list;
    ArrayList<FavoriteItem> favoriteArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Bismillah");
        list = findViewById(R.id.list_fav);
        consumerAdapter = new ConsumerAdapter(this);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setHasFixedSize(true);
        //list.setAdapter(consumerAdapter);
        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        myObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);
        new getData(this, this).execute();




    }

    @Override
    public void postExecute(Cursor favorites) {
        //ArrayList<FavoriteItem> list = MapHelper.mapCursorToArrayList(favorites);
        if (favorites != null){
            Log.d("Berhasil", "Berhasil");
        }
        else {
            Log.d("Berhasil", "Gagal");
        }
    }

    private static class getData extends AsyncTask<Void, Void, Cursor> {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadFavoriteCallback> weakCallback;

        private getData(Context context, LoadFavoriteCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return weakContext.get().getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor data) {
            super.onPostExecute(data);
            weakCallback.get().postExecute(data);
        }
    }

    static class DataObserver extends ContentObserver {
        final Context context;
        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new getData(context, (MainActivity) context).execute();
        }
    }
}
