package config;

public enum DefaultConnection {
    CONNECTION("localhost", "3306", "certyfikaty_database", "root", "admin");

    private String host;
    private String port;
    private String databaseName;
    private String user;
    private String password;

    DefaultConnection(String host, String port, String databaseName, String user, String password) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
