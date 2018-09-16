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
    BACKGROUNDCOLOR("background-color"),
    BACKGROUNDIMAGE("background-image"),
    BORDER("border"),   //TODO: test this
    BORDERCOLOR("border-color"),    //TODO: test this
    HEIGHTCSS("height"),    //TODO: test this
    WIDTHCSS("width"),  //TODO: test this
    MARGIN("^margin"),  //TODO: test this
    PADDING("^padding"),    //TODO: test this
    FLOAT("^float");    //TODO: test this
    ;

    public String propertyString;

    Property(String propertyString) {
        this.propertyString = propertyString;
    }
}
