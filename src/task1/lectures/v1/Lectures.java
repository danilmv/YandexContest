package task1.lectures.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lectures {
    private static final int NO_DEMANDS = -1;

    private static final Scanner scanner = new Scanner(System.in);
    private static Lecture[] lectures;
    private static int n;

    public static void main(String[] args) {
        n = scanner.nextInt();
        lectures = new Lecture[n];
        int p = scanner.nextInt();
        boolean result = true;

        for (int i = 0; i < p; i++) {
            short l1 = scanner.nextShort();
            short l2 = scanner.nextShort();

            if (lectures[l1] == null) {
                lectures[l1] = new Lecture(l1);
            }
            lectures[l1].setDemands(l2);
            if (lectures[l2] == null) {
                lectures[l2] = new Lecture(l2);
            }
        }
        scanner.close();

        for (int i = 0; i < n; i++) {
            if (lectures[i] == null) continue;
            if (lectures[i].isAvailable()) continue;
            if (checkLectureIsAvailable(lectures[i], 0)) continue;

            result = false;
            break;
        }

        if (result)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    static boolean checkLectureIsAvailable(Lecture lecture, int depth) {
        boolean result = true;

        if (lecture == null) return false;
        if (lecture.isChecked()) return lecture.isAvailable();
        if (lecture.getDemands() == NO_DEMANDS) {
            lecture.setAvailable(true);
            return true;
        }
        if (depth > n) return false;
        for (Short demand : lecture.getDemandList()) {
            result = checkLectureIsAvailable(lectures[demand], depth + 1);
            if (!result) break;
        }
        lecture.setAvailable(result);
        return result;
    }

    static class Lecture implements Comparable<Lecture> {
        short index;
        boolean available;
        boolean checked = false;
        short demands = NO_DEMANDS;
        List<Short> demandList = new ArrayList<>();

        public Lecture(short index) {
            this.index = index;
        }

        public short getIndex() {
            return index;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
            checked = true;
        }

        public short getDemands() {
            return demands;
        }

        public void setDemands(short demands) {
            this.demands = demands;
            if (!demandList.contains(demands))
                demandList.add(demands);
        }

        public List<Short> getDemandList() {
            return demandList;
        }

        public boolean isChecked() {
            return checked;
        }

        @Override
        public int compareTo(Lecture o) {
            return index - o.getIndex();
        }
    }
}
