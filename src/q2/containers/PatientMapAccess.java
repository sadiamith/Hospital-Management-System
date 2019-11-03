//Sadi Mohammad Mustafa
//sam774
//11257334
//CMPT270

package q2.containers;

import q2.entities.Patient;

import java.util.TreeMap;

/**
 * singleton class to access the patients dictionary
 */
public class PatientMapAccess {
    //private constructor for the singleton
    private PatientMapAccess(){}

    private static TreeMap<Integer, Patient> dictionary;

    /**
     * returns the dictionary that maps health num to patient
     * @return the dictionary that maps health num to patient
     */
    public static TreeMap<Integer, Patient> dictionary(){
        if(dictionary == null){
            dictionary = new TreeMap<Integer, Patient>();
        }
        return dictionary;
    }
}
