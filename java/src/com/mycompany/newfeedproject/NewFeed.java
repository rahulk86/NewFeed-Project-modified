/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.newfeedproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class NewFeed {

    public static void load() throws ClassNotFoundException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/tabnewfeed";
            String UserName = "root";
            String Passsword = "Rahul@123";
            Connection con = DriverManager.getConnection(url, UserName, Passsword);
            if (con.isClosed()) {
                System.out.println("coonection closed");
            } else {
                String insrt = "insert into tabnewfeed(tcsNewFeedName,tcsNewFeedEmail,tcsNewFeedPaswrd) values(?,?,?)";
                PreparedStatement pstm = con.prepareStatement(insrt);
                pstm.setString(1, "Rahul kumar t");
                pstm.setString(2, "rk062886@gmail.com");
                pstm.setString(3, "Rahul@123");
                pstm.executeUpdate();
                System.out.println("coonection created.......");
            }

        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
