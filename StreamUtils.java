package com.hspedu.homework;

import java.io.*;

public class StreamUtils {
    //inputストリームをByte[]に変換します。
    // ファイルの内容はバイト配列で読み込みます。
    public static byte[] streamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //outputストリームのオブジェクトを新規します。
        byte[] b = new byte[1024];//バイト配列を新規します。
        int len;
        while ((len=is.read(b))!=-1){//読み取りループ。
            bos.write(b,0,len);//読み取ったデーターをbosに書込みます。
        }
        byte[] array = bos.toByteArray();//bosをバイト配列に変換します。
        bos.close();
        return array;
    }
    public static String streamToString(InputStream is)throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line=reader.readLine())!=null){//nullを読み取ったら、読み取りは終了します
            builder.append(line+"\r\n");
        }
        return builder.toString();
    }
}
