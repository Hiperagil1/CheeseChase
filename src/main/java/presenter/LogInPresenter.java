package presenter;

import com.example.cheesechase.Main;
import com.example.cheesechase.SessionUtils;
import model.User;
import view.LogInInterface;
import model.repository.UserRepository;

import java.io.IOException;
import java.sql.SQLException;

public class LogInPresenter {
    private LogInInterface logInView;

    UserRepository userRepository;

    public void register() throws IOException{
        Main.changeScene("register.fxml");
    }
    public LogInPresenter(LogInInterface logInView) throws SQLException {
        this.userRepository = new UserRepository();
        this.logInView = logInView;
    }

    public void authentication() throws IOException {
        String username = logInView.getUserName();
        String password = logInView.getPassword();
        boolean authenticated = userRepository.authenticateUser(username, password);
        logInView.authenticateStatus(!authenticated);
        if(authenticated){
            if(SessionUtils.getUserType() == User.UserType.PLAYER){
                Main.changeScene("player.fxml");
            } else if(SessionUtils.getUserType() == User.UserType.ADMINISTRATOR){
                Main.changeScene("admin.fxml");
            }
        }
    }
}
