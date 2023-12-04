/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package hoteljavaaplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.DriverManager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author SABAR MARTUA TAMBA
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane base;

    @FXML
    private Hyperlink create_account;

    @FXML
    private TextField email;

    @FXML
    private AnchorPane formlogin;

    @FXML
    private ImageView hotelLogo;

    @FXML
    private Text hotelName;

    @FXML
    private AnchorPane hotelcom;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField password;

    @FXML
    private AnchorPane secondbase;

    @FXML
    private Label titleSignIn;

    @FXML
void login(ActionEvent event) {
    connect = connectDb();
    try {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        statement = connect.prepareStatement(sql);
        statement.setString(1, email.getText());
        statement.setString(2, password.getText());
        result = statement.executeQuery();

        if (result.next()) {
            String role = result.getString("role"); // Mendapatkan peran dari hasil query

            JOptionPane.showMessageDialog(null, "Successfully Login",
                    "Hotel PBO", JOptionPane.INFORMATION_MESSAGE);

            btnLogin.getScene().getWindow().hide();
            Parent root;
            String dashboardTitle;

            if (role.equals("admin")) {
                root = FXMLLoader.load(getClass().getResource("adminIndex.fxml"));
                dashboardTitle = "Dashboard Admin";
            } else if (role.equals("pelanggan")) {
                root = FXMLLoader.load(getClass().getResource("DashboardUser.fxml"));
                dashboardTitle = "Dashboard Pelanggan";
            } else {
                // Jika peran tidak sesuai dengan admin atau pelanggan, Anda dapat menangani kasus lain di sini.
                // Misalnya, memunculkan pesan bahwa peran tidak valid.
                JOptionPane.showMessageDialog(null, "Invalid role",
                        "Hotel PBO", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle(dashboardTitle);
            stage.show();
        } else {
            JOptionPane.showMessageDialog(null, "Wrong credentials",
                    "Hotel PBO", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        // Tangani eksepsi
        e.printStackTrace(); // Ini hanya untuk contoh, sebaiknya tangani eksepsi dengan lebih baik di aplikasi nyata.
    }
}


    //Database conn
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;

    public Connection connectDb() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3308/hotelpbo", "root", "");
            return connect;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Unable to execute query. Please try again later.",
                    "Hotel PBO", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    @FXML
    private void goToRegister() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml")); // Ganti Dashboard.fxml dengan nama file FXML halaman dashboard
            Stage stage = (Stage) base.getScene().getWindow(); // Mengambil stage dari base AnchorPane atau ganti sesuai kebutuhan
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception jika terjadi kesalahan saat pindah halaman
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
