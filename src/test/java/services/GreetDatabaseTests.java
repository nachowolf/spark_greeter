package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetDatabaseTests {

GreetDatabase greetDatabase = new GreetDatabase();

    @BeforeEach
   public void cleanTables(){
        greetDatabase.clear();
    }

    @Test
    public void addNewUser(){
        greetDatabase.addUser("Hon");
        assertEquals(greetDatabase.exists("Hon"), true);
    }

    @Test
    public void shouldReturnHowMuchTimesAUserHasBeenGreeted(){
        String name = "Nat";
        greetDatabase.addUser(name);
        greetDatabase.incrementGreets(name);
        greetDatabase.incrementGreets(name);
        greetDatabase.incrementGreets(name);
        greetDatabase.incrementGreets(name);
        assertEquals(greetDatabase.getTotalGreets(name), 4);
    }

    @Test
    public void shouldReturnTotalAmountOfPeople(){

        greetDatabase.addUser("Nat");
        greetDatabase.addUser("john");
        greetDatabase.addUser("sarah");
        greetDatabase.addUser("phil");
        greetDatabase.addUser("kevin");
        greetDatabase.addUser("bruce");
        greetDatabase.addUser("jenner");
        greetDatabase.addUser("sammy");
        greetDatabase.addUser("tristan");
        greetDatabase.addUser("caremen");

        assertEquals(greetDatabase.getTotalUsers(), 10);
    }

    @Test
    public void shouldReturnTotalGreets() {

        greetDatabase.addUser("Nat");
        greetDatabase.addUser("john");
        greetDatabase.addUser("sarah");
        greetDatabase.addUser("phil");
        greetDatabase.addUser("kevin");
        greetDatabase.addUser("bruce");
        greetDatabase.addUser("jenner");
        greetDatabase.addUser("sammy");
        greetDatabase.addUser("tristan");
        greetDatabase.addUser("caremen");

        greetDatabase.incrementGreets("bruce");
        greetDatabase.incrementGreets("bruce");
        greetDatabase.incrementGreets("bruce");
        greetDatabase.incrementGreets("bruce");
        greetDatabase.incrementGreets("bruce");
        greetDatabase.incrementGreets("bruce");
        greetDatabase.incrementGreets("bruce");
        greetDatabase.incrementGreets("bruce");
        greetDatabase.incrementGreets("bruce");

        greetDatabase.incrementGreets("tristan");
        greetDatabase.incrementGreets("tristan");
        greetDatabase.incrementGreets("tristan");
        greetDatabase.incrementGreets("tristan");
        greetDatabase.incrementGreets("tristan");
        greetDatabase.incrementGreets("tristan");
        greetDatabase.incrementGreets("tristan");
        greetDatabase.incrementGreets("tristan");

        greetDatabase.incrementGreets("sarah");
        greetDatabase.incrementGreets("sarah");
        greetDatabase.incrementGreets("sarah");
        greetDatabase.incrementGreets("sarah");
        greetDatabase.incrementGreets("sarah");
        greetDatabase.incrementGreets("sarah");
        greetDatabase.incrementGreets("sarah");
        greetDatabase.incrementGreets("sarah");
        assertEquals(greetDatabase.getTotalGreets(), 25);

    }

}
