package org.example;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

abstract class GameCharacter implements Serializable {

    // Instance variables
    String name;
    int hitPoints;
    double dexterity;
    Weapon equippedWeapon;
    private ArrayList<Weapon> inventory;

    // Constructor
    public GameCharacter(String name, int hitPoints, double dexterity, Weapon equippedWeapon) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.dexterity = dexterity;
        this.equippedWeapon = equippedWeapon;
        this.inventory = new ArrayList<>();
        this.inventory.add(equippedWeapon);
    }

    // Instance methods
    public void takeDamage(int damage) {
        this.hitPoints = this.hitPoints - damage;
    }

    public int attack(GameCharacter defender) {
        Random random = new Random();
        int attackerMaxStrength = this.getEquippedWeapon().getDamage();
        // Minimumstrength calculated from equippedWeapon * dexterity
        double attackerMinimumStrength = attackerMaxStrength * this.getDexterity();
        // Random attack range
        int randomAttack = random.nextInt((int) attackerMinimumStrength, attackerMaxStrength);
        // Decreases hitpoints of defender
        defender.setHitPoints(defender.getHitPoints() - randomAttack);
        return randomAttack;
    }


    public int heal() {
        return this.hitPoints = Utils.getRandomRange(65, 100);
    }

    public void addWeaponToInventory(Weapon weapon) {
        this.inventory.add(weapon);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public double getDexterity() {
        return dexterity;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public ArrayList<Weapon> getInventory() {
        return inventory;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }
}
