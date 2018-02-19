package model;

import model.htmlTests.*;

import java.util.ArrayList;

/**
 * Created by Nathan on 22/11/2017.
 * Manages tests. Add new tests here
 */
public class TestManager {

    public static ArrayList<Testable> getTests(){
        ArrayList<Testable> availableTests = new ArrayList<>();

        availableTests.add(new HtmlStructureTest());    //TODO test/fix
        //availableTests.add(new LinkedPagesTest());    Not required, error prone
        availableTests.add(new PageCountTest(6));
        //availableTests.add(new IndexTest());  //TODO test/fix

        for(TagType tagType: TagType.values()){
            availableTests.add(new TagSearchTest(tagType.name(), tagType));
        }

        return availableTests;
    }

}
