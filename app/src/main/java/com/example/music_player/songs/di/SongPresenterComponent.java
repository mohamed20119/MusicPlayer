package com.example.music_player.songs.di;

import android.content.Context;

import com.example.music_player.PlaySong.di.PlaySongPresenterComponent;
import com.example.music_player.songs.Model.Song;
import com.example.music_player.songs.Presenter.SongPresenter;

import java.util.ArrayList;

import dagger.BindsInstance;
import dagger.Component;

@Component
public interface SongPresenterComponent {
    SongPresenter getSongPresenter();
    @Component.Builder
    interface Builder
    {
        @BindsInstance SongPresenterComponent.Builder getContext(Context context);
        @BindsInstance SongPresenterComponent.Builder getArrayList(ArrayList<Song> songlist);
        SongPresenterComponent finish();
    }
}
