package baseconfig;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext browserContext;
    protected Page page;

    @BeforeAll
    public static void setUp() {
        playwright = Playwright.create();
        browser = playwright
                .chromium()
                .launch( new BrowserType.LaunchOptions()
                        .setChannel( "chrome" )
                        .setHeadless( false ) );
    }

    @BeforeEach
    public void createContext() {
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterAll
    public static void tearDown() {
        playwright.close();
    }

    @AfterEach
    public void closeContext() {
        browserContext.close();
    }


}
