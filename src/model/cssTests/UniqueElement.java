package model.cssTests;

public enum UniqueElement {
    DIV("div"),
    SPAN("span");

    String htmlAttribute;

    UniqueElement(String htmlAttribute) {
        this.htmlAttribute = htmlAttribute;
    }
}
