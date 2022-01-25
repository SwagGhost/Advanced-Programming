import java.lang.StringBuffer;

public class Identifier implements IdentifierInterface {
    private StringBuffer identifier;
    
    public Identifier(char c) {
        identifier = new StringBuffer("");
        identifier.append(c);
    }
    
    public Identifier(Identifier src) {
        src.identifier = identifier;
    }
    
    public void addToIdentifier(char c) {
        identifier.append(c);
    }
    
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Identifier x = (Identifier) obj;
        return (identifier.toString()).equals(x.identifier.toString());
        
    }
    
    public void printIdentifier() {
        System.out.printf("%s", identifier);
    }
}