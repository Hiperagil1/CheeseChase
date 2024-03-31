package presenter;

import com.example.cheesechase.Main;
import com.example.cheesechase.SessionUtils;
import model.repository.UserRepository;
import view.ScoreInterface;

import java.io.IOException;
import java.sql.SQLException;

public class ScorePresenter {

    private  ScoreInterface scoreView;
    private  UserRepository userRepository;

    public ScorePresenter(ScoreInterface scoreInterface) throws SQLException {
        userRepository = new UserRepository();
        this.scoreView = scoreInterface;
        scoreView.showScoreTable(userRepository.getScoreTable(SessionUtils.getUsername()));
    }

    public void back() throws IOException {
        Main.changeScene("player.fxml");
    }

    public void refresh() throws IOException{
        scoreView.showScoreTable(userRepository.getScoreTable(SessionUtils.getUsername()));
    }
}
