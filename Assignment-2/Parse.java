import java.util.Scanner;
import java.util.regex.Pattern;
import java.math.BigInteger;

public class Parse {
    Main m;
    
    public Parse() {}
    
    public Parse(Main m) {
        this.m = m; 
    }
    
    char nextChar(Scanner in) {
        return in.next().charAt(0);
    }
    
    boolean nextCharIs(Scanner in, char c) {
        space(in);
        return in.hasNext(Pattern.quote(c+""));
    }
    
    void space(Scanner input) { // This function reads spaces, so that they can be ignored.
        while (input.hasNext(" ")) {
            nextChar(input);
        }
    }
    
    BigInteger natural_number(Scanner input) throws APException { // Creates a BigInteger object and returns it.
        if (input.hasNextBigInteger()) {
            String number = "";
            while (input.hasNext("[0-9]")) {
                number += nextChar(input);
                if (number.length() > 1 && number.charAt(0) == '0') { // Number has to be 0 or else, not start with '0'.
                    throw new APException("Error: ',' or '}' expected.");
                }
            }
            BigInteger result = new BigInteger(number);
            return result;
        }
        throw new APException("Error: Positive BigInteger expected.");
    }
    
    Set<BigInteger> row_natural_numbers(Scanner input) throws APException { // Create a set of BigIntegers.
        Set<BigInteger> result = new Set<BigInteger>();
        space(input);
        if (! input.hasNext("}")) { // Check if there is at least one BigInteger.
            result.add(natural_number(input));
        }
        space(input);
        while (! input.hasNext("}")) { // Check if there are more BigIntegers.
            character(input, ',', "',' expected error");
            result.add(natural_number(input));
            space(input);
        }
        return result;
    }
    
    Set<BigInteger> set(Scanner input) throws APException {
        Set<BigInteger> result = row_natural_numbers(input);
        character(input, '}', "'}' expected error"); // Check for '}'.
        return result;
    }
    
    Set<BigInteger> complex_factor(Scanner input) throws APException {
        Set<BigInteger> result = expression(input);
        character(input, ')', "')' expected error"); // Check for ')'.
        return result;
    }
    
    Set<BigInteger> multiplicative_operator(Scanner input, Set<BigInteger> s1, Set<BigInteger> s2) throws APException {
        return s1.intersection(s2);
    }
    
    Set<BigInteger> factor(Scanner input) throws APException {
        if (nextCharIs(input, '(')) {
            nextChar(input);
            space(input);
            return complex_factor(input);
        }
        else if (nextCharIs(input, '{')) {
            nextChar(input);
            space(input);
            return set(input);
        }
        else if (input.hasNext("[A-Za-z]")) {
            Identifier key = identifier(input);
            if (! this.m.map.containsKey(key)) {
                throw new APException("Error: unknown variable.");
            }
            return this.m.map.get(key);
        }
        else {
            throw new APException("Error: Factor expected.");
        }
    }
    
    // Calls different function depending on operator.
    Set<BigInteger> additive_operator(char operator, Set<BigInteger> s1, Set<BigInteger> s2) throws APException {
        if (operator == '+') {
            return s1.union(s2);
        }
        else if (operator == '|') {
            return s1.symDiff(s2);
        }
        else {
            return s1.complement(s2);
        }
    }
    
    Set<BigInteger> term(Scanner input) throws APException {
        Set<BigInteger> result = factor(input);
        while (nextCharIs(input, '*')) {
            nextChar(input);
            Set<BigInteger> factor = factor(input);
            result = multiplicative_operator(input, result, factor);
        }
        return result;
    }
    
    void eoln (Scanner input) throws APException {
        space(input);
        if (input.hasNext()) {
            throw new APException("Error: <eoln> expected.");
        }
    }
    
    void line_of_text(Scanner input) {
        while (input.hasNext()) {
            nextChar(input);
        }
    }
    
    Set<BigInteger> expression(Scanner input) throws APException {
        Set<BigInteger> result = term(input);
        while (nextCharIs(input, '+') || nextCharIs(input, '|') || nextCharIs(input, '-')) {
            result = additive_operator(nextChar(input), result, term(input));
        }
        return result;
    }
    
    void character (Scanner input, char c, String errorMessage) throws APException {
        space(input);
        if (! nextCharIs(input, c)) {
            throw new APException(errorMessage);
        }
        nextChar(input);
        space(input);
    }
    
    Identifier identifier(Scanner input) throws APException { // Create an identifier and return it.
        Identifier result = new Identifier(nextChar(input));
        while (input.hasNext("[A-Za-z]") || input.hasNext("[0-9]")) {
            result.addCharacter(nextChar(input));
        }
        return result;
    }
    
    void print_statement(Scanner input) throws APException {
        character(input, '?', "'?' expected error");
        Set<BigInteger> result = expression(input);
        eoln(input);
        result.printSet(); // If input was correct, then print the result.
        System.out.println();
    }
    
    void assignment(Scanner input) throws APException { // Get key and value to put in the HashMap.
        Identifier key = identifier(input);
        character(input, '=', "'=' expected error"); // Check for '='.
        Set<BigInteger> result = expression(input);
        eoln(input);
        this.m.map = m.put_entry(key, result); // If input was correct, then add to HashMap.
    }
    
    void eof(Scanner input) throws APException {
        space(input);
        if (input.hasNext()) {
            throw new APException("Error: <eof> expected.");
        }
    }
    
    // Checks which kind of statement is following 
    void statement(Scanner input) throws APException {
        if (nextCharIs(input, '?')) {
            print_statement(input);
        }
        else if (nextCharIs(input, '/')) {
            character(input, '/', "'/' expected error");
        }
        else if (input.hasNext("[A-Za-z]")) {
            assignment(input);
        }
        else {
            throw new APException("Error: Statement expected.");
        }
    }
    
    // Keeps taking input and parses it to the function statement
    public void program(Scanner input) {
        do {
            String statement = input.nextLine();
            Scanner statementScanner = new Scanner(statement);
            statementScanner.useDelimiter("");
            try {
                statement(statementScanner);
            }
            catch (APException e) {
                System.out.println(e);
            }
        } while(input.hasNextLine());
        try {
             eof(input);
        }
        catch (APException e) {
            System.out.println(e);
        }
    }
}