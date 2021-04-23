package model;

import java.util.Date;

public class Results {
    private String login;
    private Date date;
    private int score;

    public Results(String login, Date date, boolean[] answers) {
        this.login = login;
        this.date = date;
        this.score = 0;
        for (boolean answer : answers) {
            if (answer) {
                this.score++;
            }
        }
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLogin() {
        return login;
    }

    public Date getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }
}
