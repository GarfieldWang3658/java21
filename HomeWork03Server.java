package com.hspedu.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HomeWork03Server {
    public static void main(String[] args) throws IOException {
        //1.サーバーのポート8888を新規します。
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("サーバーのポートは8888になります");
        //2.接続を待機します。
        Socket socket = serverSocket.accept();

        //3.クライアント側から送られてきたデーターを読み取ります。

        //socketでinputストリームを取得します

        InputStream inputStream = socket.getInputStream();


        byte[] b = new byte[1024];
        int Len = 0;
        String downloadFileName= " ";
        while ((Len = inputStream.read(b))!=-1){
            downloadFileName+=new String(b,0,Len);

        }
        System.out.println("クライアントはDOWNLOADしたいファイル名は"+downloadFileName);

        String resFileName = "";
        if ("BIGDUO".equals(downloadFileName)){
            resFileName = "/Users/garfield/Desktop/BIGDUO.jpg";
        }else {
            resFileName = "/Users/garfield/Desktop/car.jpg";
        }

        //4.inputストリームを新規し、ファイルを読み取ります。
        BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(resFileName));


        //5.ツールクラスでファイルを読み取り　　
        // 取得したバイト配列を指定アドレスに書込み、ファイルを生成します。
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //6.socketに関連するoutputストリームを取得します。

        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

        //7.データーチャンネルに書込みます、クライアントに戻します。
        //bos.write(StreamUtils.streamToByteArray(bis));
        bos.write(bytes);

        socket.shutdownOutput();//重要！！！


        //関連ソースを終了させます
        bis.close();
        inputStream.close();
        socket.close();
        serverSocket.close();


    }

}
