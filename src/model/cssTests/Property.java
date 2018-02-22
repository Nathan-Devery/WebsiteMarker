package model.cssTests;

public enum Property {
    FONTFAMILY("font-family"),
    FONTSIZE("font-size"),
    COLOR("color");

    public String propertyString;

    Property(String propertyString) {
        this.propertyString = propertyString;
    }
}
