package com.example.music_player.PlaySong.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.music_player.App;
import com.example.music_player.MainActivity;
import com.example.music_player.PlaySong.Presenter.PlaySongPresenter;
import com.example.music_player.PlaySong.di.DaggerPlaySongPresenterComponent;
import com.example.music_player.R;
import com.example.music_player.songs.Model.LoadLocalSong;
import com.example.music_player.songs.Model.Song;
//import com.example.music_player.songs.di.DaggerSongPresenterComponent;

import java.util.ArrayList;

public class EnternalSong extends AppCompatActivity {
    Intent intentSongData;
    App app ;
    LoadLocalSong loadLocalSong ;
    ArrayList<Song> list = new ArrayList<>();
    PlaySongPresenter playSongPresenter ;
    TextView songname ;
    SeekBar seekBar ;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enternal_song);
        this.seekBar = (SeekBar) findViewById(R.id.seekBar);
        this.app = (App) getApplication();
        this.songname = (TextView)findViewById(R.id.songname);
        this.loadLocalSong = new LoadLocalSong(this,list);
        this.intentSongData = getIntent();
        this.playSongPresenter = DaggerPlaySongPresenterComponent.builder().getLoadLocalSong(this.loadLocalSong).getAPP(this.app).getArray(this.list).getContext(this).getIntent(this.intentSongData).getTextView(this.songname).finish().getPlaySongPresenter();
        playSongPresenter.play();
       this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


           @Override
           public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               int finish = app.ref.getservice().mediaPlayer.getDuration();
               seekBar.setMax(finish);
               app.ref.getservice().mediaPlayer.seekTo(i);
               Toast.makeText(EnternalSong.this,"now : "+String.valueOf(app.ref.getservice().mediaPlayer.getCurrentPosition()),Toast.LENGTH_LONG).show();
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {
           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });
        this.button = (Button)findViewById(R.id.toggleButton);
       button.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
           @Override
           public void onClick(View view) {
               if(app.ref.getservice().mediaPlayer.isPlaying())
               {
                   playSongPresenter.pause();
                   button.setBackgroundResource(R.drawable.ic_pause_black_24dp);

               }else
               {
                   playSongPresenter.resume();

                   button.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
               }
           }
       });

        playSongPresenter.showEnternalSongSeek(this,seekBar);

    }


    public void nextsong(View view) {
       this.playSongPresenter.nextsong();
        this.button.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
    }

    public void prvsong(View view) {
        this.playSongPresenter.prevsong();
        this.button.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);

    }


}
