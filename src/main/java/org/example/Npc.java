package org.example;

import java.util.Random;

public class Npc extends GameCharacter {

    // Npc names
    private static final String[] npcNames = {"Lich King", "Ghostly Specter", "Giant Spider", "Orc Berserker", "Skeleton Warrior", "Goblin Shaman"};

    // Weapon names
    private static final String[] weaponNames = {"Spineripper", "Doombringer", "Heartstriker", "Bloodweep", "Worldslayer", "Needle"};

    // Constructors
    public Npc(String name, int hitPoints) {
        super(name, hitPoints, 0.5, new Weapon("Letter Opener", Utils.getRandomRange(20, 26)));
    }

    public Npc(String name, int hitPoints, Weapon equippedWeapon) {
        super(name, hitPoints, 0.5, equippedWeapon);
    }

    // Static factory method
    static GameCharacter spawnNpc() {
        String randomName = npcNames[Utils.getRandomRange(0, 5)];
        Weapon randomWeapon = new Weapon(weaponNames[Utils.getRandomRange(0, 5)], Utils.getRandomRange(20, 26));
        int randomHitPoints = Utils.getRandomRange(80, 100);

        return new Npc(randomName, randomHitPoints, randomWeapon);
    }

}