# README #

This project is part of the Algorithms Module of BSc(Hons) in Applied Computing Year 2 at Waterford Institute of Technology, Ireland.

### Summary of Project ###

 Two approaches are taken to implement the Autocomplete interface provided as part of the assessment. BruteAutocomplete takes a straightforward approach in sorting through a list of words and returning the results. QuickAutocomplete uses Binary Search to speed up the process of sifting through large data sets. 

### To execute project ###

There is a client each for BruteAutocomplete and QuickAutocomplete. Running either one as a Java Application will prompt a Java Swing interface where the user can select one of three available search features as follows;

* Weight of a term
* Best matching term
* List of best matches 

Where;

* Weight of term returns the weight of a specific term if found in the data set. Returns 0.0 otherwise. 
* Best matching terms returns the best matching term against the prefix provided by the user, returns null otherwise
* List of best matches returns a list of k best matching terms against the prefix and k provided by the user. If k exceeds the total matching terms, thse terms are returned. Otherwise, k specific terms are returned. 

### Test Strategy ###

A test data set was used to test the functionality of both BruteAutocomplete and QuickAutocomplete as it is more manageable. JUnit4 test cases were used to test various functionalities as follows;

* Term Class
    * Create a term object and store values of weight and theTerm
* TermList Class
    * Test terms are read in from file
    * Test terms are sorted by weight
    * Test delimiters 
    * Test no duplicate words
* Brute and Quick Classes
    * Test the weight of present term is returned and 0.0 otherwise
    * Test the best matching term is returned, null otherwise
    * Test list of matching terms for prefix and k number of terms
    * Test for exceptions 

### Libraries ###

* Guava (Preconditions, Hashcode, ToString, Equals)
* stdlib (Stopwatch)

### Known bugs ###

* QuickClient - bestMatch method - Does not return bestmatch for input "m", possibly other input
* BruteClient - bestMatch method - Does not return results for numeric inputs greater than 1 digit. For example, 999, but works for 9