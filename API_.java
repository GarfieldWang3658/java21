package com.hspedu.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class API_ {
    public static void main(String[] args) throws UnknownHostException {
       //1.該当PCのInetAddressのオブジェクトを取得します
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        //2.指定されたnameでInetAddressのオブジェクトを取得します
        InetAddress Host1 = InetAddress.getByName("garfielddeMacBook-Air.local");
        System.out.println(Host1);//IPを取得しました

        //3.ドメイン名InetAddressのオブジェクトを取得します
        InetAddress Host2 = InetAddress.getByName("www.google.com");
        System.out.println(Host2);//ドメイン名とアドレス

        //4.InetAddressのオブジェクトでIPアドレスを取得します
        String hostAddress = Host2.getHostAddress();
        System.out.println(hostAddress);//IPアドレス



    }
}
