package com.shunan.baseproject.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class MediaButtonReceiver extends BroadcastReceiver {
    private static String TAG = "MediaButtonReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // 获得KeyEvent对象
        KeyEvent event = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);

        if (Intent.ACTION_MEDIA_BUTTON.equals(action)) {
            // 获得按键码
            int keycode = event.getKeyCode();

            if (event == null || event.getAction() != KeyEvent.ACTION_UP) {
                return;
            }

            switch (keycode) {
                case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                    Logger.d("上键：ToLeft");
                    sendMsg("ToLeft");
                    break;
                case KeyEvent.KEYCODE_MEDIA_NEXT:
                    Logger.d("下键：ToRight");
                    sendMsg("ToRight");
                    break;
                case KeyEvent.KEYCODE_MEDIA_PLAY:
                case KeyEvent.KEYCODE_MEDIA_PAUSE:
                case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
                case KeyEvent.KEYCODE_HEADSETHOOK:
                    Logger.d("中键：ToRight");
                    sendMsg("ToRight");
                    break;
                default:
                    break;
            }
        }
    }

    public static void sendMsg(final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //创建客户端Socket，指定服务器的IP地址和端口
//                    socket = new Socket("172.18.2.250", 8888);
                    //获取输出流，向服务器发送数据
                    if (isServerClose(SingASongService.socket)) {
                        SingASongService.socket = new Socket("172.18.2.250", 8888);
                    }
                    OutputStream os = SingASongService.socket.getOutputStream();
//                                    PrintWriter pw = new PrintWriter(os);
                    os.write(msg.getBytes());
                    os.flush();
                    //关闭输出流
//                    SingASongService.socket.shutdownOutput();
//                                    pw.close();
//                    os.close();
//                    SingASongService.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 判断是否断开连接，断开返回true,没有返回false
     *
     * @param socket
     * @return
     */
    public static Boolean isServerClose(Socket socket) {
        try {
            socket.sendUrgentData(0);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            return false;
        } catch (Exception se) {
            return true;
        }
    }
}
