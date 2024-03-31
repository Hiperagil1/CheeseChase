package presenter;

import com.example.cheesechase.Main;
import view.PlayerController;

import java.io.IOException;

public class PlayerPresenter {
    public void startGame () throws IOException {
        Main.changeScene("game.fxml");
        GamePresenter.createMap();
    }

    public void showScore() throws IOException {
        Main.changeScene("score.fxml");
    }

    public void logOut() throws IOException{
        Main.changeScene("logIn.fxml");
    }
}
