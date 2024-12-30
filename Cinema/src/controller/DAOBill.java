
package controller;

import model.Bill;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DAOBill {
      private Connection conn ;
      
      public DAOBill() {
      try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bill";
            String user = "root" ;
            String password = "" ;
            conn = DriverManager.getConnection(url, user, password);
            
        }
        catch (Exception e){
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Failed to connect to database: " + e.getMessage());
        }
      }
      
      public ArrayList<Bill> getListBill() {
        ArrayList<Bill> list = new ArrayList<>() ;
        String sql = "SELECT * FROM tblbill";
        try {
             if (conn == null) {
                System.out.println("Database connection failed!");
                return list;
            }
            
           PreparedStatement ps = conn.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while (rs.next()) {
               Bill bill = new Bill();
               
               bill.setBillID(rs.getInt("id"));
               bill.setDateOrder(rs.getString("dateOrder"));
               bill.setPrice(rs.getInt("price"));
               list.add(bill);
           }

        } catch(Exception e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Error fetching bills: " + e.getMessage());
        }
        return list;
    }
      
      public ArrayList<Bill> searchDate(String keyword) {
        ArrayList<Bill> list = new ArrayList<>();
        String sql = "SELECT * FROM tblbill WHERE dateOrder LIKE ?";
        try {
            if (conn == null) {
                System.out.println("Database connection failed!");
                return list;
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) /*chứa những dữ liệu có điểm chung với keyword*/  {
                Bill bill = new Bill();

                bill.setBillID(rs.getInt("id"));
                bill.setDateOrder(rs.getString("dateOrder"));
                bill.setPrice(rs.getInt("price"));
               
                list.add(bill);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error searching for bills: " + e.getMessage());
        }
        return list;
    }
      
      
      
      
      public void AddBill(Bill bill) { 
    String sql = "INSERT INTO tblbill(id, dateOrder, price) VALUES"
            + "(?,?,?)";
    try {
        if (conn == null) {
                System.out.println("Database connection failed!");
                return;
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,bill.getBillID());
        ps.setString(2,bill.getDateOrder());
        ps.setInt(3,bill.getPrice());
         ps.executeUpdate();
         
    } catch(SQLException e) {
   e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error adding bill: " + e.getMessage());
    }
    }
      
     public void removeBill(int id) {
  String sql = "DELETE FROM tblbill WHERE id=?";
  try {
      PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
      ps.executeUpdate();
  } catch(Exception e) {
   e.printStackTrace();
  }
   } 
      
      
      
      
}
