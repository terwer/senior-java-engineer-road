package space.terwer;

/**
 * @name: LoginAction
 * @author: terwer
 * @date: 2022-10-25 18:43
 **/
public class LoginAction {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String execute(){
        return "success";
    }
}
