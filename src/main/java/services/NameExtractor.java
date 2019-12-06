package services;

public class NameExtractor {
    private final String input;
    private String name;

    public NameExtractor(String input) {
        this.input = input;
        extract();
    }

    private void extract(){
        this.name = input.split("=")[1];
    }

    public String getName(){
        return this.name;
    }
}
