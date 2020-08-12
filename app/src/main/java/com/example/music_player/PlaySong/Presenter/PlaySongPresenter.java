package com.example.music_player.PlaySong.Presenter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.music_player.App;
import com.example.music_player.PlaySong.Model.PlaySongModel;
import com.example.music_player.PlaySong.UI.EnternalSong;
import com.example.music_player.songs.Model.LoadLocalSong;
import com.example.music_player.songs.Model.Song;

import java.util.ArrayList;

import javax.inject.Inject;

public class PlaySongPresenter {
    LoadLocalSong loadLocalSong ;
    ArrayList<Song> list = new ArrayList<>();
    App app;
    Intent intentSongData;
    Context context ;
    TextView songname ;
    PlaySongModel playSongModel ;

    @Inject
    public PlaySongPresenter(LoadLocalSong loadLocalSong, ArrayList<Song> list, App app, Intent intentSongData, Context context, TextView songname) {
        this.loadLocalSong = loadLocalSong;
        this.list = list;
        this.app = app;
        this.intentSongData = intentSongData;
        this.context = context;
        this.songname = songname;
        this.playSongModel = new PlaySongModel(this.loadLocalSong,this.list,app,this.intentSongData,context,songname);
        getSong();
    }



    public void getSong()
    {
        this.list = this.loadLocalSong.loadsong();
    }

    public void play ()
    {
        playSongModel.play();


    }
    public void nextsong()
    {

        playSongModel.nextsong();

    }

    public void prevsong()
    {
        playSongModel.prevsong();


    }

    public void pause ()
    {
        playSongModel.pause();
    }

    public void resume()
    {
        playSongModel.resume();

    }

    public void showEnternalSongSeek(EnternalSong enternalSong, SeekBar seekBar) {
        playSongModel.showEnternalSongSeek(enternalSong,seekBar);
    }
}
