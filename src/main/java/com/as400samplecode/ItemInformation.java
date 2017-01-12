package com.as400samplecode;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by lihi martin on 1/1/2017.
 */
@WebServlet(name = "ItemInformation", urlPatterns = {"/ItemInformationT"})
public class ItemInformation extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ItemInformation() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String itemCode = request.getParameter("itemCode");
        //       String itemCode = "sragfg";
//        response.setContentType("text/html");
//        response.setHeader("Cache-control", "no-cache, no-store");
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Expires", "-1");
//
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        response.setHeader("Access-Control-Max-Age", "86400");

        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();

        Item itemInfo = setInfo(itemCode);
        JsonElement countryObj = gson.toJsonTree(itemInfo);
//        if(itemInfo.getName() == null){
//            myObj.addProperty("success", false);
//        }
//        else {
//            myObj.addProperty("success", true);
//        }
//        myObj.add("countryInfo", countryObj);
//        out.println(myObj.toString());
//
//        out.close();

    }

    public Item setInfo(String itemCode) {
//        String connectionUrl = "jdbc:mysql://localhost:3306/items";
//
//        Connection connection = null;
//        Item item = new Item();
//
//        PreparedStatement stmt = null;
//        String sql = null;
//
//        Context ctx = null;
//        try {
//
//                sql = "insert into items (item) values (?)";
//                connection = getConnection();
//                stmt = connection.prepareStatement(sql);
//                stmt.setString(1, itemCode);
//                stmt.execute();
//
//
//
////            stmt = conn.prepareStatement(sql);
////            stmt.setString(1, itemCode);
////            stmt.executeQuery();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            closeConnection(connection);
//
//        }
        Item item = new Item();
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;

        try {
//            Context ctx = (Context) new InitialContext().lookup("java:comp/env");

//            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection();

            conn = getConnection();

            sql = "insert into items (item) values (?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, itemCode.trim());
            ResultSet rs = stmt.executeQuery();

//            while(rs.next()){
//                item.setItem(rs.getString("code").trim());
//                itemCode.setName(rs.getString("name").trim());
//                country.setContinent(rs.getString("continent").trim());
//                country.setRegion(rs.getString("region").trim());
//                country.setLifeExpectancy(rs.getString("lifeExpectancy") == null ? new Double(0) : Double.parseDouble(rs.getString("lifeExpectancy").trim()));
//                itemCode.setGnp(rs.getString("gnp") == null ? new Double(0)  : Double.parseDouble(rs.getString("gnp").trim()));
//            }

            rs.close();
            stmt.close();
            stmt = null;


            conn.close();
            conn = null;

        }
        catch(Exception e){System.out.println(e);}

        finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }

                stmt = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }

                conn = null;
            }
        }

        return item;
//
//    }
//
//    public static void main (String[] args) {
//        // Make sure args is not empty
//        // In which case you can display a help message on how to use this application
//        if (args[0]== "client"){
//            // This is a client
//        } else {
//            // This is a server
//        }
//    }
    }

    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection(){
        String connectionUrl = "jdbc:mysql://localhost:3306/testing";
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionUrl, "root", "lihi3263"   );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}