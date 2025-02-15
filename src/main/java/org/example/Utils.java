package org.example;

import java.util.Random;
import java.util.Scanner;

public class Utils {

    static int getRandomRange(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt(min, max);
    }

}
