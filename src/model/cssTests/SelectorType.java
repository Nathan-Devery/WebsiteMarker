package model.cssTests;

/**
 * Selectors
 */
public enum SelectorType {
    ID("#", "id"),
    CLASS(".", "class");

    String prefix;
    String htmlAttribute;

    SelectorType(String prefix, String htmlAttribute) {
        this.prefix = prefix;
        this.htmlAttribute = htmlAttribute;
    }
}
