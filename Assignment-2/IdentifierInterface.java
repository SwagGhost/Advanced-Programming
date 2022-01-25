/* elements  : objects of type IdentifierInterface
 * structure : linear
 * domain    : an identifier only consist of letters or digits and the first character has to be a letter.
 * 
 * There is a default constructor that returns the identifier.
 *  IdentifierInterface();
 */

interface IdentifierInterface {
    void addCharacter(char c);
   /*  PRE -
       POST - A character has been added to the identifier.
    */
    
    int hashCode();
    /* PRE -
       POST - Returns a hashcode for the Identifier.
    */
    
    boolean equals(Object obj);
    /* PRE -
       POST - true: this equals obj.
              false: this does not equal obj.
    */
}