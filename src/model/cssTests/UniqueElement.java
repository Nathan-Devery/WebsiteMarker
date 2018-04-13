package model.cssTests;

/**
 * Holds search string for specific elements.
 */
public enum UniqueElement {
    DIV("div"),
    SPAN("span");

    String htmlAttribute;

    UniqueElement(String htmlAttribute) {
        this.htmlAttribute = htmlAttribute;
    }
}
