package model.htmlTests;

/**
 * Created by Nathan on 22/11/2017.
 */
public enum TagType {

    PARAGRAPH("p"),
    LINK("a[href]"),
    IMAGE("img"),
    UNORDEREDLIST("ul"),
    ORDEREDLIST("ol"),
    IMAGEALT("img[alt]"),
    YOUTUBE("iframe[src*=youtube]"),
    FORM("form"),
    TEXTINPUT("input[type=text]"),
    RADIOBUTTON("input[type=radio]"),
    SUBMITBUTTON("input[type=submit"),
    CLASS("[class]"),
    ID("[id]"),
    SPAN("span"),
    DIV("div");

    public String searchString;

    TagType(String searchString){
        this.searchString = searchString;
    }
}
