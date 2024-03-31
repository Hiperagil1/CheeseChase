package view;

import java.io.IOException;

public interface RegisterInterface {
    public String getUsername();
    public String getCreatePassword();
    public String getConfirmPasswod();
    public void registerStatus(boolean username, boolean usernameLength, boolean createPassword, boolean confirmPassword) throws IOException;
}
