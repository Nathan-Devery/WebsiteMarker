package model.cssTests;

public enum GroupType {
    ID("#", "id"),
    CLASS(".", "class");

    String prefix;
    String htmlAttribute;

    GroupType(String prefix, String htmlAttribute) {
        this.prefix = prefix;
        this.htmlAttribute = htmlAttribute;
    }
}
