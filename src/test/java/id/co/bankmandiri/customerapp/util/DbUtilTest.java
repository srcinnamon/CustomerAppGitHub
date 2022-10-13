package id.co.bankmandiri.customerapp.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DbUtilTest {
    @Test
    void getProperty(){
        String url = DbUtil.getProperty("url");
        Assertions.assertEquals("jdbc:mysql://localhost:3307/belajar_java", url);
    }

    @Test
    void getConnection(){
        Connection connection =  DbUtil.getConnection();
        Assertions.assertNotNull(connection,"connection should be successfull");
    }
}
