import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.lang.StringBuffer;

public class Main {
    static final int MAX_NUMBER_OF_ELEMENTS = 10;
    
    public Main() {}
    
    PrintStream out;
    
    // This function prints one set enclosed in brackets.
    public static void output(Set s) {
        System.out.printf("{");
        if (! s.isEmpty()) {
            s.printSet();
        }
        System.out.printf("}\n");
    }
    
    char nextChar(Scanner in) {
        return in.next().charAt(0);
    }
    
    boolean nextCharIs(Scanner in, char c) {
        return in.hasNext(Pattern.quote(c+""));
    }
    
    boolean nextCharIsDigit(Scanner in) {
        return in.hasNext("[0-9]");
    }
    
    boolean nextCharIsLetter(Scanner in) {
        return in.hasNext("[a-zA-Z]");
    }
    
    // This function checks that each set is enclosed in one opening and one closing bracket.
    boolean checkBrackets(boolean open, boolean close, boolean ch, Set s) {
        if (! open) {
            System.out.println("ERROR: '{' is missing.");
        }
        else if (! close) {
            System.out.println("ERROR: '}' is missing.");
        }
        else if (open && close && ch) {
            System.out.println("ERROR: Character after '}' is not allowed.");
        }
        else {
            return true;
        }
        s.init();
        return false;
    }
    
    // This function checks that one set contains no more than the MAX_NUMBER_OF_ELEMENTS which is allowed per set.
    boolean checkSize(Set s) {
        if (s.size() == MAX_NUMBER_OF_ELEMENTS) {
            s.init();
            System.out.println("ERROR: Set cannot contain more than ten identifiers.");
            return false;
        }
        return true;
    }
    
    /* This function takes the rest of the input and creates and identifier by adding
    the next character to the identifier until a space character appears. */
    int makeIdentifierAndAddToSet(char c, String str, Set s) {
        int nrOfChars = 0;    // Count characters that are read to increment the for-loop in inputContainsCorrectSet()
        Identifier e = new Identifier(c);
        char ch;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (ch == ' ') {    // Exit the for-loop when there is a space character.
                i = str.length();
                nrOfChars += 1;
            }
            else {
                Scanner sc = new Scanner(str.substring(i, i + 1));
                if (nextCharIsLetter(sc) || nextCharIsDigit(sc)) {
                    e.addToIdentifier(ch);
                    nrOfChars += 1;
                }
            }
        }
        if (! checkSize(s)) { // Check whether the set already contains ten identifiers
        	return -1;
        }
        s.add(e);
        return nrOfChars;
    }
    
    // This function checks whether the set is correct according to the syntax of a set.
    boolean inputContainsCorrectSet(Scanner input, Set s) {
        StringBuffer str = new StringBuffer(input.nextLine());
        if (str.length() == 0) {    // no input
            return false;
        }
        boolean openSet = false,    // openSet == true if input contains '{'
                closeSet = false;    // closeSet == true if input contains '}'
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                // Do nothing, because we ignore spaces.
            }
            else {
                Scanner c = new Scanner(str.substring(i, i + 1));
                if (nextCharIs(c, '{') && ! openSet) {    // Check whether opening curly bracket appears once.
                    openSet = true;
                }
                else if (nextCharIs(c, '}') && openSet && ! closeSet) {    // Check whether the closing curly bracket appears once.
                    closeSet = true;
                }
                else if (nextCharIsLetter(c) && openSet && ! closeSet) {    // Check for no input after a closing bracket.
                    int nextChar = makeIdentifierAndAddToSet(str.charAt(i), str.substring(i + 1, str.length()), s);
                    if (nextChar < 0) {    // If the input was incorrect
                        return false;
                    }
                    i += nextChar; // Increment i by the number of characters read.
                }
                else if (nextCharIsDigit(c) && openSet && ! closeSet) {    // Check whether the identifier begins with a digit.
                    System.out.println("ERROR: Identifier cannot start with digit.");
                    s.init();
                    return false;
                }
                else {
                    return checkBrackets(openSet, closeSet, true, s);
                }
            }
        }
        return checkBrackets(openSet, closeSet, false, s);
    }
    
    boolean calculateAndGiveOutput(Set s1, Set s2) {
        System.out.printf("difference = ");
        output(s1.diff(s2));
        System.out.printf("intersection = ");
        output(s1.intersection(s2));
        System.out.printf("union = ");
        output(s1.union(s2));
        System.out.printf("sym. diff. = ");
        output(s1.symDiff(s2));
        s1.init();
        s2.init();
        return true;
    }
    
    boolean askSet (Scanner input, String question, Set set) {
        do {
            System.out.printf("%s", question);
            if (! input.hasNextLine()) {
                System.out.printf("\n"); // otherwise line with ^D will be overwritten
                return false;
            }
        } while (! inputContainsCorrectSet(input, set));
        return true;
    }
    
    boolean askBothSets (Scanner input, Set set1, Set set2) {
        return askSet(input, "Give first set : ", set1) &&
        askSet(input, "Give second set : ", set2);
    }
    
    void start () {
        Scanner in = new Scanner(System.in);
        Set set1 = new Set(),
            set2 = new Set();
        while (askBothSets(in, set1, set2)) {
            calculateAndGiveOutput(set1, set2);
        }
    }
    
    public static void main(String args[]) {
        new Main().start();
    }
}