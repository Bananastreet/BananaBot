package com.discordbot.utils;

import java.util.List;

public class Utility {

    private static final RandomGen RANDOM = new RandomGen();

    public static int getRandom(int length) {
        return RANDOM.get().nextInt(length + 1);
    }

    public static double getRandomDouble(double length) {
        return RANDOM.get().nextDouble(length);
    }

    public static double getRandomDouble() {
        return RANDOM.get().nextDouble();
    }

    public static int getRandomInt() {
        return RANDOM.get().nextInt();
    }

    public static int inclusive(int min, int max) {
        return RANDOM.inclusive(min, max);
    }

    /**
     * Picks a random element out of any array type.
     *
     * @param array the array to pick the element from.
     * @return the element chosen.
     */
    public static <T> T randomElement(T[] array) {
        return array[(int) (RANDOM.get().nextDouble() * array.length)];
    }

    /**
     * Picks a random element out of any list type.
     *
     * @param list the list to pick the element from.
     * @return the element chosen.
     */
    public static <T> T randomElement(List<T> list) {
        return list.get((int) (RANDOM.get().nextDouble() * list.size()));
    }

    public static <T> T randomTypeOfList(List<T> list) {
        return list.get(RANDOM.get().nextInt(list.size()));
    }

    public static int randomInclusive(int min, int max) {
        return Math.min(min, max) + RANDOM.get().nextInt(Math.max(min, max) - Math.min(min, max) + 1);
    }
}
