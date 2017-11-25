package model;

import model.WebsiteTests.*;

import java.util.ArrayList;

/**
 * Created by Nathan on 22/11/2017.
 * Manages tests. Add new tests here
 */
public class TestManager {

    public static ArrayList<Testable> initializeTests(){
        ArrayList<Testable> availableTests = new ArrayList<>();

        availableTests.add(new HtmlStructureTest());
        availableTests.add(new LinkedPagesTest());
        availableTests.add(new PageCountTest(6));
        availableTests.add(new IndexTest());

        for(TagType tagType: TagType.values()){
            availableTests.add(new TagSearchTest(tagType.name(), tagType));
        }

        return availableTests;
    }

}
