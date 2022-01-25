import java.util.Scanner;
import java.util.HashMap;
import java.math.BigInteger;

public class Main {
    public HashMap<Identifier, Set<BigInteger>> map = new HashMap<Identifier, Set<BigInteger>>();
    
    Scanner in = new Scanner(System.in);
    
    HashMap<Identifier, Set<BigInteger>> put_entry(Identifier key, Set<BigInteger> value) {
        map.put(key, value);
        return map;
    }
    
    void start() {
        new Parse(this).program(in);
    }
    
    public static void main(String[] args) {
        new Main().start();
    }
}