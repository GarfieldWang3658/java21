package com.hspedu.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
1.9999のポートを監視し、接続をするまで待機します
２.クライアントがない場合はプログラムが渋滞になります、接続を待ちます
3.socket.getInputStream()でクライアントに書き込まれたデーターを読み取ります。

文字ストリームを使用します
 */
public class HomeWork01Sever {
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
//        outputStream.write("hello,client".getBytes());
        //3.socket.getInputStream()でクライアントに書き込まれたデーターを読み取ります。
        InputStream inputStream = socket.getInputStream();
//
//        byte[] buf = new byte[1024];
//        int readLen = 0;
//        while ((readLen = inputStream.read(buf))!=-1){
//            System.out.println(new String(buf,0,readLen));
//            //実際読み取った内容で、文字列を表示します
//        }
        //4.文字ストリームでIOストリームを読み取ります
        //inputストリームを文字ストリームに変換しました。
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
       boolean loop = true;
        while (loop) {
           String s = bufferedReader.readLine();
           System.out.println(s);//出力
           String answer = " ";
           if ("name".equals(s)) {
               answer = "汪洋と申します。";
           } else if ("hobby".equals(s)) {
               answer = "趣味はドライブです";
           } else {
               answer = "何言っているのは分かりません";
           }


           BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
           //IOストリームのメソッド（BufferedWriter）でバイトストリームを文字ストリームに変換します
           bufferedWriter.write(answer);
           bufferedWriter.newLine();//行を変えます、書き込みが終了した事を意味します
           //同時に相手はreadline()で読み込む事が必要になります！！！！
           bufferedWriter.flush();//

            if ("EXIT".equals(s)){
                loop = false;
                socket.shutdownOutput();
        bufferedReader.close();
       bufferedWriter.close();
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
        System.out.println("サーバーが退出しました");
            }

       }



        //5.socket関連するoutputStream;
//        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write("hello,client".getBytes());


        //6.ストリームとsocketを終了します。


    }
}
