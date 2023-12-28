//User.java

class User {
    private String username;
    private String password;
    public User() {}
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        if (this instanceof Admin) {
            return username + "," + password + ",Admin";
        } else {
            return username + "," + password + ",Customer";
        }
    }
}