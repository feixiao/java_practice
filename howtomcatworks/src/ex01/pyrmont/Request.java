package ex01.pyrmont;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by frank on 2016/7/17.
 */
public class Request {
    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public void parse() {
        StringBuffer request = new StringBuffer(2048);
        // 从输入获取数据，socket的inputStream
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        }catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        // 字节流转字符串
        for (int j=0;j<i;j++) {
            request.append((char)buffer[j]);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
        System.out.println("uri: " + uri);
    }

    private String parseUri(String requestString) {
        // 获取HTTP GET方法中的路径
        int index1,index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1 ) {
            index2 = requestString.indexOf(' ',index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1+1,index2);
            }
        }
        return  null;
    }

    public String getUri() {
        return uri;
    }


}
