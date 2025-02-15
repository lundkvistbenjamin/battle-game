package org.example;

import java.io.Serializable;

public class Player extends GameCharacter implements Serializable {


    // Constructors
    public Player(String name, int hitPoints) {
        super(name, hitPoints, 0.8, new Weapon("Sword", 20));
    }

    public Player(String name, int hitPoints, Weapon equippedWeapon) {
        super(name, hitPoints, 0.8, equippedWeapon);
    }
}
