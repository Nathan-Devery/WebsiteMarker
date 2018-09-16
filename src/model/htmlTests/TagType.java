package model.htmlTests;

/**
 * Holds the html tags to be testsed for the 'TagTypeSearchTests.
 * Elements that already have dedicated tests, i.e. Title html tag and form related tags; already have dedicated searches.
 * These elements should not be added to this enum to avoid user confusion.
 *
 * Created by Nathan on 22/11/2017.
 */
public enum  TagType {
    LINK("a[href]"),
    IMAGE("img"),
    UNORDEREDLIST("ul"),
    ORDEREDLIST("ol"),
    YOUTUBE("iframe[src*=youtube]"),
    GOOGLEMAP("iframe[src*=www.google.com/maps/embed]"),
    LINEBREAK("br"),
    STRONG("strong"),
    PARAGRAPH("p"),
    ABBREVIATION("abbr"),
    ADDRESS("address"),
    HEADING1("h1");
    ;

    /*
    Form testing is split into its own testing class.

    FORM("form"),
    BUTTON("button"),
    TEXTINPUT("input[type=text]");
    SUBMITBUTTON("input[type=submit]");
    */


    public String searchString;

    TagType(String searchString){
        this.searchString = searchString;
    }
}
