package view;

import com.example.cheesechase.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presenter.RegisterPresenter;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController implements RegisterInterface{
    @FXML
    private TextField username;
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

    private RegisterPresenter registerPresenter;

    public RegisterController() throws SQLException{
        registerPresenter = new RegisterPresenter(this);
    }

    @Override
    public String getUsername() {
        return username.getText();
    }

    @Override
    public String getCreatePassword() {
        return createPassword.getText();
    }

    @Override
    public String getConfirmPasswod() {
        return confirmPassword.getText();
    }

    public void register (ActionEvent actionEvent) throws IOException {
        registerPresenter.register();
    }

    public void back (ActionEvent actionEvent) throws IOException {
        registerPresenter.back();
    }

    public void registerStatus(boolean username, boolean usernameL, boolean createPassword, boolean confirmPassword) throws IOException {
        tooShort.setVisible(!createPassword);
        notMatch.setVisible(!confirmPassword);
        userExists.setVisible(!username);
        usernameLength.setVisible(!usernameL);
    }
}
