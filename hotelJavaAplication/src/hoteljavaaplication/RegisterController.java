/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hoteljavaaplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.DriverManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author SABAR MARTUA TAMBA
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField alamat;

    @FXML
    private AnchorPane base;

    @FXML
    private TextField email;

    @FXML
    private AnchorPane form;

    @FXML
    private AnchorPane formDesign;

    @FXML
    private Label labelSignUp;

    @FXML
    private Label labelSignUp1;

    @FXML
    private ImageView logohotel;

    @FXML
    private TextField nama;

    @FXML
    private TextField password;

    @FXML
    private Hyperlink loginlink;
    
    @FXML
    private Button registerButton;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleRegistration() {
        String dbUrl = "jdbc:mysql://localhost:3308/hotelpbo"; // Ganti nama_database dengan nama database yang kamu gunakan
        String dbUsername = "root"; // Ganti username dengan username database
        String dbPassword = ""; // Ganti password dengan password database

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            String query = "INSERT INTO users (email, nama, alamat, password, role) VALUES (?, ?, ?, ?, 'pelanggan')";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, email.getText());
            preparedStatement.setString(2, nama.getText());
            preparedStatement.setString(3, alamat.getText());
            preparedStatement.setString(4, password.getText());

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                goToDashboard();
                // Lakukan tindakan sesuai kebutuhan, seperti pindah ke halaman login atau tampilkan pesan sukses
            } else {
                JOptionPane.showMessageDialog(null, "Invalid role",
                        "Hotel PBO", JOptionPane.ERROR_MESSAGE);
                // Tampilkan pesan gagal atau lakukan tindakan lain sesuai kebutuhan
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    @FXML
    private void handleLoginLink(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); // Ganti Login.fxml dengan nama file FXML halaman login
            Stage stage = (Stage) loginlink.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception jika terjadi kesalahan saat pindah halaman
        }
    }

    private void goToDashboard() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DashboardUser.fxml")); // Ganti Dashboard.fxml dengan nama file FXML halaman dashboard
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
