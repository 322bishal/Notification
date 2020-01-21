package com.nayaapp.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nayaapp.notification.broadcast.BroadcastRecieverExample;
import com.nayaapp.notification.createchannel.CreateChannel;

public class MainActivity extends AppCompatActivity {
    BroadcastRecieverExample broadcastRecieverExample=new BroadcastRecieverExample();
    private NotificationManagerCompat notificationManagerCompat;
    private Button btnDisplayNotification;
    private Button btnDisplayNotification2;
    int count1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        btnDisplayNotification = findViewById(R.id.notification1);
        btnDisplayNotification2 = findViewById(R.id.notification2);

        btnDisplayNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayNotification();
            }

            private void DisplayNotification() {
                count1++;
                Notification notification = new NotificationCompat.Builder(MainActivity.this, CreateChannel.CHANNEL_1)
                        .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                        .setContentTitle("First Message")
                        .setContentText("First message body")
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                notificationManagerCompat.notify(count1, notification);
            }
        });
        btnDisplayNotification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayNotification2();
            }

            private void DisplayNotification2() {
                count1++;
                Notification notification = new NotificationCompat.Builder(MainActivity.this, CreateChannel.CHANNEL_2)
                        .setSmallIcon(R.drawable.ic_warning_black_24dp)
                        .setContentTitle("Second Message")
                        .setContentText("Second message body")
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                notificationManagerCompat.notify(count1, notification);
            }
        });

    }
    protected void onStart(){
        super.onStart();
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastRecieverExample,intentFilter);
    }

    protected  void onStop(){
        super.onStop();
        unregisterReceiver(broadcastRecieverExample);
    }
}
