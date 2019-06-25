import java.util.Arrays;
import java.util.Scanner;

class RomanCalculator extends RomanConverter {

    RomanCalculator() {
        this.nums = new int[2];
        String[] araPatterns = {"[1-9]{1}", "10"};
        String[] romanPatterns = {"[IV]{1}[I]?[I]?", "[I]?[VX]{1}", "VIII"};
        Patterns.setPatternsArabic(araPatterns);
        Patterns.setPatternsRoman(romanPatterns);
    }

    private static char[] operations = {'+', '-', '*', '/'};
    private char operation;
    private int[] nums;
    private boolean isRoman = false;

    private void isRoman() {
        isRoman = true;
    }

    void run() {
        try {
            input();
            int result = operation();
            if (isRoman) {
                System.out.println(toConvert(result));
            } else {
                System.out.println(result);
            }
        }
        catch (IncorrectInputException | SomeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void input() throws IncorrectInputException {
        //input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        s = s.replaceAll(" ", "");
        //check
        int n;
        for (char a :
                operations) {
            n = s.indexOf(a);
            if (n != -1) {
                operation = s.substring(n, n + 1).charAt(0);
                check(s.substring(0, n), s.substring(n + 1));
                return;
            }
        }
        throw new IncorrectInputException("Incorrect input");
    }

    private void check(String a, String b) throws IncorrectInputException {
        if (checkArabic(a)) {
            if (checkArabic(b)) {
                nums[0] = Integer.parseInt(a);
                nums[1] = Integer.parseInt(b);
            } else {
                throw new IncorrectInputException("both numbers must be either roman or arab");
            }
        } else if (checkRoman(a) && checkRoman(b)) {
            isRoman();
            nums[0] = toConvert(a);
            nums[1] = toConvert(b);
        } else {
            throw new IncorrectInputException("Incorrect input");
        }
    }

    private boolean checkArabic(String s) {
        for (String pattern :
                Patterns.getPatternsArabic()) {
            if (s.matches(pattern)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRoman(String s) {
        for (String pattern :
                Patterns.getPatternsRoman()) {
            if (s.matches(pattern)) {
                return true;
            }
        }
        return false;
    }

    private int operation() throws SomeException {
        switch (operation) {
            case '+':
                return nums[0] + nums[1];
            case '-':
                return nums[0] - nums[1];
            case '*':
                return nums[0] * nums[1];
            case '/':
                return nums[0] / nums[1];
        }
        throw new SomeException("something unexpected happened");
    }
}
