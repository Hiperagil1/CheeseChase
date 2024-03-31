package view;

import com.example.cheesechase.MapGenerator;

import java.awt.event.KeyEvent;

public interface GameInterface {
    public void gridView(Integer n, char[][] map);
    public void mouseMove(int currentX, int currentY, int nextX, int nextY, int n);

    public void setLevel(Integer level);
    public void setScore(Integer score);
}
