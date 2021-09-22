package br.com.bandtec.ink4you2;

//import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Configuration;

import java.sql.*;

@Configuration
public class Conexao {

    private BasicDataSource banco;

    public Conexao() throws SQLException {

        this.banco = new BasicDataSource();
        banco.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        banco.setUrl("jdbc:sqlserver://ink4you.database.windows.net:1433;database=bd-ink4you;user=ink4you@ink4you;password={#Gfgrupo8};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        banco.setUsername("ink4you");
        banco.setPassword("#Gfgrupo8");

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://ink4you.database.windows.net:1433;database=bd-ink4you;user=ink4you@ink4you;password={#Gfgrupo8};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
            String SQL = "SELECT * from Usuario";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

//    public BasicDataSource getBanco() {
//        return banco;
//    }
}
