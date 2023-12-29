package gorestapi.apitests.usercreation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import gorestapi.accesses.Accesses;
import gorestapi.config.Config;
import org.junit.jupiter.api.Test;
import org.pojoclasses.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static gorestapi.accesses.Accesses.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateAndCheckUserTest extends Config {

    @Test
    public void createUserWithoutAuth() throws IOException {
        Map<String, String> userData = new HashMap<>();

        userData.put( "name", "Kate Laswell" );
        userData.put( "email", "laswell_12345@mail.com" );
        userData.put( "gender", "female" );
        userData.put( "status", "active" );

        requestContext = playwright.request().newContext();
        APIResponse response = requestContext.post( URL_USERS, RequestOptions.create().setData( userData ) );

        assertEquals( response.status(), 401);
        assertEquals( response.statusText(), "Unauthorized" );

        JsonNode responseJson = new ObjectMapper().readTree( response.body() );

        System.out.println( "Test processing is finished." );
        System.out.println( "Server response is " + responseJson.toPrettyString() );
    }

    @Test
    public void createUserWithAuth() throws IOException {
        Map<String, String> headers = new HashMap<>();
        Map<String, String> userData = new HashMap<>();

        headers.put( "Authorization", AUTH_TYPE + " " + AUTH_TOKEN );
        userData.put( "name", "Kate Laswall" );
        userData.put( "email", "laswall_12345@mail.com" );
        userData.put( "gender", "female" );
        userData.put( "status", "active" );

        requestContext = playwright
                .request()
                .newContext( new APIRequest.NewContextOptions().setExtraHTTPHeaders( headers ) );

        APIResponse response = requestContext.post( URL_USERS, RequestOptions.create().setData( userData ) );

        assertEquals( response.status(), 201 );
        assertEquals( response.statusText(), "Created" );

        JsonNode responseJson = new ObjectMapper().readTree( response.body() );

        System.out.println( "Test processing is finished." );
        System.out.println( "Server response is " + responseJson.toPrettyString() );
    }

    @Test
    public void createUserWithPOJO() throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put( "Authorization", AUTH_TYPE + " " + AUTH_TOKEN );

        User testUser = new User(
                "Kate Losswell",
                "losswell_12345@mail.com",
                "female",
                "active"
        );

        requestContext = playwright
                .request()
                .newContext( new APIRequest.NewContextOptions().setExtraHTTPHeaders( headers ) );

        APIResponse response = requestContext.post( URL_USERS, RequestOptions.create().setData( testUser ) );

        assertEquals( response.status(), 201 );
        assertEquals( response.statusText(), "Created" );

        JsonNode responseJson = new ObjectMapper().readTree( response.body() );

        System.out.println( "Test processing is finished." );
        System.out.println( "Server response is " + responseJson.toPrettyString() );


    }

}
















