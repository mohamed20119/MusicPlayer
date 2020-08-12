package com.example.music_player.songs.UI;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music_player.PlaySong.UI.EnternalSong;
import com.example.music_player.R;
import com.example.music_player.songs.Model.Song;
import java.util.ArrayList;

import javax.inject.Inject;


public class SongListRecycleViewAdaptor extends RecyclerView.Adapter<SongListRecycleViewAdaptor.songholder> {
    private  ArrayList<Song> songList = new ArrayList<>();
    private  ArrayList<Song> original = new ArrayList<>();
    private  ArrayList<Song> result = new ArrayList<>();
    private  Context context;
    private  EnternalsongCallBack enternalsongCallBack = null ;
    @Inject
    public SongListRecycleViewAdaptor(ArrayList<Song> songList, Context context,EnternalsongCallBack enternalsongCallBack) {
        this.songList = songList;
        this.context = context;
        this.enternalsongCallBack=enternalsongCallBack;
        this.original.addAll(this.songList);

    }

    @NonNull
    @Override
    public songholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(this.context,R.layout.songitem,null);
        songholder songholder = new songholder(v);
        return songholder;
    }

    @Override
    public void onBindViewHolder(@NonNull songholder holder, final int position) {
        holder.songname.setText(this.songList.get(position).getSongName());
        holder.songname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        String data = original.get(position).getData();
                        Intent i = new Intent(context,EnternalSong.class);
                        i.putExtra("data",data);
                        i.putExtra("currentsong",position);
                     //   i.putExtra("list",original);
                        enternalsongCallBack.go(i);
            }
        });
        holder.singer.setText(this.songList.get(position).getSingerName());
        holder.singer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = original.get(position).getData();
                Intent i = new Intent(context,EnternalSong.class);
                i.putExtra("data",data);
                i.putExtra("currentsong",position);
                //i.putExtra("list",original);
                enternalsongCallBack.go(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.songList.size();
    }

    public void Filter (String name)
    {
        if(name ==null||name.equals("")||name.isEmpty()==true)
        {
            this.songList.clear();
            this.songList.addAll(this.original);
            notifyDataSetChanged();
        }else
        {
            this.result.clear();
            for(int x=0;x<this.original.size();x++)
            {
                if(this.original.get(x).getSongName().toLowerCase().contains(name))
                {
                    this.result.add(this.original.get(x));
                }
            }
            this.songList.clear();
            this.songList.addAll(this.result);
            notifyDataSetChanged();

        }
    }


    public class songholder extends RecyclerView.ViewHolder
    {
        TextView songname,singer;
        ImageView songpic;
        public songholder(@NonNull View itemView) {
            super(itemView);
            this.songname = (TextView) itemView.findViewById(R.id.songname);
            this.singer = (TextView) itemView.findViewById(R.id.singer);
            this.songpic = (ImageView) itemView.findViewById(R.id.songpic);
        }
    }

}
