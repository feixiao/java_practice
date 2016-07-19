package ex02.pyrmont;
import java.io.IOException;

/**
 * Created by frank on 2016/7/18.
 */
public class StaticResoureProcessor {
    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
