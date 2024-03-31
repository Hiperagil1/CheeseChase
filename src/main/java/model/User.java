package model;


public class User {
    private String username;
    private String password;
    private UserType userType;

    private int level;
    private int score;

    public enum UserType{
        PLAYER,
        ADMINISTRATOR
    }

    public User(String username, String password, UserType userType){
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public User(String username, int level, int score){
        this.username = username;
        this.level = level;
        this.score = score;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }
}
