package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class SimpleWebServer {
    public static final String WEB_ROOT = "src/main/resources";
    private static final Map<String, BiFunction<Request, Response, String>> getRoutes = new HashMap<>();
    private static String staticFilesDir = WEB_ROOT;

    public static void get(String path, BiFunction<Request, Response, String> handler) {
        getRoutes.put(path, handler);
    }

    public static void staticfiles(String directory) {
        staticFilesDir = directory;
    }

    public static void main(String[] args) {
        staticfiles("src/main/resources");
        get("/App/hello", (req, res) -> "Hello " + req.getValues("name"));
        get("/App/pi", (req, res) -> String.valueOf(Math.PI));
        get("/App/received_data", (req, res) -> {
            try {
                Path filePath = Paths.get(WEB_ROOT, "received_data.txt");
                return Files.readString(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                return "Error reading file";
            }
        });

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started on port 8080");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedOutputStream dataOut = new BufferedOutputStream(clientSocket.getOutputStream())) {

            String requestLine = in.readLine();
            if (requestLine == null) return;
            String[] tokens = requestLine.split(" ");
            String method = tokens[0];
            String fileRequested = tokens[1];

            if (method.equals("GET")) {
                handleGetRequest(fileRequested, out, dataOut);
            } else if (method.equals("POST")) {
                handlePostRequest(fileRequested, in, out);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handlePostRequest(String fileRequested, BufferedReader in, PrintWriter out) throws IOException {
        StringBuilder payload = new StringBuilder();
        String line;

        while (!(line = in.readLine()).isEmpty()) {
        }

        while (in.ready() && (line = in.readLine()) != null) {
            payload.append(line);
        }
        String body = payload.toString();

        String fileName = "received_data.txt";
        try (FileWriter fileWriter = new FileWriter(new File(SimpleWebServer.WEB_ROOT, fileName))) {
            fileWriter.write(body);
        }

        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/plain");
        out.println();
        out.println("File created: " + fileName);
        out.flush();
    }

    private static void handleGetRequest(String fileRequested, PrintWriter out, BufferedOutputStream dataOut) throws IOException {
        String path = fileRequested.split("\\?")[0];
        String query = fileRequested.contains("?") ? fileRequested.split("\\?")[1] : "";

        if (getRoutes.containsKey(path)) {
            Request req = new Request(query);
            Response resp = new Response();
            String response = getRoutes.get(path).apply(req, resp);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/plain");
            out.println();
            out.println(response);
            out.flush();
        } else {
            File file = new File(staticFilesDir, path);
            if (file.exists()) {
                int fileLength = (int) file.length();
                String content = getContentType(path);
                byte[] fileData = readFileData(file, fileLength);

                out.println("HTTP/1.1 200 OK");
                out.println("Content-type: " + content);
                out.println("Content-length: " + fileLength);
                out.println();
                out.flush();
                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();
            } else {
                out.println("HTTP/1.1 404 Not Found");
                out.println("Content-type: text/html");
                out.println();
                out.flush();
                out.println("<html><body><h1>File Not Found</h1></body></html>");
                out.flush();
            }
        }
    }

    private static String getContentType(String fileRequested) {
        if (fileRequested.endsWith(".html")) return "text/html";
        else if (fileRequested.endsWith(".css")) return "text/css";
        else if (fileRequested.endsWith(".js")) return "application/javascript";
        else if (fileRequested.endsWith(".png")) return "image/png";
        else if (fileRequested.endsWith(".jpg")) return "image/jpeg";
        return "text/plain";
    }

    private static byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];
        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null) fileIn.close();
        }
        return fileData;
    }
}