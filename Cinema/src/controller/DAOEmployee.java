package controller;


import model.Employee;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.swing.JOptionPane;

public class DAOEmployee {
    private Connection conn ;
    
    public DAOEmployee() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/employee";
            String user = "root" ;
            String password = "" ;
            conn = DriverManager.getConnection(url, user, password);
           
        }
        catch (Exception e){
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Failed to connect to database: " + e.getMessage());
        }
    }

    public ArrayList<Employee> getListEmployee() {
        ArrayList<Employee> list = new ArrayList<>() ;
        String sql = "SELECT * FROM tblemployee";
        try {
             if (conn == null) {
                System.out.println("Database connection failed!");
                return list;
            }
            
           PreparedStatement ps = conn.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while (rs.next()) {
               Employee employee = new Employee();
               
               employee.setEmployeeID(rs.getString("employeeID"));
               employee.setName(rs.getString("name"));
               employee.setPosition(rs.getString("position"));
               employee.setDate(rs.getString("date"));
               employee.setGender(rs.getString("gender"));
               employee.setAddress(rs.getString("address"));
               employee.setPhoneNumber(rs.getString("phoneNumber"));
               employee.setEmail(rs.getString("email"));
               employee.setSalary(rs.getInt("salary"));
               list.add(employee);
           }

        } catch(Exception e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Error fetching employees: " + e.getMessage());
        }
        return list;
    }
    public void AddEmployee(Employee employee) {
    String sql = "INSERT INTO `tblemployee`(`employeeID`, `name`, `position`, `date`, `gender`, `address`, `phoneNumber`, `email`, `salary`) VALUES " 
       + "(?,?,?,?,?,?,?,?,?)";
    try {
        if (conn == null) {
                System.out.println("Database connection failed!");
                return;
        }
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,employee.getEmployeeID());
        ps.setString(2,employee.getName());
        ps.setString(3,employee.getPosition());
        ps.setString(4,employee.getDate());
        ps.setString(5,employee.getGender());
        ps.setString(6,employee.getAddress());
        ps.setString(7,employee.getPhoneNumber());
        ps.setString(8,employee.getEmail());
        ps.setInt(9,employee.getSalary());
        ps.executeUpdate();
           
    } catch(SQLException e) {
   e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error adding employee: " + e.getMessage());
    }
    }
    
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE tblemployee SET name=?, position=?, date=?, gender=?, address=?, phoneNumber=?, email=?, salary=? WHERE employeeID=?";
        try {
            if (conn == null) {
                System.out.println("Database connection failed!");
                return false;
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getPosition());
            ps.setString(3, employee.getDate());
            ps.setString(4, employee.getGender());
            ps.setString(5, employee.getAddress());
            ps.setString(6, employee.getPhoneNumber());
            ps.setString(7, employee.getEmail());
            ps.setInt(8, employee.getSalary());
            ps.setString(9, employee.getEmployeeID());
            int rowsUpdated = ps.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating employee: " + e.getMessage());
        }
        return false;
    }
    
     public boolean deleteEmployee(String employeeID) {
        String sql = "DELETE FROM tblemployee WHERE employeeID=?";
        try {
            if (conn == null) {
                System.out.println("Database connection failed!");
                return false;
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employeeID);
            int rowsDeleted = ps.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting employee: " + e.getMessage());
        }
        return false;
    }
    
    public ArrayList<Employee> searchEmployee(String keyword) {
        ArrayList<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM tblemployee WHERE employeeID LIKE ? OR name LIKE ?";
        try {
            if (conn == null) {
                System.out.println("Database connection failed!");
                return list;
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();

                employee.setEmployeeID(rs.getString("employeeID"));
                employee.setName(rs.getString("name"));
                employee.setPosition(rs.getString("position"));
                employee.setDate(rs.getString("date"));
                employee.setGender(rs.getString("gender"));
                employee.setAddress(rs.getString("address"));
                employee.setPhoneNumber(rs.getString("phoneNumber"));
                employee.setEmail(rs.getString("email"));
                employee.setSalary(rs.getInt("salary"));
                list.add(employee);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error searching for employees: " + e.getMessage());
        }
        return list;
    }
}