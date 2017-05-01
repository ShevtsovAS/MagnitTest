package main.java.ru.magnit.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/test";
    private static final String user = "user";
    private static final String password = "password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Test test = new Test(connection, 1000000);
            test.createXml("1.xml");
            XmlUtil.convertWithXSLT("1.xml", "2.xml", "src/main/resources/style.xsl");
            System.out.println("Sum of elements = " + XmlUtil.countSumOfElements("2.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
