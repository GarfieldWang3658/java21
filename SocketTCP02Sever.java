package com.hspedu.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
1.9999のポートを監視し、接続をするまで待機します
２.クライアントがない場合はプログラムが渋滞になります、接続を待ちます
3.socket.getInputStream()でクライアントに書き込まれたデーターを読み取ります。
 */
public class SocketTCP02Sever {
    public static void main(String[] args) throws IOException {
        //1.9999のポートを監視し、接続をするまで待機します
        ServerSocket serverSocket = new ServerSocket(9983);
        //ポートが使用されていないのは前提条件です。
        //ServerSocketはメソッドaccept()で多数のsocketを戻ることができます。
        //複数のクライアントとサーバーに接続することができます。
        //多重同時性を実現することができます。
        Socket socket = serverSocket.accept();
        System.out.println("サーバーはポート:9999にて待機中...");
        // Socket socket = serverSocket.accept();

        //２.クライアントがない場合はプログラムが渋滞になります、接続を待ちます
        //クライアントに接続したら、Socketオブジェクトに戻ります。プログラムは続行します

        System.out.println("サーバー socketに戻り "+socket.getClass());

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,client".getBytes());
        //3.socket.getInputStream()でクライアントに書き込まれたデーターを読み取ります。
        InputStream inputStream = socket.getInputStream();

        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf))!=-1){
            System.out.println(new String(buf,0,readLen));
            //実際読み取った内容で、文字列を表示します
        }



        //5.socket関連するoutputStream;
//        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write("hello,client".getBytes());


        //6.ストリームとsocketを終了します。
        socket.shutdownOutput();
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
        System.out.println("サーバーが退出しました");

    }
}
