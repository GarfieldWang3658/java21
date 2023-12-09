package com.hspedu.upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPFileUploadServer {
    public static void main(String[] args) throws IOException {
        //1.サーバーのポート8888を新規します。
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("サーバーのポートは8888になります");
        //2.接続を待機します。
        Socket socket = serverSocket.accept();

        //3.クライアント側から送られてきたデーターを読み取ります。
        //socketでinputストリームを取得します

        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());

        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //取得したバイト配列を指定アドレスに書込み、ファイルを生成します。
        String destFilePath = "/Users/garfield/Desktop/BIGDUO2.jpg";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
        bos.write(bytes);

        //クライアント側にメッセジーを送信します
        //
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("画像を受け取れました");
        bw.flush();//データーをチャンネルに更新します
        socket.shutdownOutput();



        //関連ソースを終了させます
        bos.close();
        bis.close();
        bw.close();
        socket.close();
        serverSocket.close();


    }

}
