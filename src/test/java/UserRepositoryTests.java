import model.User;
import model.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class UserRepositoryTests {
    private UserRepository userRepository;

    @Before
    public void setUp() throws SQLException {
        userRepository = new UserRepository();
    }

    @Test
    public void testAuthenticateUser() {
        Assert.assertTrue(userRepository.authenticateUser("root", "root"));
        Assert.assertFalse(userRepository.authenticateUser("nonexistentuser", "nonexistentpassword"));
    }

    @Test
    public void testAddUser() {
        Assert.assertFalse(userRepository.addUser("root", "root", User.UserType.PLAYER));
        Assert.assertTrue(userRepository.addUser("newuser", "newpassword", User.UserType.PLAYER));
    }

    @Test
    public void testGetLevel() {
        Integer level = userRepository.getLevel("root");
        Assert.assertNotNull(level);
        System.out.println("Level for 'root' user: " + level);
    }

    @Test
    public void testSetLevel() {
        Assert.assertTrue(userRepository.setLevel("root", 2));
    }

    @Test
    public void testSetScore() {
        Assert.assertTrue(userRepository.setScore("root", 100));
    }

    @Test
    public void testGetScoreTable() {
        Assert.assertFalse(userRepository.getScoreTable("root").isEmpty());
    }

    @Test
    public void testGetAllUsers() {
        Assert.assertFalse(userRepository.getAllUsers().isEmpty());
    }

    @Test
    public void testUpdatePassword() {
        Assert.assertTrue(userRepository.updatePassword("root", "newrootpassword"));
    }

    @Test
    public void testDeleteUser() {
        Assert.assertTrue(userRepository.deleteUser("newuser"));
    }
}
