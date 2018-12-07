import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;


/*
 * This Java application prints random quoteCache to the console.
 * The quote will come from the following API if a connection is available:
 * http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en
 * Otherwise, the quote will come from the quote cache.
 * The quote cache consists of content provided in Lab, plus quoteCache from the
 * above API that have previously been retrieved and then cached.
 */
public class App {

    private static ArrayList<Quote> quoteCache;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        readCachedQuotes();
        System.out.println(getRandomQuote());
    }

    /**
     *
     */
    private static void readCachedQuotes() {
        BufferedReader reader = null;
        Path inputPath = FileSystems.getDefault().getPath("assets/recentquotes.json");

        try {
            reader = Files.newBufferedReader(inputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type typeOfT = new TypeToken<ArrayList<Quote>>(){}.getType();
        quoteCache = gson.fromJson(reader, typeOfT);
    }

    /**
     *
     */
    private static void writeCachedQuotes() {
//        Path outputPath = FileSystems.getDefault().getPath("assets/recentquotes.json");
//        FileOutputStream outputStream = openFileOutput
//
//
//        Gson gson = new Gson();
//        String quotesToCache = gson.toJson(quoteCache);

        try (Writer writer = new FileWriter("assets/recentquotes.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    /**
     *
     * @return
     * @throws IOException
     */
    private static String requestAPIQuote() throws IOException {
        URL aURL = new URL("http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en");
        HttpURLConnection  connection = (HttpURLConnection) aURL.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine = reader.readLine();
        reader.close();
        return inputLine;
    }

    /**
     *
     * @return
     */
    protected static String getRandomQuote() {

        String theQuote;
        try {
            theQuote = requestAPIQuote();

            //Get the quote text
            int quoteTextKeyIndex = theQuote.indexOf("quoteText");
            int quoteTextStartIndex = theQuote.indexOf('"', quoteTextKeyIndex+11) + 1;
            int quoteTextEndIndex = theQuote.indexOf('"', quoteTextStartIndex + 1);
            String quoteText = theQuote.substring(quoteTextStartIndex, quoteTextEndIndex);

            //Get the author text
            int authorTextKeyIndex = theQuote.indexOf("quoteAuthor");
            int authorTextStartIndex = theQuote.indexOf('"', authorTextKeyIndex+12) + 1;
            int authorTextEndIndex = theQuote.indexOf('"', authorTextStartIndex+1);
            String authorText = theQuote.substring(authorTextStartIndex, authorTextEndIndex);
            System.out.println();
            quoteCache.add(new Quote(quoteText, null, authorText, null));
        } catch (IOException e) {
            Random r = new Random();
            theQuote = quoteCache.get(r.nextInt(quoteCache.size())).toAuthorAndTextString();
        }

        return theQuote;
    }
}
