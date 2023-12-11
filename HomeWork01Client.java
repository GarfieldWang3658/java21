package com.hspedu.homework;
/*
1、サーバーに接続します、IPとポートが必要になります。
2.接続したら、Socketを生成します、
3.socket.getOutputStream（）出力ストリームでデータを通信チャンネルに書込みます。

文字ストリームを使用します


 */

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class HomeWork01Client {
    public static void main(String[] args) throws IOException {

//        1、サーバーに接続します、IPとポートが必要になります。
        //アドレスに該当するPCに接続します、ポートは9999
        //接続が成功したら、Socketオブジェクトに戻ります。

        Socket socket = new Socket(InetAddress.getLocalHost(),9983);
        System.out.println("クライアント　socketに戻り"+socket.getClass());

//        2.接続したら、Socketを生成します、
        //該当socketオブジェクトに関連する出力ストリームを取得します。
        OutputStream outputStream = socket.getOutputStream();
//        3.socket.getOutputStream（）outputストリームでデータを通信チャンネルに書込みます。
        //outputStream.write("hello,sever".getBytes());

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        //IOストリームのメソッド（BufferedWriter）でバイトストリームを文字ストリームに変換します

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("入力してください");
            String str = scanner.next();
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);//出力

            if ("EXIT".equals(str)) {
                loop = false;
                bufferedWriter.close();
                inputStream.close();
                outputStream.close();
                socket.close();
                System.out.println("クライアントが退出しました");
            }
        }



    }
}
