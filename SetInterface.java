interface SetInterface {
    /*
     *
     *  Elements: sets of the type Set
     *  Structure: none
     *  Domain: all sets contain elements of type Identifier.
     *
     *  constructors
     *
     *  Set();
     *  PRE  -
     *  POST - A new Set-object has been made and contains the empty set.
     *
     *  Set(Set src);
     *  PRE  -
     *  POST - A new Set-object has been made and contains a copy of src.
     *
     */
    
    void init();
    /*  PRE  -
     	POST - The set is empty.
     */
    
    Set diff(Set rhs);
    /*  PRE  -
     	POST - The set containing elements that are in the left-hand side and not in the right-hand side set has been returned.
     */
    
    Set intersection(Set rhs);
    /*  PRE  -
     	POST - The set containing elements that are in both the left-hand side and right-hand side set has been returned.
     */
    
    Set union(Set rhs);
    /*  PRE  -
     	POST - The set containing elements that are in the left-hand side and right-hand side set has been returned.
     */
    
    Set symDiff(Set rhs);
    /* PRE  -
     	POST - The set containing elements that are in union of the left-hand side and right-hand side set and not intersection of the two has been returned.
     */
    
    void add(Identifier x);
    /*  PRE - The identifier may not not already be in the set.
     	POST - A new identifier has been added to the set if it did not already exist in the set.
     */
    
    boolean isEmpty();
    /*  PRE -
     	POST -  true: The set is empty.
     			false: The set is not empty.
     */
    
    int size();
    /*  PRE -
     	POST - The number of identifiers in the Set has been returned.
     */
    
    void printSet();
    /* PRE -
       POST - The content of the set will be printed to the standard output.
     */
}