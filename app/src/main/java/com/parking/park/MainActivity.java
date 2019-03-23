package com.parking.park;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.parking.park.tcp.EmCommand;
import com.parking.park.tcp.MessageReceiver;
import com.parking.park.tcp.ParkingHelper;
import com.parking.park.tcp.ParkingInfoListener;

import io.netty.channel.ChannelHandlerContext;

public class MainActivity extends AppCompatActivity {

    private View enter;
    private View exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter = findViewById(R.id.tv_enter);
        exit = findViewById(R.id.tv_exit);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EntranceActivity.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExitActivity.class);
                startActivity(intent);
            }
        });
    }

}
