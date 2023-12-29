package gorestapi.config;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class Config {
    protected static Playwright playwright;
    protected APIRequestContext requestContext;

    @BeforeAll
    public static void setUp() {
        playwright = Playwright.create();
    }

    @AfterAll
    public static void tearDown() {
        playwright.close();
    }
}
