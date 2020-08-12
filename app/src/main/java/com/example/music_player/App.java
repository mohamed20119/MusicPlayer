package com.example.music_player;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.SeekBar;

import com.example.music_player.PlaySong.SongPlayerService;
import com.example.music_player.PlaySong.UI.EnternalSong;

public class App extends Application {

    public SongPlayerService.Bind ref ;
    Intent intentSongPlayerService;
    Intent intentSongData;
    private  SeekBar seekBar ;
    private EnternalSong enternalSong;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void makeservice (Intent intentSongData )
    {
        this.intentSongData = intentSongData ;
       if(ref != null)
       {
           ref.getservice().onDestroy();
           SongPlayerservice();
       }else
       {
           SongPlayerservice();
       }
    }

    public void SongPlayerservice()
    {
        this.intentSongPlayerService = new Intent(this,SongPlayerService.class);
        bindService(intentSongPlayerService, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                ref = (SongPlayerService.Bind) iBinder;
                ref.getMedia(MediaPlayer.create(getApplicationContext(),Uri.parse(intentSongData.getStringExtra("data"))));
                ref.getservice().play();
                ref.getservice().showEnternalSongSeek(seekBar,enternalSong);

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                ref = null;
            }
        },BIND_AUTO_CREATE);
    }


    public void showEnternalSongSeek(EnternalSong enternalSong, SeekBar seekBar) {
        this.seekBar = seekBar;
        this.enternalSong = enternalSong;
    }
}
