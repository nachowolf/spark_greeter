package services;

public class Greeter {

    enum Language{
        ENGLISH("Hello");

        private String greeting;

        private Language(String greeting){
            this.greeting = greeting;
        }

        public String getGreeting(){
            return greeting;
        }
    }

    public String greet(String name){
        return String.format("%s, %s", Language.ENGLISH.getGreeting(), name);
    }


}
