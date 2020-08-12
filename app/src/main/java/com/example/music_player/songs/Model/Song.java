package com.example.music_player.songs.Model;

import android.net.Uri;

import javax.inject.Inject;

public class Song {
    private String data, songName , songLength ,SingerName ,cover;

    @Inject
    public Song(String cover, String data, String songName, String songLength, String singerName) {
        this.cover = cover;
        this.data = data;
        this.songName = songName;
        this.songLength = songLength;
        SingerName = singerName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongLength() {
        return songLength;
    }

    public void setSongLength(String songLength) {
        this.songLength = songLength;
    }

    public String getSingerName() {
        return SingerName;
    }

    public void setSingerName(String singerName) {
        SingerName = singerName;
    }
}
