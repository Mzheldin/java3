package hw8;

public interface AuthService {
    String authByLoginAndPassword(String login, String password);

    //User createOrActivateUser(String login, String password, String nick);
    void createOrActivateUser(String login, String password, String nick);

    String nickChange(String thisnick, String newnick);

    boolean deactivateUser(String nick);
}