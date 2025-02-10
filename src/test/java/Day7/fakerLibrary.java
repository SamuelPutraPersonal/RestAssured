package Day7;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class fakerLibrary {

    @Test
    void testGenerateDummyData(){
        Faker faker = new Faker();

        String fullName = faker.name().fullName();
        String username = faker.name().username();
        String email = faker.internet().safeEmailAddress();
        System.out.println("Full Name: " + fullName);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
    }
}
