package services;

public class Extractor {
    private final String input;
    private String name;
    private String langauge;

    public Extractor(String input) {
        this.input = input;

        extract();
    }

    private void extract(){
        this.name = input.split("&")[0].split("=")[1];
        this.langauge = input.split("&")[1].split("=")[1];
    }

    public String getName(){
        return this.name;
    }
    public String getLanguage(){
        return this.langauge;
    }
}
