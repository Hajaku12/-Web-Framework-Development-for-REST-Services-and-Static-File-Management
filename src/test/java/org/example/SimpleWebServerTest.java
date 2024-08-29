package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class SimpleWebServerTest {
    private Socket mockSocket;
    private BufferedReader mockIn;
    private PrintWriter mockOut;
    private BufferedOutputStream mockDataOut;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() throws IOException {
        mockSocket = mock(Socket.class);
        mockIn = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("GET /App/hello HTTP/1.1\n\n".getBytes())));
        outputStream = new ByteArrayOutputStream();
        mockOut = new PrintWriter(outputStream, true);
        mockDataOut = mock(BufferedOutputStream.class);

        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("GET /App/hello HTTP/1.1\n\n".getBytes()));
        when(mockSocket.getOutputStream()).thenReturn(outputStream);
    }

    @Test
    public void testGetHelloRoute() throws IOException {
        SimpleWebServer.get("/App/hello", (req, res) -> "Hello Test");

        SimpleWebServer.handleClient(mockSocket);

        String response = outputStream.toString();
        assert response.contains("HTTP/1.1 200 OK");
        assert response.contains("Content-Type: text/plain");
        assert response.contains("Hello Test");
    }

    @Test
    public void testGetPiRoute() throws IOException {
        mockIn = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("GET /App/pi HTTP/1.1\n\n".getBytes())));
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("GET /App/pi HTTP/1.1\n\n".getBytes()));

        SimpleWebServer.get("/App/pi", (req, res) -> String.valueOf(Math.PI));

        SimpleWebServer.handleClient(mockSocket);

        String response = outputStream.toString();
        assert response.contains("HTTP/1.1 200 OK");
        assert response.contains("Content-Type: text/plain");
        assert response.contains(String.valueOf(Math.PI));
    }

}