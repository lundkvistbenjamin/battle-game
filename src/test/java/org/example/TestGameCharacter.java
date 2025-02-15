package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameCharacter {

    // Tests for GameCharacter
    @Test
    void testGetName() {
        GameCharacter g = new Player("foo", 100);
        assertEquals("foo", g.getName());
    }

    @Test
    void testTakeDamage() {
        GameCharacter g = new Player("foo", 100);
        g.takeDamage(30);
        assertEquals(70, g.getHitPoints());
    }

    // Tests for Player and Npc
    @Test
    void testPlayerAttack() {
        GameCharacter player = new Player("player", 50);
        GameCharacter npc = new Npc("npc", 50);
        player.attack(npc);
        assertNotEquals(50, npc.getHitPoints());
        assertEquals(50, player.getHitPoints());
    }

    @Test
    void testNpcAttack() {
        GameCharacter npc = new Npc("npc", 50);
        GameCharacter player = new Player("player", 50);
        npc.attack(player);
        assertNotEquals(50, player.getHitPoints());
        assertEquals(50, npc.getHitPoints());
    }

    @Test
    void testSpawnNpc() {
        GameCharacter npc = Npc.spawnNpc();
        assertNotNull(npc.getName());
    }

    // Test save functionality
    @Test
    void testSaveGameFunctionality() {
        String saveFile = "test.save";

        Player player = new Player("Hugo", 100, new Weapon("Excalibur", 50));

        FileUtils.saveObject(player, saveFile);
        Player savedPlayer = (Player) FileUtils.loadObject(saveFile);

        assertEquals(player.getName(), savedPlayer.getName());
        assertEquals(player.getEquippedWeapon().getName(), savedPlayer.getEquippedWeapon().getName());
    }

}
