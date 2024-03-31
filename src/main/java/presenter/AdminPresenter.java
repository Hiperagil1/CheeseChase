package presenter;

import com.example.cheesechase.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import model.repository.UserRepository;
import view.AdminInterface;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AdminPresenter {

    private AdminInterface adminView;
    private UserRepository userRepository;
    public AdminPresenter(AdminInterface adminInterface) throws SQLException {
        userRepository = new UserRepository();
        this.adminView = adminInterface;
        //adminView.showScoreTable(userRepository.getAllUsers());
        refresh("");
    }

    public void back() throws IOException {
        Main.changeScene("login.fxml");
    }

    public void refresh(String searchText){
        List<User> allUsers = userRepository.getAllUsers();
        ObservableList<User> userList = FXCollections.observableArrayList();

        if (searchText.isEmpty()) {
            userList.addAll(allUsers); // Adăugăm întreaga listă de utilizatori
        } else {
            // Filtrăm lista pentru a găsi doar utilizatorul căutat
            List<User> filteredUsers = allUsers.stream()
                    .filter(user -> user.getUsername().equalsIgnoreCase(searchText))
                    .collect(Collectors.toList());
            userList.addAll(filteredUsers); // Adăugăm utilizatorul filtrat în lista de afișare
        }
        adminView.showScoreTable(userList);
    }

    public void deleteUser(String username){
        userRepository.deleteUser(username);
    }

    public void register() throws IOException {
        String username = adminView.getUsername();
        String createPassword = adminView.getCreatePassword();
        String confirmPassword = adminView.getConfirmPasswod();

        boolean usernameLabel = true;
        boolean usernameLengthLabel = true;
        boolean createPasswordLabel = true;
        boolean confirmPasswordLabel = true;

        if(username.length() >= 4){
            if(createPassword.length() >= 4){
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

        adminView.registerStatus(usernameLabel, usernameLengthLabel,  createPasswordLabel, confirmPasswordLabel);
    }

    public void changePassword() throws IOException {
        String username = adminView.getUsername();
        String createPassword = adminView.getCreatePassword();
        String confirmPassword = adminView.getConfirmPasswod();

        boolean createPasswordLabel = true;
        boolean confirmPasswordLabel = true;
        boolean usernameLabel = true;
        boolean usernameLengthLabel = true;

        if(username.length() >= 4){
            if(createPassword.length() >= 4){
                createPasswordLabel = true;
                if(createPassword.equals(confirmPassword)){
                    confirmPasswordLabel = true;
                     userRepository.updatePassword(username, createPassword);
                }else{
                    confirmPasswordLabel = false;
                }
            }else{
                createPasswordLabel = false;
            }
        }else{
            usernameLengthLabel = false;
        }
        adminView.registerStatus(usernameLabel, usernameLengthLabel,  createPasswordLabel, confirmPasswordLabel);
    }



}
