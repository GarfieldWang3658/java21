package com.hspedu.homework;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class HomeWork02SenderB {
    public static void main(String[] args) throws IOException {

        //1.DatagramSocketオブジェクトを新規し、送受信の準備をします。
        //ポートは9998に指定します
        DatagramSocket socket = new DatagramSocket(8850);

        //2.送信する予定のデーターをDatagramPacketオブジェクトに入れます
        Scanner scanner = new Scanner(System.in);
        System.out.println("入力してください。");
        String str = scanner.next();
        byte[] data = str.getBytes();


        //パケージングしたDatagramPacketオブジェクト(data データーバイト配列、data.length、IPアドレス、ポート)

        DatagramPacket packet =
                new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8888);
        socket.send(packet);
        byte[] buf = new byte[1024];
         packet = new DatagramPacket(buf, buf.length);
       System.out.println("Bはデーターの受信を待機しています");

        socket.receive(packet);//packetを分解し、データーを引き出します、モニターに表示ます
        int length = packet.getLength();
        data = packet.getData();

        String s = new String(data, 0, length);
        System.out.println(s);



        socket.close();
        System.out.println("Bはリタイヤーします");




    }
}
