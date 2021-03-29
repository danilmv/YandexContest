package task3.houses.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reconstruction {
    private static final int MAX_LEVEL = 5;

    private static Scanner scanner = new Scanner(System.in);
    private static int rightLevel = 0;
    private static int currentLevel = 0;
    private static int topBuilding = 0;

    public static void main(String[] args) {
        int leftLevel = 0;
        List<House> houses = new ArrayList<>();

        String[] data = scanner.nextLine().split("\\s");
        scanner.close();

        for (int i = 0; i < data.length; i++) {
            currentLevel = Integer.parseInt(data[i]);
            if (topBuilding< currentLevel)topBuilding = currentLevel;
            if (currentLevel > MAX_LEVEL) currentLevel = MAX_LEVEL;
            houses.add(new House(currentLevel, leftLevel));
            if (leftLevel < currentLevel && i < data.length - 1) leftLevel = currentLevel;
        }
        for (int i = houses.size() - 1; i >= 0; i--) {
            House house = houses.get(i);
            house.setRightLevel(rightLevel);
            currentLevel = house.getLevel();
            if (rightLevel < currentLevel && i > 0) rightLevel = currentLevel;
        }

        House first = houses.get(0);
        if (first!=null){
            if (first.getLevel() == topBuilding){
                first.setLevel(MAX_LEVEL);
            }
        }

        House last = houses.get(houses.size() - 1);
        if (last!=null){
            if (last.getLevel() == topBuilding){
                last.setLevel(MAX_LEVEL);
            }
        }

        for (int i = 1; i < houses.size() - 1; i++) {
            House house = houses.get(i);
            house.reconstruct();
        }

        for (House house : houses) {
            System.out.print(house.getLevel() + " ");
        }
    }

    static class House {
        int level;
        int leftLevel = MAX_LEVEL;
        int rightLevel = MAX_LEVEL;

        public House(int level, int leftLevel) {
            this.level = level;
            this.leftLevel = leftLevel;
            if (this.leftLevel <= 0) this.leftLevel = MAX_LEVEL;
        }

        public void setRightLevel(int rightLevel) {
            this.rightLevel = rightLevel;
            if (this.rightLevel <= 0) this.rightLevel = MAX_LEVEL;
        }

        public void reconstruct() {

            int newLevel = Math.min(rightLevel, leftLevel);
            if (newLevel > level) {
                level = Math.min(newLevel, MAX_LEVEL);
            }
            if (level >= topBuilding)
                level = MAX_LEVEL;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void setLeftLevel(int leftLevel) {
            this.leftLevel = leftLevel;
        }
    }
}
