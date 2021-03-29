package task2.phoneNumbers.v1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsePhoneNumber {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String line;
        char[] chars;
        Pattern pattern = Pattern.compile("^(\\+7|8)(\\d{3}|\\(\\d{3}\\))(\\d-?){6}\\d");
        do {
            line = scanner.nextLine().replaceAll("\\s", "");
            if (line.equals("q"))
                break;
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                chars = line.replaceAll("\\D", "").toCharArray();
                if (chars[0]=='7')chars[0] = '8';
                Character[] wrapper = new Character[chars.length];
                for (int i = 0; i < chars.length; i++)
                    wrapper[i] = chars[i];

                System.out.println(String.format("%c (%c%c%c) %c%c%c-%c%c-%c%c", wrapper));
            } else
                System.out.println("error");

            break;
        } while (true);
    }
}
