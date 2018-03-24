package de.utilities;
import java.util.UUID;

public class randomStringGenerator {
  

    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return  uuid;
    }
}
