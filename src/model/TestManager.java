package model;

import model.cssTests.*;
import model.htmlTests.*;
import model.javascript.IfTest;
import model.javascript.OperatorTest;
import model.javascript.VariableTest;

import javax.swing.text.html.HTML;
import java.util.ArrayList;

/**
 * Created by Nathan on 22/11/2017.
 * Manages tests. Add new tests here
 */
public class TestManager {

    public static ArrayList<Testable> getTests(){
        ArrayList<Testable> availableTests = new ArrayList<>();

        //HTML tests
        availableTests.add(new HtmlStructureTest());
        availableTests.add(new PageCountTest(4));
        availableTests.add(new TagSearchTest(TagType.ORDEREDLIST, 1));
        availableTests.add(new TagSearchTest(TagType.UNORDEREDLIST, 1));
        availableTests.add(new TagSearchTest(TagType.IMAGE, 2));
        availableTests.add(new TagSearchTest(TagType.YOUTUBE, 1));
        availableTests.add(new TagSearchTest(TagType.TITLE, 4));    //One for each page

        //CSS tests
        availableTests.add(new PropertyTester(Property.FONTFAMILY));
        availableTests.add(new PropertyTester(Property.FONTSIZE));
        availableTests.add(new PropertyTester(Property.COLOR));
        availableTests.add(new SelectorTest(SelectorType.CLASS));
        availableTests.add(new SelectorTest(SelectorType.ID));
        availableTests.add(new UniqueElementTest(UniqueElement.DIV));
        availableTests.add(new UniqueElementTest(UniqueElement.SPAN));

        //JAVASCRIPT tests
        availableTests.add(new IfTest());
        availableTests.add(new VariableTest());
        availableTests.add(new OperatorTest());

        /*
        HtmlStructureTest test = new HtmlStructureTest();
        availableTests.add(test);    //TODO test/fix
        //availableTests.add(new LinkedPagesTest());    Not required, error prone
        availableTests.add(new PageCountTest(6));
        //availableTests.add(new IndexTest());  //TODO test/fix

        for(TagType tagType: TagType.values()){
            availableTests.add(new TagSearchTest(tagType.name(), tagType));
        }

        //Css
        availableTests.add(new ElementSelectorTest("ELEMENTSELECTOR"));

        for(Property property: Property.values()){
            availableTests.add(new PropertyTester(property.name(), property));
        }

        for(UniqueElement element: UniqueElement.values()){
            availableTests.add(new UniqueElementTest(element.name(), element));
        }

        for(SelectorType selectorType: SelectorType.values()){
            availableTests.add(new SelectorTest(selectorType.name(), selectorType));
        }

        */
        return availableTests;
    }

}
