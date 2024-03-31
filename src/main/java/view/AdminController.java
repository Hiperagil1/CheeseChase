package view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.User;
import presenter.AdminPresenter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminController implements AdminInterface{
    @FXML
    private TextField user;
    @FXML
    private TextField createPassword;
    @FXML
    private TextField confirmPassword;
    @FXML
    private Label userExists;
    @FXML
    private Label notMatch;
    @FXML
    private Label tooShort;

    @FXML
    private Label usernameLength;
    @FXML
    private TableView<User> scoreTable;
    @FXML
    private TableColumn<User, Number> number;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, Number> level;
    @FXML
    private TableColumn<User, Number> score;
    private List<User> allUsers; // Lista completă de utilizatori


    @FXML
    private TextField search;

    private AdminPresenter adminPresenter;

    @FXML
    public void initialize() {
        try {
            adminPresenter = new AdminPresenter(this);
            // Adăugăm un listener pentru evenimentul de apăsare a tastelor la TextField-ul de căutare
            search.setOnKeyPressed(event -> {
                if (event.getCode().toString().equals("ENTER")) {
                    search();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showScoreTable(ObservableList<User> userList) {
        scoreTable.setItems(userList);
        // Setăm celulele fiecărui rând pentru fiecare coloană
        number.setCellValueFactory(column -> {
            int rowIndex = column.getTableView().getItems().indexOf(column.getValue()) + 1;
            return new SimpleIntegerProperty(rowIndex);
        });
        username.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUsername()));
        level.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getLevel()));
        score.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getScore()));
    }

    @Override
    public String getUsername() {
        return user.getText();
    }

    @Override
    public String getCreatePassword() {
        return createPassword.getText();
    }

    @Override
    public String getConfirmPasswod() {
        return confirmPassword.getText();
    }


    @Override
    public void registerStatus(boolean username, boolean usernameL, boolean createPassword, boolean confirmPassword) throws IOException {
        tooShort.setVisible(!createPassword);
        notMatch.setVisible(!confirmPassword);
        userExists.setVisible(!username);
        usernameLength.setVisible(!usernameL);
    }

    public void search(){
        adminPresenter.refresh(search.getText().trim());
    }

    public void delete() {
        // Obținem utilizatorul selectat din TableView
        User selectedUser = scoreTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            // Apelăm metoda deleteUser() din AdminPresenter și transmitem utilizatorul selectat ca parametru
            adminPresenter.deleteUser(selectedUser.getUsername());
            adminPresenter.refresh(search.getText().trim());
        } else {
            System.out.println("Nu a fost selectat niciun utilizator pentru ștergere.");
        }
    }

    public void addUser() throws IOException {
        adminPresenter.register();
    }

    public void back() throws IOException {
        adminPresenter.back();
    }

    public void changePassword() throws IOException {
        adminPresenter.changePassword();
    }


}
