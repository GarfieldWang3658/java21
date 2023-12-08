package com.hspedu.socket;
/*
1、サーバーに接続します、IPとポートが必要になります。
2.接続したら、Socketを生成します、
3.socket.getOutputStream（）出力ストリームでデータを通信チャンネルに書込みます。


 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class   SocketTCP02Client {
    public static void main(String[] args) throws IOException {

//        1、サーバーに接続します、IPとポートが必要になります。
        //アドレスに該当するPCに接続します、ポートは9999
        //接続が成功したら、Socketオブジェクトに戻ります。

        Socket socket = new Socket(InetAddress.getLocalHost(),9983);
        System.out.println("クライアント　socketに戻り"+socket.getClass());

//        2.接続したら、Socketを生成します、
        //該当socketオブジェクトに関連する出力ストリームを取得します。
        OutputStream outputStream = socket.getOutputStream();
//        3.socket.getOutputStream（）出力ストリームでデータを通信チャンネルに書込みます。
        outputStream.write("hello,sever".getBytes());

        //4.socketに関連するInputストリームを取得します、バイトを読み取り、モニターに表示ます。
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen=inputStream.read(buf))!=-1){
            System.out.println(new String(buf,0,readLen));
        }
        socket.shutdownOutput();


        //5.オブジェクトとストリームを終了します。
        inputStream.close();
        outputStream.close();
        socket.close();
        System.out.println("クライアントが退出しました");

    }
}
