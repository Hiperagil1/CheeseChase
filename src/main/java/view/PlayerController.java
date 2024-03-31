package view;

import com.example.cheesechase.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import presenter.GamePresenter;
import presenter.PlayerPresenter;

import java.io.IOException;

public class PlayerController implements PlayerInterface{
    @FXML
    private Button startGame;
    @FXML
    private Button scoreBoard;
    @FXML
    private Button changePassword;
    @FXML
    private Button logOut;
    private PlayerPresenter playerPresenter;

    public PlayerController(){
        this.playerPresenter = new PlayerPresenter();
    }
    public void startGame(ActionEvent actionEvent) throws IOException {
        playerPresenter.startGame();
    }

    public void showScore(ActionEvent actionEvent) throws IOException{
        playerPresenter.showScore();
    }

    public void logOut(ActionEvent actionEvent) throws IOException{
        playerPresenter.logOut();
    }
}
