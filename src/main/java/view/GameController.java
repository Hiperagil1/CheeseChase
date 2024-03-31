package view;

import com.example.cheesechase.MapGenerator;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Label;
import presenter.GamePresenter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController implements GameInterface {
    private Map<String, Image> imageCache = new HashMap<>();

    private GamePresenter gamePresenter;
    @FXML
    private GridPane grid;

    @FXML
    private Label level;

    @FXML
    private Label score;

    private ExecutorService executor;

    public GameController() throws SQLException {
        gamePresenter = new GamePresenter(this);
    }

    @FXML
    public void keyPressed(javafx.scene.input.KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        if (keyCode.isArrowKey()) {
            switch (keyCode.getName()) {
                case "Up":
                    gamePresenter.mouseMove(GamePresenter.Actions.UP);
                    break;
                case "Down":
                    gamePresenter.mouseMove(GamePresenter.Actions.DOWN);
                    break;
                case "Left":
                    gamePresenter.mouseMove(GamePresenter.Actions.LEFT);
                    break;
                case "Right":
                    gamePresenter.mouseMove(GamePresenter.Actions.RIGHT);
                    break;
            }
        }
    }


    @Override
    public void gridView(Integer n, char[][] map) {
        executor = Executors.newFixedThreadPool(10);
        ObservableList<Node> children = grid.getChildren();
        children.removeIf(node -> node instanceof ImageView);

        grid.setGridLinesVisible(true);

        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();


        grid.setMinWidth(380);
        grid.setMinHeight(380);
        grid.setPrefWidth(380);
        grid.setPrefHeight(380);


        for (int i = 0; i < n; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(380.0 / n); // Setăm fiecărei coloane o lățime de 1/n din grid
            grid.getColumnConstraints().add(colConst);

            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(380.0 / n); //
            grid.getRowConstraints().add(rowConst);

            for (int j = 0; j < n; j++) {
                switch (map[i][j]) {
                    case 'M':
                        loadAndAddImage("C:\\Users\\pinte\\Desktop\\An3_sem2\\Proiectare software\\Laborator\\Cheese Chase\\CheeseChase\\src\\main\\resources\\images\\Mouse.png", 380 / n - 10, j, i);
                        break;
                    case 'T':
                        loadAndAddImage("C:\\Users\\pinte\\Desktop\\An3_sem2\\Proiectare software\\Laborator\\Cheese Chase\\CheeseChase\\src\\main\\resources\\images\\Trap.png", 380 / n - 10, j, i);
                        break;
                    case 'C':
                        loadAndAddImage("C:\\Users\\pinte\\Desktop\\An3_sem2\\Proiectare software\\Laborator\\Cheese Chase\\CheeseChase\\src\\main\\resources\\images\\Cheese.png", 380 / n - 10, j, i);
                        break;
                }
            }
        }
    }

    // Metodă pentru încărcarea și adăugarea unei imagini în fundal
    private void loadAndAddImage(String imagePath, int size, int columnIndex, int rowIndex) {
        // Verifică dacă imaginea este deja în cache
        Image image = imageCache.get(imagePath);

        if (image == null) {
            // Dacă imaginea nu este în cache, încarc-o din fișier
            image = new Image(imagePath);

            // Adaugă imaginea în cache pentru utilizări ulterioare
            imageCache.put(imagePath, image);
        }

        // Creează un ImageView pentru imagine și adaugă-l în grid
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        grid.add(imageView, columnIndex, rowIndex);
    }



    // Metodă pentru a elimina nodul de la o anumită poziție din grid
    private void removeNodeAtPosition(int columnIndex, int rowIndex) {
        Node node = null;
        ObservableList<Node> children = grid.getChildren();
        for (Node child : children) {
            Integer colIndex = GridPane.getColumnIndex(child);
            Integer row = GridPane.getRowIndex(child);
            if (colIndex != null && row != null && colIndex.intValue() == columnIndex && row.intValue() == rowIndex) {
                node = child;
                break;
            }
        }
        if (node != null) {
            grid.getChildren().remove(node);
        }
    }


    public void mouseMove(int currentX, int currentY, int nextX, int nextY, int n) {
        // Creează o nouă imagine pentru spațiul gol
        ImageView blank = new ImageView("C:\\Users\\pinte\\Desktop\\An3_sem2\\Proiectare software\\Laborator\\Cheese Chase\\CheeseChase\\src\\main\\resources\\images\\Blank.png");
        // Setează dimensiunile imaginii
        int d = 380 / n - 10; // Sau dimensiunea dorită
        blank.setFitWidth(d);
        blank.setFitHeight(d);

        // Șterge nodul anterior de la poziția curentă
        removeNodeAtPosition(currentX, currentY);
        removeNodeAtPosition(currentX, currentY);

        // Creează o nouă imagine pentru șoarece
        ImageView mouse = new ImageView("C:\\Users\\pinte\\Desktop\\An3_sem2\\Proiectare software\\Laborator\\Cheese Chase\\CheeseChase\\src\\main\\resources\\images\\Mouse.png");
        // Setează dimensiunile imaginii
        mouse.setFitWidth(d);
        mouse.setFitHeight(d);

        // Adaugă imaginea cu șoarecele la noua poziție
        grid.add(mouse, nextX, nextY);
    }

    public void setLevel(Integer level){
        this.level.setText(level.toString());
    }
    public void setScore(Integer score){
        this.score.setText(score.toString());
    }

    public void back(ActionEvent actionEvent) throws IOException {
        gamePresenter.back();
    }

}
