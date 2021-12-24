package com.nkl.nmu.erp.supplies.controller; 
import static java.lang.Class.forName;
import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
import java.sql.SQLException;
;
public class Consql {

    

    public static Connection getCon() {
        Connection con = null;

        try {
            forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost\\MSSQLSERVER:1433;databaseName=NMU_ERP";
            con = getConnection(url, "sa", "nklV1");

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return con;
    }

}
