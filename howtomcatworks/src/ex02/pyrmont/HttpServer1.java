package ex02.pyrmont;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by frank on 2016/7/17.
 */
public class HttpServer1 {
    private static final String SHUTDOWN_COMMAND = "SHUTDOWN";

    private boolean shutdown = false;


    public static void main(String[] args) {
        HttpServer1 server  = new HttpServer1();
        server.await();
    }

    public void await() {
        ServerSocket serverSocket =  null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));

        }catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try{
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);

                // 判断资源的请求类型
                String url = request.getUri();
                if (url == null) {
                    socket.close();
                    continue;
                }

                if (url.startsWith("/servlet")) {
                    ServletProcessor1 processor1 = new ServletProcessor1();
                    processor1.process(request,response);
                }else {
                    StaticResoureProcessor processor = new StaticResoureProcessor();
                    processor.process(request, response);
                }

                socket.close();
                shutdown = url.equals(SHUTDOWN_COMMAND);
            }catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
