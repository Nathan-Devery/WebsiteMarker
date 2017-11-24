package model;

import java.util.ArrayList;

/**
 * Created by Nathan on 22/11/2017.
 * Manages tests. Add new tests here
 */
public class TestManager {

    public static ArrayList<Testable> initializeTests(){
        ArrayList<Testable> availableTests = new ArrayList<>();

        for(TagType tagType: TagType.values()){
            availableTests.add(new TagSearch(tagType.name(), tagType));
        }

        return availableTests;
    }



}
