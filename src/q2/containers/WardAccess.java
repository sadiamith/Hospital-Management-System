//Sadi Mohammad Mustafa
//sam774
//11257334
//CMPT270



package q2.containers;

import q2.entities.Ward;

/**
 * singleton class to access a ward
 */
public class WardAccess {
    //private constructor as its singleton
    public WardAccess(){};
    private static Ward ward;

    /**
     * creates and initializes one ward
     * @param name name of the ward
     * @param minBedLabel index of the first bed of the ward
     * @param maxBedLabel index of the last bed of the ward
     */

    public static void Initialize(String name, int minBedLabel, int maxBedLabel){
        if(name == null || name.equals("")){
            throw new RuntimeException("The name of the ward cannot be null or empty");
        }
        if (minBedLabel < 0 || maxBedLabel < minBedLabel)
            throw new RuntimeException("The bed labels " + minBedLabel + " and " + maxBedLabel
                    + " are invalid as they cannot be negative and at least one bed.");
        if (ward != null)
            throw new RuntimeException("Initialize should only be invoked once.");

        ward = new Ward(name, minBedLabel, maxBedLabel);
    }

    public static Ward ward(){
        if(ward == null)
            throw new RuntimeException("The ward must be previously initialized" +  "before it can be accessed");
        return ward;
    }
}
