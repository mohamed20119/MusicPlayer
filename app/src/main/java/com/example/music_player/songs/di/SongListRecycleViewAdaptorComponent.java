package com.example.music_player.songs.di;

import android.content.Context;
import com.example.music_player.songs.Model.Song;
import com.example.music_player.songs.UI.EnternalsongCallBack;
import com.example.music_player.songs.UI.SongListRecycleViewAdaptor;
import java.util.ArrayList;
import dagger.BindsInstance;
import dagger.Component;

@Component
public interface SongListRecycleViewAdaptorComponent {
    SongListRecycleViewAdaptor getAdaptor();
    @Component.Builder
    interface Builder
    {
        @BindsInstance
        SongListRecycleViewAdaptorComponent.Builder getContext(Context context);
        @BindsInstance SongListRecycleViewAdaptorComponent.Builder getArrayList(ArrayList<Song> songlist);
        @BindsInstance SongListRecycleViewAdaptorComponent.Builder getEnternalsongCallBack(EnternalsongCallBack enternalsongCallBack);
        SongListRecycleViewAdaptorComponent finish();
    }
}
