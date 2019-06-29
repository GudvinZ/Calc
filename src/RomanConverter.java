import java.util.HashMap;
import java.util.Map;

class RomanConverter {

    private static final int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final Map<Integer, String> romanNums = new HashMap<>();

    static {
        romanNums.put(1000, "M");
        romanNums.put(900, "CM");
        romanNums.put(500, "D");
        romanNums.put(400, "CD");
        romanNums.put(100, "C");
        romanNums.put(90, "XC");
        romanNums.put(50, "L");
        romanNums.put(40, "XL");
        romanNums.put(10, "X");
        romanNums.put(9, "IX");
        romanNums.put(5, "V");
        romanNums.put(4, "IV");
        romanNums.put(1, "I");
    }

    static int toConvert(String s) {
        int i = 0;
        for (char c :
                s.toCharArray()) {
            for (int j = 0; j < nums.length; j += 2) {
                if (c == romanNums.get(nums[j]).charAt(0)) {
                    i += nums[j];
                }
            }
        }
        for (int j = 1; j < nums.length - 4; j += 2) {
            if (s.contains(romanNums.get(nums[j]))) {
                i -= nums[j - 1] - nums[j];
            }
        }
        if (s.contains("IV")) {
            i -= 2;
        }
        if (s.contains("IX")) {
            i -= 2;
        }
        return i;
    }

    static String toConvert(int i) {
        StringBuilder s = new StringBuilder();
        for (int n :
                nums) {
            while (i >= n) {
                s.append(romanNums.get(n));
                i -= n;
            }
        }
        return s.toString();
    }

}
