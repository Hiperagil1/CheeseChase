package view;

import com.example.cheesechase.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import presenter.LogInPresenter;

import java.io.IOException;
import java.sql.SQLException;

public class LogInController implements LogInInterface{
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Label wrongLogIn;

    private LogInPresenter logInPresenter;

    public LogInController() throws SQLException {
        logInPresenter = new LogInPresenter(this);
    }

    @Override
    public String getUserName() {
        return username.getText();
    }
    @Override
    public String getPassword() {
        return password.getText();
    }

    public void authenticateUser (ActionEvent actionEvent) throws SQLException, IOException {
        logInPresenter.authentication();
    }
    public void register(ActionEvent actionEvent) throws IOException {
        logInPresenter.register();
    }

    public void authenticateStatus(boolean status){
        wrongLogIn.setVisible(status);
    }

}

