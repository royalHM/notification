package com.example.hamed.notification_developers.Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.hamed.notification_developers.R;

public class MainActivity extends AppCompatActivity {

    LinearLayout btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10;

    private static final int NOTIFICATION_ID_1 = 1;
    private static final int NOTIFICATION_ID_2 = 2;
    private static final int NOTIFICATION_ID_3 = 3;
    private static final int NOTIFICATION_ID_4 = 4;
    private static final int NOTIFICATION_ID_5 = 5;
    private static final int NOTIFICATION_ID_6 = 6;
    private static final int NOTIFICATION_ID_7 = 7;
    private static final int NOTIFICATION_ID_8 = 8;
    private static final int NOTIFICATION_ID_9 = 9;
    private static final int NOTIFICATION_ID_10 = 10;

    private static final int THREAD_SLEEP = 1000;
    private static final int MAX_PROGRESS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    void findViews() {
        btn1 = (LinearLayout) findViewById(R.id.btn1);
        btn2 = (LinearLayout) findViewById(R.id.btn2);
        btn3 = (LinearLayout) findViewById(R.id.btn3);
        btn4 = (LinearLayout) findViewById(R.id.btn4);
        btn5 = (LinearLayout) findViewById(R.id.btn5);
        btn6 = (LinearLayout) findViewById(R.id.btn6);
        btn7 = (LinearLayout) findViewById(R.id.btn7);
        btn8 = (LinearLayout) findViewById(R.id.btn8);
        btn9 = (LinearLayout) findViewById(R.id.btn9);
        btn10 = (LinearLayout) findViewById(R.id.btn10);
    }

    public void Notify(View v) {
        int id = v.getId();

        if (id == R.id.btn1)
            simpleNoti();
        else if (id == R.id.btn2)
            simpleNoti_2();
        else if (id == R.id.btn3)
            twoOptionNoti();
        else if (id == R.id.btn4)
            bigTextNoti();
        else if (id == R.id.btn5)
            bigPictureNoti();
        else if (id == R.id.btn6)
            inboxStyleNoti();
        else if (id == R.id.btn7)
            bigPicture_twoOption_Noti();
        else if (id == R.id.btn8)
            largeIconNoti();
        else if (id == R.id.btn9)
            progressNoti();
        else if (id == R.id.btn10)
            customNoti();
    }

    private void simpleNoti() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "simpleNoti");

        builder.setContentTitle("Simple Title");
        builder.setContentText("Simple Content");
        builder.setSmallIcon(R.drawable.notification);
        builder.setTicker("Simple Ticker");
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        assert manager != null;
        manager.notify(NOTIFICATION_ID_1, notification);
    }

    private void simpleNoti_2() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "simpleNoti_2");

        builder.setContentTitle("Simple Title 2");
        builder.setContentText("Simple Content 2");
        builder.setSmallIcon(R.drawable.notification);
        builder.setTicker("Simple Ticker 2");
        builder.setAutoCancel(true);//اگر فالس باشد موقعی که روی نوتیفیکیشن کلیک شد نوتیفیکین پاک نمیشود
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setSubText("This is a SubText");
        builder.setColor(784651);
        builder.setOngoing(true);
        builder.setVisibility(Notification.VISIBILITY_PRIVATE);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000, 1000});

        Intent i = new Intent(MainActivity.this, SampleActivity.class);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, i, 0);
        builder.setContentIntent(pi);

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;//نوتیفیکیشن با کلیک بر رویش پاک نشود
        NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        assert manager != null;
        manager.notify(NOTIFICATION_ID_2, notification);

        //manager.cancel(NOTIFICATION_ID_2);
    }

    private void twoOptionNoti() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "twoOptionNoti");

        builder.setContentTitle("Two Option Title");
        builder.setContentText("Two Option Content");
        builder.setSmallIcon(R.drawable.notification);
        builder.setTicker("Two Option Ticker");
        builder.setAutoCancel(true);
        builder.setOngoing(false);
        builder.setSubText("This is Two Option Subtext");
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setVibrate(new long[0]);


        Intent i = new Intent(MainActivity.this, SampleActivity.class);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, i, 0);

        Intent j = new Intent(MainActivity.this, SettingActivity.class);
        PendingIntent pi_settings = PendingIntent.getActivity(MainActivity.this, 0, j, 0);

        Intent k = new Intent(MainActivity.this, HelpActivity.class);
        PendingIntent pi_help = PendingIntent.getActivity(MainActivity.this, 0, k, 0);

        builder.setContentIntent(pi);
        builder.addAction(android.R.drawable.ic_delete, "Delete", pi_settings);
        builder.addAction(android.R.drawable.ic_dialog_email, "Email", pi_help);

        Notification notification = builder.build();
        //notification.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        assert manager != null;
        manager.notify(NOTIFICATION_ID_3, notification);
    }

    private void bigTextNoti() {
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.setBigContentTitle("Big Content Title");
        style.bigText("shoud be @drawable/image where image could" +
                " have any extension like: image.png, image.xml, image.gif. " +
                "Android will automatically create a reference in R class with its name," +
                " so you cannot have in any drawable folder image.png and image.gif.");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "bigTextNoti");
        builder.setContentTitle("Content Title");
        builder.setContentText("Big Text Content");
        builder.setSmallIcon(R.drawable.notification);
        builder.setTicker("Big Text Ticker");
        builder.setAutoCancel(true);
        builder.setStyle(style);
        builder.setPriority(Notification.PRIORITY_MAX);

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        assert manager != null;
        manager.notify(NOTIFICATION_ID_4, notification);
    }

    private void bigPictureNoti() {
        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.big);
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("Big Picture Title");
        style.bigPicture(bmp);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "bigPictureNoti");
        builder.setContentTitle("Big Picture Title");
        builder.setContentText("Big Picture Content");
        builder.setSmallIcon(R.drawable.notification);
        builder.setTicker("Big Picture Ticker");
        builder.setAutoCancel(true);
        builder.setStyle(style);
        builder.setPriority(Notification.PRIORITY_MAX);

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        assert manager != null;
        manager.notify(NOTIFICATION_ID_5, notification);
    }

    private void inboxStyleNoti() {
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        style.setBigContentTitle("Inbox Title");
        style.addLine("Inbox Line 1");
        style.addLine("Inbox Line 2");
        style.addLine("Inbox Line 3");
        style.addLine("Inbox Line 4");
        style.addLine("Inbox Line 5");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "inboxStyleNoti");
        builder.setContentTitle("Inbox Title");
        builder.setContentText("Inbox Content");
        builder.setSmallIcon(R.drawable.notification);
        builder.setTicker("Inbox Ticker");
        builder.setAutoCancel(true);
        builder.setStyle(style);
        builder.setPriority(Notification.PRIORITY_MAX);

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        assert manager != null;
        manager.notify(NOTIFICATION_ID_6, notification);
    }

    private void bigPicture_twoOption_Noti() {
        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.big);
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("Big Pic 2 Opt Title");
        style.bigPicture(bmp);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "bigPicture_twoOption_Noti");
        builder.setContentTitle("Big Pic 2 Opt Title");
        builder.setContentText("Big Pic 2 Opt Content");
        builder.setSmallIcon(R.drawable.notification);
        builder.setTicker("Big Pic 2 Opt Ticker");
        builder.setAutoCancel(true);
        builder.setStyle(style);
        builder.setPriority(Notification.PRIORITY_MAX);

        Intent i = new Intent(MainActivity.this, SampleActivity.class);
        PendingIntent pi_main = PendingIntent.getActivity(MainActivity.this, 0, i, 0);

        Intent j = new Intent(MainActivity.this, SettingActivity.class);
        PendingIntent pi_settings = PendingIntent.getActivity(MainActivity.this, 0, j, 0);

        Intent k = new Intent(MainActivity.this, HelpActivity.class);
        PendingIntent pi_help = PendingIntent.getActivity(MainActivity.this, 0, k, 0);

        builder.setContentIntent(pi_main);
        builder.addAction(android.R.drawable.ic_delete, "Settings", pi_settings);
        builder.addAction(android.R.drawable.ic_menu_help, "Help", pi_help);

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        assert manager != null;
        manager.notify(NOTIFICATION_ID_7, notification);
    }

    private void largeIconNoti() {
        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.gmail_48);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "largeIconNoti");
        builder.setContentTitle("Mail Received");
        builder.setContentText("First Mail");
        builder.setSmallIcon(R.drawable.notification);
        builder.setTicker("New Mail... :)");
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setVibrate(new long[]{100, 100, 100, 100, 100, 100});

        builder.setNumber(65);
        builder.setLargeIcon(bmp);

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        assert manager != null;
        manager.notify(NOTIFICATION_ID_8, notification);
    }

    private void progressNoti() {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "progressNoti");

        builder.setContentTitle("Picture Download");
        builder.setContentText("Download in progress");
        builder.setSmallIcon(R.drawable.notification);
        builder.setTicker("Download Started");
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);

        final NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i <= MAX_PROGRESS; i += 10) {
                    builder.setProgress(MAX_PROGRESS, i, false);
                    builder.setNumber(i);
                    Notification notification = builder.build();

                    assert manager != null;
                    manager.notify(NOTIFICATION_ID_9, notification);

                    try {
                        Thread.sleep(THREAD_SLEEP);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                builder.setContentText("Download Finish");
                builder.setProgress(0, 0, false);
                Notification notification = builder.build();
                manager.notify(NOTIFICATION_ID_9, notification);
            }
        }).start();

    }

    void customNoti() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "customNoti");

        builder.setContentTitle("Custom Title");
        builder.setContentText("Custom Content");
        builder.setSmallIcon(R.drawable.notification);
        builder.setTicker("Custom Ticker");
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);

        RemoteViews notificationView = new RemoteViews(getPackageName(), R.layout.notif);
        builder.setContent(notificationView);

        Intent notificationIntent = new Intent(MainActivity.this, SampleActivity.class);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder.setContentIntent(pendingNotificationIntent);

        Intent playIntent = new Intent(this, PlayButtonListener.class);
        PendingIntent pendingPlayIntent = PendingIntent.getBroadcast(this, 0, playIntent, 0);
        notificationView.setOnClickPendingIntent(R.id.imgPlay, pendingPlayIntent);

        Intent pauseIntent = new Intent(this, PauseButtonListener.class);
        PendingIntent pendingPauseIntent = PendingIntent.getBroadcast(this, 0, pauseIntent, 0);
        notificationView.setOnClickPendingIntent(R.id.imgPause, pendingPauseIntent);

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(NOTIFICATION_ID_10, notification);
    }

    public static class PlayButtonListener extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "play music", Toast.LENGTH_LONG).show();
        }
    }

    public static class PauseButtonListener extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "pause music", Toast.LENGTH_LONG).show();
        }
    }


}