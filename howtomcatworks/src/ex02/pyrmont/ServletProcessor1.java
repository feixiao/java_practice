package ex02.pyrmont;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

// 来自tomcat的包,需要自己添加
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by frank on 2016/7/17.
 */
public class ServletProcessor1 {

    public void process(Request request, Response response) {
        String uri = request.getUri();

        // 来获得servlet的名字
        String servletName = uri.substring(uri.lastIndexOf("/")+1);
        URLClassLoader loader = null;

        try {
            // 类加载器直接在Constants指向的目录里边查找。WEB_ROOT就是指向工作目录下面的webroot目录。
            URL[]  urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file",null,classPath.getCanonicalPath()+File.separator)).toString();
            urls[0] = new URL(null,repository,streamHandler);
            loader = new URLClassLoader(urls);
        }catch(IOException e) {
            e.printStackTrace();
        }

        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet) myClass.newInstance();
            // 这会危害安全性。知道这个 servlet 容器的内部运作的 Servlet 程序员可以分别把
            // ServletRequest 和 ServletResponse 实 例 向 下 转 换 为 ex02.pyrmont.Request 和
            // ex02.pyrmont.Response，并调用他们的公共方法。拥有一个 Request 实例，它们就可以调用 parse
            // 方法。拥有一个 Response 实例，就可以调用 sendStaticResource 方法。
            servlet.service((ServletRequest) request, (ServletResponse) response);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        catch (Throwable e) {
            System.out.println(e.toString());
        }

    }
}
