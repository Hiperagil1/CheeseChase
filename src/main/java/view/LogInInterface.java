package view;

import com.example.cheesechase.Main;

import java.io.IOException;

public interface LogInInterface {
    public String getUserName();
    public String getPassword();
    public void authenticateStatus(boolean authenticated);

}
