package services;

public class Greeter {

    enum Language{
        ENGLISH("Hello"),
        AFRIKAANS("Hallo"),
        JAPANESE("Konichiwa");

        private String greeting;

        private Language(String greeting){
            this.greeting = greeting;
        }

        public String getGreeting(){
            return greeting;
        }
    }

    public String greet(Extractor extract){
        return String.format("%s, %s", Language.valueOf(extract.getLanguage()).getGreeting(), extract.getName());
    }



}
