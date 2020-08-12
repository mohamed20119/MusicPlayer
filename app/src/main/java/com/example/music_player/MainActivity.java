package com.example.music_player;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.example.music_player.songs.Model.Song;
import com.example.music_player.songs.Presenter.SongPresenter;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;

import android.widget.Toast;

import com.example.music_player.songs.UI.EnternalsongCallBack;
import com.example.music_player.songs.UI.SongListRecycleViewAdaptor;
import com.example.music_player.songs.di.DaggerSongListRecycleViewAdaptorComponent;
//import com.example.music_player.songs.di.DaggerSongPresenterComponent;
import com.example.music_player.songs.di.DaggerSongPresenterComponent;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity
        implements  EnternalsongCallBack {

    private ArrayList<Song> list;
    private SongPresenter presenter;
   private RecyclerView songlist ;
   private SongListRecycleViewAdaptor adaptor ;
   private SearchView searchview;
   private LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActivityCompat.requestPermissions(this,new String[]{READ_EXTERNAL_STORAGE},100);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100)
        {
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                show();
            }
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("currentitem",this.linearLayoutManager.onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.linearLayoutManager.onRestoreInstanceState(savedInstanceState.getParcelable("currentitem"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        this.searchview = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.app_bar_search));
        search();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.exit)
        {
            exit();
        }
        return super.onOptionsItemSelected(item);
    }

    public void show()
   {
       this.list= new ArrayList<>();
       this.presenter= DaggerSongPresenterComponent.builder().getContext(this).getArrayList(this.list).finish().getSongPresenter();

       this.list =  this.presenter.getSongs();
       initRecycleView();

   }

    public void initRecycleView ()
    {
        this.songlist = (RecyclerView) findViewById(R.id.songList);
        this.adaptor = DaggerSongListRecycleViewAdaptorComponent.builder().getArrayList(this.list).getContext(this).getEnternalsongCallBack(this).finish().getAdaptor();
        this.songlist.setAdapter(this.adaptor);
        this.songlist.setLayoutManager(this.linearLayoutManager);


    }

    public void search() {
        this.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adaptor.Filter(newText);

                return false;
            }
        });
    }


    public void exit()
    {
        System.exit(0);
        finish();
    }
    @Override
    public void go(Intent inent) {
        startActivity(inent);
    }
}


