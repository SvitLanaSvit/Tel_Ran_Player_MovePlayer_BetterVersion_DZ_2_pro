package model;

import enumLeague.League;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class Player implements Comparable<Player> {
    private UUID id;
    private String name;
    private String surname;
    int age;
    private League league;
    private int score;

    public Player(UUID id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;

    }

    public void addScore(int val){
        score += val;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-5d %-5d %-10s", getName(), getSurname(), getAge(), getScore(), getLeague());
    }

    @Override
    public int compareTo(Player o) {
        return this.age - o.age;
    }
}
