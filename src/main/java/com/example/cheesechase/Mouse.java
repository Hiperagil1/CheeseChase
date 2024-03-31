package com.example.cheesechase;

public class Mouse {
    private int currentMouseX;
    private int currentMouseY;
    private int nextMouseX;
    private int nextMouseY;


    public Mouse(){
        this.currentMouseX = 0;
        this.currentMouseY = 0;
        this.nextMouseX = 0;
        this.nextMouseY = 0;
    }

    public void setCurrentMouseX(int currentMouseX) {
        this.currentMouseX = currentMouseX;
    }

    public void setCurrentMouseY(int currentMouseY) {
        this.currentMouseY = currentMouseY;
    }

    public void setNextMouseX(int nextMouseX) {
        this.nextMouseX = nextMouseX;
    }

    public void setNextMouseY(int nextMouseY) {
        this.nextMouseY = nextMouseY;
    }

    public int getCurrentMouseX() {
        return currentMouseX;
    }

    public int getCurrentMouseY() {
        return currentMouseY;
    }

    public int getNextMouseX() {
        return nextMouseX;
    }

    public int getNextMouseY() {
        return nextMouseY;
    }
}
