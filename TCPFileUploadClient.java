package com.hspedu.upload;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPFileUploadClient {
    public static void main(String[] args) throws Exception {
        //クライアント側はサーバーに接続します、ポートは8888です、socketオブジェクトを取得します。
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        //inputストリームを新規し、デイスクを読み取る
        //
        String filePath = "/Users/garfield/Desktop/BIGDUO.jpg";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
        //bytes は　filepathが対象するバイト配列です。
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //socketで取得したoutputストリームはbytesのデーターをサーバー側に送信します。
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);//ファイルに対応するバイト配列をデーターチャンネルに書込みます。
        bis.close();
        socket.shutdownOutput();//データー書込みを終了します。

        InputStream inputStream = socket.getInputStream();
        String s = StreamUtils.streamToString(inputStream);
        System.out.println(s);


        //ストリームを終了します
        inputStream.close();
        bos.close();
        socket.close();



    }
}
