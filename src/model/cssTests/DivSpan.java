package model.cssTests;

public enum DivSpan {
    DIV("div"),
    SPAN("span");

    String htmlAttribute;

    DivSpan(String htmlAttribute) {
        this.htmlAttribute = htmlAttribute;
    }
}
