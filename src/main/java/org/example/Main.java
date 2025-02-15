package org.example;

import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner strInput = new Scanner(System.in);

        String saveFile = "player.save";

        // Check if there is a save or starts new game
        Player player;
        try {
            player = (Player) FileUtils.loadObject(saveFile);
            System.out.printf("Welcome back, %s! New game (n) or continue (Enter)? ", player.getName());
            if (strInput.nextLine().equals("n")) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            player = new Player("Human", 100);
            // Enter and set name
            System.out.print("\nWelcome stranger! Enter your name: ");
            String chosenName = strInput.nextLine();
            player.setName(chosenName);
        }

        // Instantiate enemy
        GameCharacter enemy = Npc.spawnNpc();


        // Game loop
        while (true) {

            // Starts with player as attacker and enemy as defender
            GameCharacter attacker = player;
            GameCharacter defender = enemy;

            System.out.printf("\nA scary-looking %s runs towards you. \n", enemy.getName());

            // Fight loops as long as someone is alive or player decides to quit
            while (attacker.getHitPoints() > 0 && defender.getHitPoints() > 0) {

                // Checks if it's the player's turn
                if (attacker == player) {

                    System.out.print("\n--- Inventory ---\n");
                    for (int i = 0; i < player.getInventory().size(); i++) {
                        Weapon w = player.getInventory().get(i);
                        System.out.printf("%d - %s (%s)\n",
                                i + 1,
                                w.getName(),
                                w.getDamage());
                    }

                    System.out.printf("Choose weapon for attack (1-%d) or flee (q)?\n", player.getInventory().size());

                    while (true) {
                        try {
                            String input = strInput.nextLine();
                            if (input.equals("q")) {
                                System.out.println("\nYou decided to flee.");
                                FileUtils.saveObject(player, saveFile);
                                System.out.println("Game saved to file.");
                                System.exit(0);
                            }
                            int chosenWeaponIndex = Integer.parseInt(input) - 1;
                            Weapon chosenWeapon = player.getInventory().get(chosenWeaponIndex);
                            player.setEquippedWeapon(chosenWeapon);
                            break;
                        } catch (Exception e) {
                            System.out.println("Choose a number from the list or quit (q)!");
                        }
                    }
                }


                // Attacks and prints attack
                int damage = attacker.attack(defender);
                System.out.printf("%s attacks %s with a %s for %d HP.\n",
                        attacker.getName(),
                        defender.getName(),
                        attacker.getEquippedWeapon().getName(),
                        damage);

                // Checks if defender is dead, else prints how much hp is left
                if (defender.getHitPoints() <= 0) {
                    System.out.printf("-- %s is dead. %s wins with %d hp left!\n\n",
                            defender.getName(),
                            attacker.getName(),
                            attacker.getHitPoints());

                    // If enemy is dead, asks if player wants to pick up their weapon
                    if (defender == enemy) {
                        System.out.printf("Your defeated foe dropped their %s.\n", enemy.getEquippedWeapon().getName());
                        System.out.print("-- Add to inventory (a) or continue (Enter)? ");

                        if (strInput.nextLine().equals("a")) {
                            player.addWeaponToInventory(enemy.getEquippedWeapon());
                            System.out.printf("You added the %s to your inventory.\n", enemy.getEquippedWeapon().getName());
                        } else {
                            System.out.println("You decided not to pick up the weapon.");
                        }
                    }

                } else {
                    System.out.printf("-- %s has %d HP left.\n",
                            defender.getName(),
                            defender.getHitPoints());
                }

                // Swap attacker and defender
                GameCharacter temp = attacker;
                attacker = defender;
                defender = temp;

            }

            // If player is dead prints Game over and exits
            if (player.getHitPoints() <= 0) {
                System.out.print("\n-- Game over! --\n-- Thanks for playing! --\n");
                System.exit(0);
            } else {
                // Else if player is still alive and enemy is dead, asks to play again or exit
                int healedAmount = player.heal();
                System.out.printf("\nYou rest a while and patch up your wounds, you now have %d HP.\n", healedAmount);
                System.out.print("\nFight the next enemy (Enter), or quit (q)?");
                if (strInput.nextLine().equals("q")) {
                    System.out.println("\nYou decided to quit.");
                    FileUtils.saveObject(player, saveFile);
                    System.out.println("Game saved to file.");
                    System.exit(0);
                }

                // Spawns new enemy
                enemy = Npc.spawnNpc();

            }

        }

    }
}