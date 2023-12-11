package com.hspedu.udp;

import java.io.IOException;
import java.net.*;

public class UDPSenderB {
    public static void main(String[] args) throws IOException {

        //1.DatagramSocketオブジェクトを新規し、送受信の準備をします。
        //ポートは9998に指定します
        DatagramSocket socket = new DatagramSocket(9998);

        //2.送信する予定のデーターをDatagramPacketオブジェクトに入れます
        byte[] data = "hello、明日は鍋にします".getBytes();


        //パケージングしたDatagramPacketオブジェクト(data データーバイト配列、data.length、IPアドレス、ポート)

        DatagramPacket packet =
                new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 9999);
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
