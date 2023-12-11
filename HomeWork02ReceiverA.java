package com.hspedu.homework;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HomeWork02ReceiverA {
    public static void main(String[] args) throws IOException {
        //DatagramSocketのオブジェクトを新規し、データーを受け取る準備をします。
        //ポートを9999に指定します。
        DatagramSocket socket = new DatagramSocket(8888);
        //DatagramPacketを新規し、データーを受け取る準備をします。
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        //受信メソッドを引き出します,
        // 送信されたDatagramPacketのオブジェクトをpacketオブジェクトに充填します
        //DatagramPacketはポート9999に送信された場合はデーターを受け取ることになります
        //DatagramPacketはポート9999に送信されていなかった場合は渋滞することになります
        System.out.println("Aはデーターの受信を待機しています");
        socket.receive(packet);
        //packetを分解し、データーを引き出します、モニターに表示ます
        int length = packet.getLength();
        byte[] data = packet.getData();

        String s = new String(data, 0, length);
        //System.out.println(s);
        String answer = "";
        if ( ("四大名著").equals(s)){
            answer = "中国の四大名書はそれぞれ「西游记」「红楼梦」「三国演义」「水浒传」になります";
        }else {
            answer = "何を言っているのは分かりません。";
        }

        data = answer.getBytes();
         packet =
                new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8850);
        socket.send(packet);


        socket.close();
        System.out.println("Aはリタイヤーします");



    }
}
