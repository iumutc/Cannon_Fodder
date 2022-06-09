import java.util.ArrayList;

public class Database {
    protected int score;

    public Database(int score, String userName) {
        this.score = score;
        this.userName = userName;
    }

    protected String userName;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
