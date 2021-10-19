package ru.dm.ex_rate_by_ruble;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.dm.ex_rate_by_ruble.TestData.*;

@SpringBootTest
class ControllerTest {

    private WireMockServer wireMockServer = new WireMockServer();

    @BeforeEach
    void setUp() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
    }

    @AfterEach
    void stopWm() {
        wireMockServer.stop();
    }

    @Test
    void getCurrencyCodes() throws IOException {
        wireMockServer.stubFor(
                WireMock.get("/alpha/codes")
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(codes))
        );
        HttpResponse httpResponse = executeAndReturnResponse("http://localhost:8080/alpha/codes");
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        assertEquals("application/json", httpResponse.getFirstHeader("Content-Type").getValue());
        assertEquals(codes, convertHttpResponseToString(httpResponse));
    }

    @Test
    void checkAppCurrency() throws IOException {
        wireMockServer.stubFor(
                WireMock.get("/alpha/check?userCode=EUR")
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(getRateResponse))
        );
        HttpResponse httpResponse = executeAndReturnResponse("http://localhost:8080/alpha/check?userCode=EUR");
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        assertEquals("application/json", httpResponse.getFirstHeader("Content-Type").getValue());
        assertEquals(getRateResponse, convertHttpResponseToString(httpResponse));
    }

    @Test
    void invalidCode() throws IOException {
        wireMockServer.stubFor(
                WireMock.get("/alpha/check?userCode=SIKL")
                        .willReturn(aResponse()
                                .withStatus(404)
                                .withHeader("Content-Type", "application/json")
                                .withBody(invalidCodeResponse))
        );
        HttpResponse httpResponse = executeAndReturnResponse("http://localhost:8080/alpha/check?userCode=SIKL");

        assertEquals(404, httpResponse.getStatusLine().getStatusCode());
        assertEquals("application/json", httpResponse.getFirstHeader("Content-Type").getValue());
        assertEquals(invalidCodeResponse, convertHttpResponseToString(httpResponse));
    }

    private HttpResponse executeAndReturnResponse(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        return httpClient.execute(request);

    }

    private String convertHttpResponseToString(HttpResponse httpResponse) throws IOException {
        InputStream inputStream = httpResponse.getEntity().getContent();
        return convertInputStreamToString(inputStream);
    }

    private String convertInputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        String string = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return string;
    }


}