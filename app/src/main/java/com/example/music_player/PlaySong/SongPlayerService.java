package com.example.music_player.PlaySong;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.SeekBar;

import com.example.music_player.PlaySong.UI.EnternalSong;

public class SongPlayerService extends Service {
    public MediaPlayer mediaPlayer;
    public SongPlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Bind() ;
    }

    public void play()
    {
        mediaPlayer.start();
        Log.e("data",String.valueOf(mediaPlayer.toString()));

    }

    public void stop()
    {
        mediaPlayer.stop();
    }

    public void pause()
    {
        mediaPlayer.pause();
        Log.e("data",String.valueOf(mediaPlayer.toString()));
    }


    public void showEnternalSongSeek(final SeekBar seekBar , EnternalSong enternalSong)
    {
        final int finish = mediaPlayer.getDuration();
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        seekBar.setMax(finish);
     /*    final int current = mediaPlayer.getCurrentPosition();
        enternalSong.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                while( mediaPlayer.getDuration()>mediaPlayer.getCurrentPosition())
                {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            }
        });*/
    }
    @Override
    public void onDestroy() {
        stop();
        super.onDestroy();
    }

    public class Bind extends Binder
    {

        public SongPlayerService getservice()
        {
            return SongPlayerService.this;
        }
        public void getMedia(MediaPlayer mediaPlayer)
        {
            getservice().mediaPlayer = mediaPlayer;
        }
    }
}
