package model.cssTests;

/**
 * css properties that may be searched with.
 *
 * Font-family, font-size and color have been concatenated into the 'text formatting' test. Avoid placing these properties here.
 */
public enum Property {
    FONTFAMILY("font-family"),
    FONTSIZE("font-size"),
    COLOR("color"),
    BACKGROUND("background");

    public String propertyString;

    Property(String propertyString) {
        this.propertyString = propertyString;
    }
}
