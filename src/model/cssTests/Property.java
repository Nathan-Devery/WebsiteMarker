package model.cssTests;

/**
 * css properties that may be searched with.
 */
public enum Property {
    FONTFAMILY("font-family"),
    FONTSIZE("font-size"),
    COLOR("color");

    public String propertyString;

    Property(String propertyString) {
        this.propertyString = propertyString;
    }
}
