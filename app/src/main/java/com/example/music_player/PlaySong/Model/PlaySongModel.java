package com.example.music_player.PlaySong.Model;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.music_player.App;
import com.example.music_player.PlaySong.UI.EnternalSong;
import com.example.music_player.songs.Model.LoadLocalSong;
import com.example.music_player.songs.Model.Song;

import java.util.ArrayList;

import javax.inject.Inject;

public class PlaySongModel {
    LoadLocalSong loadLocalSong ;
    ArrayList<Song> list = new ArrayList<>();
    App app;
    Intent intentSongData;
    int current;
    Context context ;
    TextView songname ;

    int now;
    public PlaySongModel(LoadLocalSong loadLocalSong, ArrayList<Song> list, App app, Intent intentSongData, Context context, TextView songname) {
        this.loadLocalSong = loadLocalSong;
        this.list = list;
        this.app = app;
        this.intentSongData = intentSongData;
        this.context = context;
        this.songname = songname;
    }

    public void getSong()
    {
        this.list = this.loadLocalSong.loadsong();
    }

    public void play ()
    {
        this.app.makeservice(intentSongData);
        this.current = this.intentSongData.getIntExtra("currentsong",0);
        this.songname.setText(this.list.get(this.current).getSongName());

    }

    public void resume()
    {
        this.app.ref.getservice().mediaPlayer.seekTo(this.now);
        this.app.ref.getservice().mediaPlayer.start();
    }
    public void nextsong()
    {

        if(this.current==this.list.size()-1||this.current>this.list.size())
        {
            Toast.makeText(context,"This Last Song",Toast.LENGTH_LONG).show();

        }else
        {
            this.current +=1;
            String data = this.list.get(current).getData();
            MediaPlayer x = MediaPlayer.create(context, Uri.parse(data));
            app.ref.getservice().stop();
            app.ref.getservice().mediaPlayer = x;
            app.ref.getservice().play();
            this.songname.setText(this.list.get(this.current).getSongName().toString());
        }

    }

    public void prevsong()
    {
        if(this.current==0)
        {
            Toast.makeText(context,"This First Song",Toast.LENGTH_LONG).show();
        }else
        {
            this.current -=1;
            String data = this.list.get(current).getData();
            MediaPlayer x = MediaPlayer.create(context,Uri.parse(data));
            app.ref.getservice().stop();
            app.ref.getservice().mediaPlayer = x;
            app.ref.getservice().play();
            this.songname.setText(this.list.get(this.current).getSongName().toString());
        }

    }

    public void pause ()
    {
        app.ref.getservice().pause();
       this.now= app.ref.getservice().mediaPlayer.getCurrentPosition();
    }




    public void showEnternalSongSeek(EnternalSong enternalSong, SeekBar seekBar) {
        app.showEnternalSongSeek(enternalSong,seekBar);
    }

}
