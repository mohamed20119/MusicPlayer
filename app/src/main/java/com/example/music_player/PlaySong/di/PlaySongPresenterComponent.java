package com.example.music_player.PlaySong.di;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.example.music_player.App;
import com.example.music_player.PlaySong.Presenter.PlaySongPresenter;
import com.example.music_player.songs.Model.LoadLocalSong;
import com.example.music_player.songs.Model.Song;

import java.util.ArrayList;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Subcomponent;

@Component
public interface PlaySongPresenterComponent {
    PlaySongPresenter getPlaySongPresenter();
    @Component.Builder
    interface Builder{
        @BindsInstance PlaySongPresenterComponent.Builder getContext(Context context);
        @BindsInstance PlaySongPresenterComponent.Builder getAPP(App app);
        @BindsInstance PlaySongPresenterComponent.Builder getIntent(Intent intent);
        @BindsInstance PlaySongPresenterComponent.Builder getTextView(TextView textView);
        @BindsInstance PlaySongPresenterComponent.Builder getArray( ArrayList <Song> list);
        @BindsInstance PlaySongPresenterComponent.Builder getLoadLocalSong(LoadLocalSong loadLocalSong);
        PlaySongPresenterComponent finish();
    }
}
