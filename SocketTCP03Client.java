package com.hspedu.socket;
/*
1、サーバーに接続します、IPとポートが必要になります。
2.接続したら、Socketを生成します、
3.socket.getOutputStream（）出力ストリームでデータを通信チャンネルに書込みます。

文字ストリームを使用します


 */

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketTCP03Client {
    public static void main(String[] args) throws IOException {

//        1、サーバーに接続します、IPとポートが必要になります。
        //アドレスに該当するPCに接続します、ポートは9999
        //接続が成功したら、Socketオブジェクトに戻ります。

        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        System.out.println("クライアント　socketに戻り"+socket.getClass());

//        2.接続したら、Socketを生成します、
        //該当socketオブジェクトに関連する出力ストリームを取得します。
        OutputStream outputStream = socket.getOutputStream();
//        3.socket.getOutputStream（）outputストリームでデータを通信チャンネルに書込みます。
        //outputStream.write("hello,sever".getBytes());

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        //IOストリームのメソッド（BufferedWriter）でバイトストリームを文字ストリームに変換します
        bufferedWriter.write("Hello,Sever 文字ストリーム");
        bufferedWriter.newLine();//行を変えます、書き込みが終了した事を意味します
        //同時に相手はreadline()で読み込む事が必要になります！！！！
        bufferedWriter.flush();//文字ストリームを使用する場合は手動で更新する必要が有ります。
        //更新しなければデーターチャンネルに書き込むことができません。　


        //4.socketに関連するInputストリームを取得します、バイトを読み取り、モニターに表示ます。
        InputStream inputStream = socket.getInputStream();
//        byte[] buf = new byte[1024];
//        int readLen = 0;
//        while ((readLen=inputStream.read(buf))!=-1){
//            System.out.println(new String(buf,0,readLen));
//        }
//        socket.shutdownOutput();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);//出力


        //5.オブジェクトとストリームを終了します。
        bufferedReader.close();
        bufferedWriter.close();
//        inputStream.close();
//        outputStream.close();
        socket.close();
        System.out.println("クライアントが退出しました");

    }
}
