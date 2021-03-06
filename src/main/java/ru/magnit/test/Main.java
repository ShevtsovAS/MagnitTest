package main.java.ru.magnit.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    private static final String URL = "jdbc:postgresql://localhost:5432/test";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Подключаемся к базе данных и заполняем таблицу TEST 1..n элементами
            TestDb testDb = new TestDb(connection, 1000000);

            // Формируем XML-документ 1.xml
            testDb.createXml("1.xml");
            testDb.disconnect();

            // преобразуем содержимое 1.xml посредством XSLT к 2.xml
            XmlUtil.convertWithXSLT("1.xml", "2.xml", "src/main/resources/style.xsl");

            // парсим 2.xml и выводим арифметическую сумму значений всех атрибутов field в консоль
            System.out.println("Sum of elements = " + XmlUtil.countSumOfElements("2.xml"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
