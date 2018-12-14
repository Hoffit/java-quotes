import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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


/**
 * This Java application prints a random quote to the console.
 *
 * The quote will come from the following API if a connection is available:
 * http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en
 *
 * Otherwise, the quote will come from the quote cache.
 *
 * The core quote cache represents a list of quotes provided for the lab. Plus,
 * each time this application is executed, and successfully retrieves a quote
 * from the API, it is added to the cache.
 *
 * A note about this application. It is essentially a static, procedural app.
 */
public class App {

    /**
     * The local file based quote cache. The file contents are expected to be in
     * JSON format.
     */
    private static ArrayList<Quote> quoteCache = new ArrayList<>();

    /**
     * The application.
     * @param args Ignored/not used.
     */
    public static void main(String[] args) {
        readCachedQuotes();
        System.out.println(getRandomQuote());
        writeCachedQuotes();
    }

    /**
     * Reads quotes from the local file based cache into memory.
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
        if (reader != null) {
            quoteCache = gson.fromJson(reader, typeOfT);
        }
    }

//TODO Gson new v builder???

    /**
     * Writes the quotes to the local file based cache.
     */
    private static void writeCachedQuotes() {
        //TODO Pretty print isn't working. Is there another way?
        try (Writer writer = new FileWriter("assets/recentquotes.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(quoteCache, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calls the quote API, updates the in-memory collection of quotes, and returns the API quote.
     * @return The quote retrieved from the API.
     * @throws IOException In case the API could not be contacted, or was unsuccessful in retrieving a quote.
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
     * Returns a quote from the API, if possible, and reverts to returning a cached quote, if the API
     * quote is not available.
     * @return The quote, either from the API, or the cache.
     */
    static String getRandomQuote() {

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

    /**
     * This is a test method that is used to test the random algorithm. It's not intended
     * for actual use. It demonstrates even distribution by the Random algorithm.
     * @param quotes Cache of quotes.
     * @return A randomly selected quote.
     */
    static String getRandomQuote(Quote[] quotes) {
        Random r = new Random();
        return quotes[r.nextInt(quotes.length)].toAuthorAndTextString();
    }
}
