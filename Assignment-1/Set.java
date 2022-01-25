public class Set implements SetInterface {
    private static final int MAX_NUMBER_OF_IDENTIFIERS = 20;
    
    private Identifier[] set;
    private int amountOfElements;
    
    public Set() {
        set = new Identifier[MAX_NUMBER_OF_IDENTIFIERS];
        amountOfElements = 0;
    }
    
    private void copyElements(Identifier[] dest, Identifier[] src, int amount) {
        for (int i = 0; i < amount; i++) {
            dest[i] = new Identifier(src[i]);
        }
    }
    
    public Set(Set src) {
        set = new Identifier[MAX_NUMBER_OF_IDENTIFIERS];
        amountOfElements = src.amountOfElements;
        copyElements(set, src.set, amountOfElements);
    }
    
    public void init() {
        amountOfElements = 0;
    }
    
    public Set diff(Set rhs) {
        Set diffSet = new Set();
        for (int i = 0; i < amountOfElements; i++) {
            if (! rhs.contains(set[i])) {
                diffSet.add(set[i]);
            }
        }
        return diffSet;
    }
    
    public Set intersection(Set rhs) {
        Set intersectionSet = new Set();
        for (int i = 0; i < amountOfElements; i++) {
            if (rhs.contains(set[i])) {
                intersectionSet.add(set[i]);
            }
        }
        return intersectionSet;
    }
    
    public Set union(Set rhs) {
        Set unionSet = new Set();
        for (int i = 0; i < amountOfElements; i++) {
            unionSet.add(set[i]);
        }
        for (int i = 0; i < rhs.amountOfElements; i++) {
            unionSet.add(rhs.set[i]);
        }
        return unionSet;
    }
    
    public Set symDiff(Set rhs) {
        Set symDiffSet = new Set();
        symDiffSet = union(rhs).diff(intersection(rhs));
        return symDiffSet;
    }
    
    private boolean contains(Identifier e) {
        if (amountOfElements > 0) {
            for (int i = 0; i < amountOfElements; i++) {
                if (set[i].equals(e)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void add(Identifier x) {
    	if (! contains(x)) {
    		set[amountOfElements] = x;
            amountOfElements += 1;
    	}
    }
    
    public boolean isEmpty() {
        return amountOfElements == 0;
    }
    
    public int size() {
        return amountOfElements;
    }
    
    public void printSet() {
        for (int i = 0; i < amountOfElements; i++) {
            set[i].printIdentifier();
            if (i < amountOfElements - 1) {
                System.out.printf(" ");
            }
        }
    }
}
