package User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Admin.User;

public class SettingController implements Initializable {

    private List<User> users; // Variabel instance untuk menyimpan daftar pengguna

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField homeAddressField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField joinDateField;

    @FXML
    private TextField stemFieldField;

    @FXML
    private TextField statusField;

    @FXML
    private Button exitbutton;

    @FXML
    private Button savebutton;

    @FXML
    void handleExitButton(ActionEvent event) {
        // Logika untuk menangani tombol exit
        System.out.println("Exit button clicked!");
        // Tambahkan logika lainnya di sini
    }

    @FXML
    void handleSaveChanges(ActionEvent event) {
        // Logika untuk menangani tombol save changes
        System.out.println("Save changes button clicked!");
        // Tambahkan logika lainnya di sini
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUsersFromCSV();
        // Tampilkan data untuk ID 1
        displayUserById(1);
    }

    private void loadUsersFromCSV() {
        String csvFile = "src/Admin/users.csv";

        try {
            users = Files.lines(Paths.get(csvFile))
                    .skip(1) // skip header
                    .map(line -> {
                        String[] fields = line.split(",");
                        int id = Integer.parseInt(fields[0].trim());
                        String name = fields[1].trim();
                        String email = fields[2].trim();
                        String homeAddress = fields[3].trim();
                        String password = fields[4].trim();
                        LocalDate joinDate = LocalDate.parse(fields[5].trim());
                        String stemField = fields[6].trim();
                        String status = fields[7].trim();
                        return new User(id, name, email, homeAddress, password, joinDate, stemField, status);
                    })
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayUserById(int id) {
        // Implementasi untuk menampilkan data pengguna berdasarkan ID
        // Di sini kita hanya menampilkan data untuk ID 1
        User user = getUserById(id);

        if (user != null) {
            fullNameField.setText(user.getName());
            emailField.setText(user.getEmail());
            homeAddressField.setText(user.getAddress());
            passwordField.setText(user.getPassword());
            joinDateField.setText(user.getJoinDate().toString());
            stemFieldField.setText(user.getStemField());
            statusField.setText(user.getStatus());
        }
    }

    private User getUserById(int id) {
        // Mengambil data pengguna dari list yang sudah dimuat dari CSV
        // Sesuaikan dengan implementasi Anda
        // Contoh sederhana: ambil data dari list users yang sudah dimuat
        // Untuk keperluan contoh ini, kita akan melakukan iterasi di dalam list users
        // Namun, untuk aplikasi yang sebenarnya, lebih baik menggunakan struktur data yang lebih efisien seperti Map atau Database
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; // Jika tidak ditemukan
    }
}
