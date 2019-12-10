package services;

public enum DatabaseConnectionType {
    LOCAL("jdbc:postgresql://localhost/Greets", "coder", "coder"),
    HEROKU("dbc:postgresql://ec2-50-19-95-77.compute-1.amazonaws.com/d1747f3hptor0j", "fhdhlahsbgzmgb","138c8ba19e9280fca7c5a0a55d319f2a9a1675e8f7cac4e0df50735d063d5777");

    final String host;
    final String user;
    final String pw;

    DatabaseConnectionType(String host, String user, String pw){
        this.host = host;
        this.user = user;
        this.pw = pw;
    }
    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPw() {
        return pw;
    }
}
