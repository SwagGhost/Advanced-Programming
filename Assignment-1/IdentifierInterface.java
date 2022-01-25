interface IdentifierInterface {
    /*
     *
     *  Elements: identifiers of the type Identifier
     *  Structure: none
     *  Domain: all identifiers start with a letter and contain only letters or digits
     *
     *  constructors
     *
     *  Identifier(char c);
     *  PRE  -
     *  POST - A new Identifier-object has been made and contains an single character.
     *
     *  Identifier(Identifier src);
     *  PRE  -
     *  POST - A new Identifier-object has been made and contains a copy of src.
     *
     */
    
    public void addToIdentifier(char c);
    /*  PRE  -
 		POST - A character is added to the identifier.
     */
    
    public boolean equals(Object obj);
    /*  PRE  -
 		POST - true: this equals obj.
 			   false: this does not equal obj. 
     */
    
    public void printIdentifier();
    /*  PRE  -
		POST - The identifier is printed to the standard output.
     */
}