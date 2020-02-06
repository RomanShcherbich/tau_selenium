package tau.selenium.saucedemo.matchers;

import static org.testng.Assert.assertEquals;

public class FinishMatcher {

    public void messageLike(String actualMessage, String expectedMessage) {
        assertEquals(actualMessage, expectedMessage, "Message is not correct");
    }
}
