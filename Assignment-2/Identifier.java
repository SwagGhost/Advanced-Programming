import java.lang.StringBuffer;

public class Identifier implements IdentifierInterface {
    StringBuffer variable;
    
    public Identifier(char c) {
        variable = new StringBuffer("");
        variable.append(c);
    }
    
    public void addCharacter(char c) {
    	variable.append(c);
    }
    
    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + variable.toString().hashCode();
        return hash;
    }
    
    @Override
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
        return variable.toString().equals(x.variable.toString());
    }
}
