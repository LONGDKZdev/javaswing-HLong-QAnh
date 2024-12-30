
package controller;

import model.DetailProduct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DAODetailProduct {
    
    private Connection conn ;
    
    public DAODetailProduct() {
     try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/detailbill";
            String user = "root" ;
            String password = "" ;
            conn = DriverManager.getConnection(url, user, password);
           
        }
        catch (Exception e){
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Failed to connect to database: " + e.getMessage());
        }
    
    }
    
    public ArrayList<DetailProduct> getListProduct() {
        ArrayList<DetailProduct> list = new ArrayList<>() ;
        String sql = "SELECT * FROM tbldetailbill";
        try {
             if (conn == null) {
                System.out.println("Database connection failed!");
                return list;
            }
            
           PreparedStatement ps = conn.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while (rs.next()) {
               DetailProduct detailProduct = new DetailProduct();
               detailProduct.setProductID(rs.getString("productID"));
               detailProduct.setQuantity(rs.getInt("quantity"));
               detailProduct.setPrice(rs.getInt("price"));
  
               list.add(detailProduct);
           }

        } catch(Exception e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Error fetching detail Product: " + e.getMessage());
        }
        return list;
    }
    
    
    public void AddDetailProduct(DetailProduct detail) {
    String sql = "INSERT INTO `tbldetailbill`(`productID`, `quantity`, `price`) VALUES" + 
            "(?,?,?)";
    try {
        if (conn == null) {
                System.out.println("Database connection failed!");
                return;
        }
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,detail.getProductID());
        ps.setInt(2,detail.getQuantity());
        ps.setInt(3,detail.getPrice());
        
        ps.executeUpdate();
           
    } catch(SQLException e) {
   e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error adding detail products: " + e.getMessage());
    }
    }
    
   public void removeDetailProduct(String id) {
  String sql = "DELETE FROM tbldetailbill WHERE productID=?";
  try {
      PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
      ps.executeUpdate();
  } catch(Exception e) {
   e.printStackTrace();
  }
   }
    
    
    
}
