import java.util.ArrayList;
import java.util.Scanner;

class RomanCalculator extends Operations {

    RomanCalculator() {
        String[] araPatterns = {"-?[1-9]{1}[0-9]{0,3}", "0"};
        String[] romanPatterns = {"M{0,3}(CM|DC{0,3}|CD|C{1,3})?(XC|LX{0,3}|XL|X{1,3})?(IX|VI{0,3}|IV|I{1,3})?"};
        Patterns.setPatternsArabic(araPatterns);
        Patterns.setPatternsRoman(romanPatterns);
    }

    private boolean isRoman;
    private boolean firstCheck=true;

    private void isRoman() {
        isRoman = true;
    }

    void run() {
        try {
            int result = operations(input());
            if (isRoman) {
                System.out.println(RomanConverter.toConvert(result));
            } else {
                System.out.println(result);
            }
        }
        catch (ArithmeticException e) {
            System.out.println("dividing by zero");
        }
        catch (IncorrectInputException | SomeException e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<Integer> input() throws IncorrectInputException {
        //input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        s = s.replaceAll(" ", "");
        //check
        ArrayList<Integer> elements = new ArrayList<>();
        int k = 0;
        char[] c = s.toCharArray();
        for (int n = 0; n < c.length; n++) {
            for (char a :
                    Operations.get()) {
                if(c[n]==a) {
                    if (n == 0) {
                        throw new IncorrectInputException("Incorrect input");
                    } else {
                        elements.add(check(s.substring(k, n)));
                        elements.add((int) s.substring(n, n + 1).charAt(0));
                        k = n + 1;
                    }
                }
            }
        }
            if (k == 0) {
                throw new IncorrectInputException("Incorrect input");
            } else {
                elements.add(check(s.substring(k)));
                return elements;
            }
    }

    private int check(String a) throws IncorrectInputException {
        if(firstCheck) {
            if(checkRoman(a)){
                isRoman();
                firstCheck=false;
                return RomanConverter.toConvert(a);
            } else if (checkArabic(a)) {
                firstCheck=false;
                return Integer.parseInt(a);
            } else {
                throw new IncorrectInputException("Incorrect input");
            }
        }
        if(isRoman) {
            if (checkRoman(a)) {
                return RomanConverter.toConvert(a);
            } else {
                throw new IncorrectInputException("all numbers must be either roman or arab");
            }
        } else if (checkArabic(a)) {
            return Integer.parseInt(a);
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


}
