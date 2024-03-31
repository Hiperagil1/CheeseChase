package presenter;

import com.example.cheesechase.Main;
import com.example.cheesechase.MapGenerator;
import com.example.cheesechase.Mouse;
import com.example.cheesechase.SessionUtils;
import model.repository.UserRepository;
import view.GameInterface;

import java.io.IOException;
import java.sql.SQLException;

public class GamePresenter {
    private static GameInterface gameView;
    private UserRepository userRepository;
    private static MapGenerator map;
    private static Mouse mouse;

    public GamePresenter(GameInterface gameView) throws SQLException {
        this.mouse = new Mouse();
        this.userRepository = new UserRepository();
        this.gameView = gameView;
    }

    public static void createMap(){
        map = new MapGenerator();
        gameView.gridView(map.getGridSize(), map.getMap());
        SessionUtils.setScore(0);
        mouse.setCurrentMouseX(0);
        mouse.setCurrentMouseY(0);
        mouse.setNextMouseX(0);
        mouse.setNextMouseY(0);
        gameView.setLevel(SessionUtils.getLevel());
        gameView.setScore(SessionUtils.getScore());
    }

    public void mouseMove(Actions action) {
        boolean isValidMove = false;
        switch (action) {
            case UP:
                if (mouse.getCurrentMouseY() != 0) {
                    mouse.setNextMouseY(mouse.getCurrentMouseY() - 1);
                    isValidMove = true;
                }
                break;
            case DOWN:
                if (mouse.getCurrentMouseY() != map.getGridSize() - 1) {
                    mouse.setNextMouseY(mouse.getCurrentMouseY() + 1);
                    isValidMove = true;
                }
                break;
            case LEFT:
                if (mouse.getCurrentMouseX() != 0) {
                    mouse.setNextMouseX(mouse.getCurrentMouseX() - 1);
                    isValidMove = true;

                }
                break;
            case RIGHT:
                if (mouse.getCurrentMouseX() != map.getGridSize() - 1) {
                    mouse.setNextMouseX(mouse.getCurrentMouseX() + 1);
                    isValidMove = true;

                }
                break;
        }
        if(isValidMove){
            SessionUtils.updateScore();
            gameView.setScore(SessionUtils.getScore());
            gameView.mouseMove(mouse.getCurrentMouseX(), mouse.getCurrentMouseY(), mouse.getNextMouseX(), mouse.getNextMouseY(), map.getGridSize());
            if(isGameOver()){
                map = new MapGenerator();
                createMap();
            }else{
                mouse.setCurrentMouseY(mouse.getNextMouseY());
                mouse.setCurrentMouseX(mouse.getNextMouseX());
            }
        }
    }

    private boolean isGameOver(){
        if(map.getMap()[mouse.getNextMouseY()][mouse.getNextMouseX()] == 'C') {
            updateLevel();
            SessionUtils.updateLevel();
            return true;
        }else if(map.getMap()[mouse.getNextMouseY()][mouse.getNextMouseX()] == 'T'){
            return true;
        }else{
            return false;
        }
    }

    private void updateLevel(){
        //salvez ultimul level terminat si scorul asociat
        userRepository.setLevel(SessionUtils.getUsername(), SessionUtils.getLevel());
        userRepository.setScore(SessionUtils.getUsername(), SessionUtils.getScore());
    }

    public void back() throws IOException {
        Main.changeScene("player.fxml");
    }

    public enum Actions {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }
}



