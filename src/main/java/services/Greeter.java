package services;

public class Greeter {

    enum Language{
        ENGLISH("Hello"),
        AFRIKAANS("Hallo"),
        JAPANESE("Konichiwa");

        private String greeting;

        Language(String greeting){
            this.greeting = greeting;
        }

        public String getGreeting(){
            return greeting;
        }
    }

    public String greet(Extractor extract){
       if(extract != null){
           GreetDatabase db = new GreetDatabase();

           if(!db.exists(extract.getName())){
               db.addUser(extract.getName());
           }

           db.incrementGreets(extract.getName());

           return String.format("%s, %s", Language.valueOf(extract.getLanguage()).getGreeting(), extract.getName());
       }
       return "";
    }



}
