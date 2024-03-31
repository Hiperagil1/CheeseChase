package com.example.cheesechase;

import model.User;

public class SessionUtils {
    private static String username;
    private static User.UserType userType;

    private static int level;
    private static int score;

    public static void setUsername(String username) {
        SessionUtils.username = username;
    }

    public static void setUserType(User.UserType userType) {
        SessionUtils.userType = userType;
    }

    public static void updateScore(){
        score++;
    }

    public static void updateLevel(){
        level++;
    }

    public static void setLevel(int level) {
        SessionUtils.level = level;
    }

    public static void setScore(int score) {
        SessionUtils.score = score;
    }

    public static int getLevel() {
        return level;
    }

    public static int getScore() {
        return score;
    }

    public static String getUsername() {
        return username;
    }

    public static User.UserType getUserType() {
        return userType;
    }
}
