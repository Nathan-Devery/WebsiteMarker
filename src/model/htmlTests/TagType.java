package model.htmlTests;

/**
 * Created by Nathan on 22/11/2017.
 */
public enum  TagType {

    LINK("a[href]"),
    IMAGE("img"),
    UNORDEREDLIST("ul"),
    ORDEREDLIST("ol"),
    YOUTUBE("iframe[src*=youtube]"),
    GOOGLEMAP("iframe[src*=www.google.com/maps/embed]"),
    FORM("form"),
    TEXTINPUT("input[type=text]"),
    SUBMITBUTTON("input[type=submit]"),
    BUTTON("button"),
    CLASS("[class]"),
    ID("[id]"),
    SPAN("span"),
    DIV("div"),
    TITLE("title");

    public String searchString;

    TagType(String searchString){
        this.searchString = searchString;
    }
}
