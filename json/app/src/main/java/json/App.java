/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package json;

import com.alibaba.fastjson.JSON;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public void test() {
        Group group = new Group();
        group.setId(0L);
        group.setName("admin");

        User guestUser = new User();
        guestUser.setId(2L);
        guestUser.setName("guest");

        User rootUser = new User();
        rootUser.setId(3L);
        rootUser.setName("root");

        group.addUser(guestUser);
        group.addUser(rootUser);

        String jsonString = JSON.toJSONString(group);

        System.out.println(jsonString);
    }

    public static void main(String[] args) {
        App app = new App();
        System.out.println(app.getGreeting());

        app.test();

    }
}