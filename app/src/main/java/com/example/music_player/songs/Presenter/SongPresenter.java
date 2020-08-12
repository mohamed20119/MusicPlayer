package com.example.music_player.songs.Presenter;

import android.content.Context;

import com.example.music_player.songs.Model.Song;
import com.example.music_player.songs.Model.SongModel;

import java.util.ArrayList;

import javax.inject.Inject;

public class SongPresenter {
    private Context context;
    private ArrayList<Song> songlist ;
    @Inject
     SongModel songModel ;

    @Inject
    public SongPresenter(Context context, ArrayList<Song> songlist) {
        this.context = context;
        this.songlist = songlist;
    }

    public ArrayList<Song> getSongs()
    {
        return this.songModel.getsongs();
    }
}
