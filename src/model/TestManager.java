package model;

import model.cssTests.*;
import model.htmlTests.*;
import model.javascript.IfTest;
import model.javascript.JavascriptTest;
import model.javascript.OperatorTest;
import model.javascript.VariableTest;

import java.util.ArrayList;

/**
 * Holds the tests which are displayed on the option pane.
 */
public class TestManager {

    public static ArrayList<Testable> getTests(){
        ArrayList<Testable> availableTests = new ArrayList<>();

        availableTests.add(new HtmlStructureTest());
        availableTests.add(new PageCountIndexTest(4));
        availableTests.add(new NavigationBarTest());
        availableTests.add(new TitleTest(4));
        availableTests.add(new AltTextTest());
        availableTests.add(new FormsTest());
        availableTests.add(new JavascriptTest());
        availableTests.add(new CommentTest());
        availableTests.add(new TextFormattingTest());
        availableTests.add(new TableTest());
        availableTests.add(new SelectorTest(SelectorType.CLASS));
        availableTests.add(new SelectorTest(SelectorType.ID));
        availableTests.add(new UniqueElementTest(UniqueElement.SPAN));
        availableTests.add(new UniqueElementTest(UniqueElement.DIV));

        //Add html searches
        for(TagType tagType: TagType.values()){
            availableTests.add(new TagSearchTest(tagType, 1));
        }

        for(Property property: Property.values()){
            availableTests.add(new PropertyTester(property));
        }

        return availableTests;

        /*
        for(UniqueElement element: UniqueElement.values()){
            availableTests.add(new UniqueElementTest(element.name(), element));
        }

        for(SelectorType selectorType: SelectorType.values()){
            availableTests.add(new SelectorTest(selectorType.name(), selectorType));
        }
        */
    }

}
