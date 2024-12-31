package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnCreateAccount;

    public Login() {
        // Set JFrame properties
        setTitle("Cinema Login");
        setSize(1200, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // Username
        JLabel lblUsername = new JLabel("Login Name:");
        lblUsername.setForeground(new Color(255, 255, 255));
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblUsername.setBounds(247, 217, 133, 35);
        getContentPane().add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(390, 212, 150, 35);
        getContentPane().add(txtUsername);

        // Password
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(new Color(255, 255, 255));
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPassword.setBounds(247, 248, 126, 32);
        getContentPane().add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(390, 257, 150, 30);
        getContentPane().add(txtPassword);

        
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnLogin.setBounds(420, 297, 120, 30);
        btnLogin.setBackground(new Color(59, 89, 152));
        btnLogin.setForeground(Color.BLACK);
        getContentPane().add(btnLogin);

        
        btnCreateAccount = new JButton("Canel");
        btnCreateAccount.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCreateAccount.setBounds(253, 297, 120, 30);
        btnCreateAccount.setBackground(Color.GREEN);
        btnCreateAccount.setForeground(Color.BLACK);
        getContentPane().add(btnCreateAccount);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/view/admin.jpg")));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 0, 797, 473);
        getContentPane().add(lblNewLabel);

       
        btnLogin.addActionListener(new ActionListener() {
            @Override
public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = String.valueOf(txtPassword.getPassword());

                // Demo authentication
                if ("user".equals(username) && "1234".equals(password)) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                    view.AnalystManage a = new AnalystManage();
                    a.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu.");
                }
            }
        });

        btnCreateAccount.addActionListener(e -> 
            JOptionPane.showMessageDialog(null, "Hãy truy cập trang đăng ký tài khoản mới.")
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}