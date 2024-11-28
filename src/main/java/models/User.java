package models;

public class User {
    private int id;
    private String username;
    private int rating;

    public User(int id, String username, int rating) {
        this.id = id;
        this.username = username;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }
}
