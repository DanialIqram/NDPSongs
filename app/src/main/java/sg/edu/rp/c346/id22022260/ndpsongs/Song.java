package sg.edu.rp.c346.id22022260.ndpsongs;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int id, String title, String singers, int year, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int get_id() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String newTitle) { title = newTitle; }

    public String getSingers() {
        return singers;
    }
    public void setSingers(String newSingers) {
        singers = newSingers;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int newYear) {
        year = newYear;
    }

    public int getStars() {
        return stars;
    }
    public void setStars(int newStars) {
        stars = newStars;
    }

    public String getStarDisplay() {
        String starText = "";
        for (int a = 0; a < stars; a++) {
            starText += "⭐";
        }

        return starText;
    }

    @NonNull
    @Override
    public String toString() {
        String starText = "";
        for (int a = 0; a < stars; a++) {
            starText += "⭐";
        }

        return String.format("Title: %s\nSingers: %s\nYear: %d\nStars: %s", title, singers, year, starText);
    }
}