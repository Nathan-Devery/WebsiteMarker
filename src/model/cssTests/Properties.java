package model.cssTests;

public enum Properties {
    FONTFAMILY("font-family"),
    FONTSIZE("font-size"),
    COLOR("color");

    public String searchString;

    Properties(String searchString) {
        this.searchString = searchString;
    }
}
