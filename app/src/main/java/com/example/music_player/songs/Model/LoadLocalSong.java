package com.example.music_player.songs.Model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

public class LoadLocalSong {
    private Context context;
    private ArrayList<Song> songlist;

    @Inject
    public LoadLocalSong(Context context, ArrayList<Song> songlist) {
        this.context = context;
        this.songlist = songlist;
    }

    public ArrayList<Song> loadsong() {
       Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String data = MediaStore.Audio.Media.DATA;
        String duration = MediaStore.Audio.Media.DURATION;
        String songname = MediaStore.Audio.Media.TITLE;
        String singer = MediaStore.Audio.Media.ARTIST;
        String cover = MediaStore.Audio.Media.ALBUM;
        String datas[] = new String[]{data, duration, songname, singer,cover};
        Cursor cursor = this.context.getContentResolver().query(uri, datas, data + " like ?", new String[]{"%.mp3%"}, null);
        while (cursor.moveToNext()) {
            this.songlist.add(new Song(cursor.getString(4),cursor.getString(0),cursor.getString(2),cursor.getString(1),cursor.getString(3)));
        }
        return this.songlist;
    }
}
