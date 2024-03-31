package view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.User;
import presenter.ScorePresenter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ScoreController implements ScoreInterface{
    @FXML
    private TableView<User> scoreTable;
    @FXML
    private TableColumn<User, Number> number;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, Number> level;
    @FXML
    private TableColumn<User, Number> score;
    private ScorePresenter scorePresenter;

    @FXML
    public void initialize() {
        try {
            scorePresenter = new ScorePresenter(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void showScoreTable(List<User> scoreList) {
        ObservableList<User> userList = FXCollections.observableArrayList(scoreList);
        scoreTable.setItems(userList);

        // Setăm celulele fiecărui rând pentru fiecare coloană
        number.setCellValueFactory(column -> {
            int rowIndex = column.getTableView().getItems().indexOf(column.getValue()) + 1;
            return new SimpleIntegerProperty(rowIndex);
        });
        username.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUsername()));
        level.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getLevel()));
        score.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getScore()));
    }

    public void back (ActionEvent actionEvent) throws IOException {
        scorePresenter.back();
    }

    public void refresh(ActionEvent actionEvent) throws IOException{
        scorePresenter.refresh();
    }
}
