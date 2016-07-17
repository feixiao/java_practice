package ex01.pyrmont;

import com.sun.org.apache.regexp.internal.RE;
import jdk.nashorn.internal.ir.RuntimeNode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by frank on 2016/7/17.
 */
public class HttpServer {
    public static  final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // 循环等待请求
        while(!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                socket.close();

                String url = request.getUri();
                if (url != null){
                    shutdown = url.equals(SHUTDOWN_COMMAND);
                }

            }catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }

    }

}
