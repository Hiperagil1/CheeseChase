package model.repository;

import com.example.cheesechase.SessionUtils;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final Connection connection;

    public UserRepository() throws SQLException {
        this.connection = Repository.getConnection();
    }

    public boolean authenticateUser(String username, String password){
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users WHERE username = ? AND password = ? "))
        {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            boolean userAuthenticated = resultSet.next(); // Verificați dacă există o înregistrare

            if(userAuthenticated){
                SessionUtils.setUsername(resultSet.getString("username"));
                SessionUtils.setUserType(User.UserType.valueOf(resultSet.getString("user_type")));
                SessionUtils.setLevel(resultSet.getInt("level")+1);
                SessionUtils.setScore(0);
            }

            return userAuthenticated; // Returnați rezultatul autentificării
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public boolean addUser(String username, String password, User.UserType userType) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users (username, password, user_type) " +
                        "VALUES (?, ?, ?)"))
        {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, userType.toString());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User registered successfully.");
                return true;
            } else {
                System.out.println("Registration unsuccessful.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer getLevel(String username) {
        Integer level = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT level FROM users WHERE username = ?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    level = resultSet.getInt("level");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return level;
    }



    public boolean setLevel(String username, int level) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE users SET level = ? WHERE username = ?")) {
            statement.setInt(1, level);
            statement.setString(2, username);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Nivelul utilizatorului " + username + " a fost actualizat cu succes.");
                return true;
            } else {
                System.out.println("Actualizarea nivelului pentru utilizatorul " + username + " a eșuat.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setScore(String username, int score) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE users SET score = ? WHERE username = ?")) {
            statement.setInt(1, score);
            statement.setString(2, username);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Scorul utilizatorului " + username + " a fost actualizat cu succes.");
                return true;
            } else {
                System.out.println("Actualizarea scorului pentru utilizatorul " + username + " a eșuat.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getScoreTable(String username) {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT username, level, score FROM users WHERE user_type = 'PLAYER' AND level = ?\n")) {
            Integer level = getLevel(username);
            if (level != null) {
                statement.setInt(1, level);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String userName = resultSet.getString("username");
                        int userLevel = resultSet.getInt("level");
                        int userScore = resultSet.getInt("score");
                        userList.add(new User(userName, userLevel, userScore));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT username, level, score FROM users WHERE user_type = 'PLAYER'")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String userName = resultSet.getString("username");
                    int userLevel = resultSet.getInt("level");
                    int userScore = resultSet.getInt("score");
                    userList.add(new User(userName, userLevel, userScore));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public boolean updatePassword(String username, String newPassword) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE users SET password = ? WHERE username = ?")) {
            statement.setString(1, newPassword);
            statement.setString(2, username);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Parola pentru utilizatorul " + username + " a fost actualizată cu succes.");
                return true;
            } else {
                System.out.println("Actualizarea parolei pentru utilizatorul " + username + " a eșuat.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String username) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM users WHERE username = ?")) {
            statement.setString(1, username);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Utilizatorul cu numele " + username + " a fost șters cu succes.");
                return true;
            } else {
                System.out.println("Nu s-a găsit niciun utilizator cu numele " + username + ".");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}