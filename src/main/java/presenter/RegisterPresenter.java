package presenter;

import com.example.cheesechase.Main;
import model.User;
import view.RegisterInterface;
import model.repository.UserRepository;

import java.io.IOException;
import java.sql.SQLException;


public class RegisterPresenter {
    private RegisterInterface registerView;
    UserRepository userRepository;

    public RegisterPresenter(RegisterInterface registerView) throws SQLException {
        this.userRepository = new UserRepository();
        this.registerView = registerView;
    }

    public void back() throws IOException {
        Main.changeScene("login.fxml");
    }


    public void register() throws IOException {
        String username = registerView.getUsername();
        String createPassword = registerView.getCreatePassword();
        String confirmPassword = registerView.getConfirmPasswod();

        boolean usernameLabel = true;
        boolean usernameLengthLabel = true;
        boolean createPasswordLabel = true;
        boolean confirmPasswordLabel = true;

        if(username.length() >= 4){
            if(createPassword.length() >= 8){
                createPasswordLabel = true;
                if(createPassword.equals(confirmPassword)){
                    confirmPasswordLabel = true;
                    User user = new User(username, createPassword, User.UserType.PLAYER) ;
                    usernameLabel = userRepository.addUser(username, createPassword, User.UserType.PLAYER);
                }else{
                    confirmPasswordLabel = false;
                }
            }else{
                createPasswordLabel = false;
            }
        }else{
            usernameLengthLabel = false;
        }
        if (usernameLabel && createPasswordLabel && confirmPasswordLabel && usernameLengthLabel){
            Main.changeScene("login.fxml");
        }
        registerView.registerStatus(usernameLabel, usernameLengthLabel,  createPasswordLabel, confirmPasswordLabel);
    }
}
