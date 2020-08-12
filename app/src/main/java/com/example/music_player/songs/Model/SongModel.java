package com.example.music_player.songs.Model;

import android.content.Context;

import java.util.ArrayList;

import javax.inject.Inject;

public class SongModel {
    private Context context;
    private ArrayList<Song> list;

    @Inject
    public SongModel(Context context, ArrayList<Song> list) {
        this.context = context;
        this.list = list;
    }

    public ArrayList<Song> getsongs ()
    {
        LoadLocalSong loadLocalSong = new LoadLocalSong(this.context,this.list);
        return  loadLocalSong.loadsong();

    }
}
