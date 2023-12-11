package com.hspedu.homework;

import com.hspedu.upload.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class HomeWork03Client {
    public static void main(String[] args) throws Exception {
        //1.スキャンオブジェクトを新規します
        //inputストリームを新規します
        Scanner scanner = new Scanner(System.in);
        System.out.println("入力してください");
        String downloadFileName = scanner.next();

        //2.クライアント側はサーバーに接続します、ポートは8888です、socketオブジェクトを取得します。
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        //3.socketに関連するoutputストリームを取得します。
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(downloadFileName.getBytes());

        socket.shutdownOutput();

        //4.サーバーから戻ってきたファイル（バイト配列）を読み取ります
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());

        byte[] bytes = StreamUtils.streamToByteArray(bis);

        String destFilePath = "/Users/garfield/Desktop/"+downloadFileName+".jpg";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
        bos.write(bytes);

        //ストリームを終了します
        bos.close();
        bis.close();
        outputStream.close();
        socket.close();

        System.out.println("download終了、クライアントは退出します。");





    }
}
