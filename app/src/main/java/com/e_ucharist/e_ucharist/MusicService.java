package com.e_ucharist.e_ucharist;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class MusicService extends Service{

    private  MediaPlayer player;

    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Creates a global player MediaPlayer object to play sound
        player = MediaPlayer.create(this, R.raw.singing_bowl);
        player.setLooping(true);
        player.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //handle volume fade here
        player.stop();
    }
}
