package org.example;

import java.io.Serializable;

public class Weapon implements Serializable {

    private String name;
    private int damage;

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
