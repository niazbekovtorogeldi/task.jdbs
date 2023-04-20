package Config;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuration {
    private static  final String url="jdbc:postgresql://localhost:5432/for_practice";
    private static final  String userName="postgres";
    private static final String password="1234";

    public static Connection connectionToDataBace(){
        Connection connection = null;

    try {
       connection= DriverManager.getConnection(
                url,
                userName,
                password
        );
        System.out.println("SuccessFlly connected to databace");
    }catch (SQLException a){
        System.out.println(a.getMessage());
    }
    return connection;
    }
}
