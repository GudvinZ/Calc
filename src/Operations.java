import java.util.ArrayList;

class Operations {
    private static char[] operations = {'+', '-', '*', '/'};

    public static char[] get() {
        return operations;
    }

    private int operation(int a, char operation, int b) throws SomeException {
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        throw new SomeException("something unexpected happened");
    }

    int operations(ArrayList<Integer> elements) throws SomeException {
        int sum=0;
        for (int i = 0; i < elements.size()-1; i+=2) {
            elements.set(i+2,operation(elements.get(i), (char)elements.get(i+1).intValue(), elements.get(i+2)));
        }
        return elements.get(elements.size()-1);
    }
}
