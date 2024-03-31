package view;

import javafx.collections.ObservableList;
import model.User;

import java.io.IOException;

public interface AdminInterface {
    public void showScoreTable(ObservableList<User> allUsers);
    public String getUsername();
    public String getCreatePassword();
    public String getConfirmPasswod();

    public void registerStatus(boolean username, boolean usernameLength, boolean createPassword, boolean confirmPassword) throws IOException;
}
