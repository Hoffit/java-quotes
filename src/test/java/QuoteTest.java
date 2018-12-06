import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class QuoteTest {

    @Test
    public void testQuoteConstructor() {
        //public Quote(String text, String[] tags, String author, String likes)
        Quote testQuote1 = new Quote("Quote1 text.", new String[] {"esoteric", "test", "tags"}, "Billy Bob", "10 likes");

        assertEquals("Confirm fields set correctly in the constructor.", "Quote1 text.", testQuote1.getText());
        assertArrayEquals("Confirm fields set correctly in the constructor.", new String[] {"esoteric", "test", "tags"}, testQuote1.getTags());
        assertEquals("Confirm fields set correctly in the constructor.", "Billy Bob", testQuote1.getAuthor());
        assertEquals("Confirm fields set correctly in the constructor.", "10 likes.", testQuote1.getLikes());
    }

    @Test
    public void testToAuthorAndTextString() {
        Quote testQuote1 = new Quote("Quote1 text.", new String[] {"esoteric", "test", "tags"}, "Billy Bob", "10 likes");
        Quote testQuote2 = new Quote("Hello world.", new String[] {"computers", "magic", "electricity"}, "Sue Ellen", "50 likes");
        Quote testQuote3 = new Quote("I like cats.", new String[] {"pets", "kitties", "meow", "fur"}, "Benicio", "1 like");
        assertEquals("Confirm String formatted correctly", "Quote1 text. - Billy Bob", testQuote1.toAuthorAndTextString());
        assertEquals("Confirm String formatted correctly", "Hello world. - Sue Ellen", testQuote2.toAuthorAndTextString());
        assertEquals("Confirm String formatted correctly", "I like cats. - Benicio", testQuote3.toAuthorAndTextString());
    }

    @Test
    public void testGetText() {
        Quote testQuote1 = new Quote("Quote1 text.", new String[] {"esoteric", "test", "tags"}, "Billy Bob", "10 likes");
        Quote testQuote2 = new Quote("Hello world.", new String[] {"computers", "magic", "electricity"}, "Sue Ellen", "50 likes");
        Quote testQuote3 = new Quote("I like cats.", new String[] {"pets", "kitties", "meow", "fur"}, "Benicio", "1 like");
        assertEquals("Confirm the quote is returned.", "Quote1 text.", testQuote1.getText());
        assertEquals("Confirm the quote is returned.", "Hello world.", testQuote2.getText());
        assertEquals("Confirm the quote is returned.", "I like cats.", testQuote3.getText());
    }

    @Test
    public void testGetTags() {
        Quote testQuote1 = new Quote("Quote1 text.", new String[] {"esoteric", "test", "tags"}, "Billy Bob", "10 likes");
        Quote testQuote2 = new Quote("Hello world.", new String[] {"computers", "magic", "electricity"}, "Sue Ellen", "50 likes");
        Quote testQuote3 = new Quote("I like cats.", new String[] {"pets", "kitties", "meow", "fur"}, "Benicio", "1 like");
        assertArrayEquals("Confirm the tags array is returned.", new String[] {"esoteric", "test", "tags"}, testQuote1.getTags());
        assertArrayEquals("Confirm the tags array is returned.", new String[] {"computers", "magic", "electricity"}, testQuote2.getTags());
        assertArrayEquals("Confirm the tags array is returned.", new String[] {"pets", "kitties", "meow", "fur"}, testQuote3.getTags());
    }

    @Test
    public void testGetAuthor() {
        Quote testQuote1 = new Quote("Quote1 text.", new String[] {"esoteric", "test", "tags"}, "Billy Bob", "10 likes");
        Quote testQuote2 = new Quote("Hello world.", new String[] {"computers", "magic", "electricity"}, "Sue Ellen", "50 likes");
        Quote testQuote3 = new Quote("I like cats.", new String[] {"pets", "kitties", "meow", "fur"}, "Benicio", "1 like");
        assertEquals("Confirm author's name is returned.", "Billy Bob", testQuote1.getAuthor());
        assertEquals("Confirm author's name is returned.", "Sue Ellen", testQuote2.getAuthor());
        assertEquals("Confirm author's name is returned.", "Benicio", testQuote3.getAuthor());
    }

    @Test
    public void testGetLikes() {
        Quote testQuote1 = new Quote("Quote1 text.", new String[] {"esoteric", "test", "tags"}, "Billy Bob", "10 likes");
        Quote testQuote2 = new Quote("Hello world.", new String[] {"computers", "magic", "electricity"}, "Sue Ellen", "50 likes");
        Quote testQuote3 = new Quote("I like cats.", new String[] {"pets", "kitties", "meow", "fur"}, "Benicio", "1 like");
        assertEquals("Confirm author's name is returned.", "10 likes", testQuote1.getLikes());
        assertEquals("Confirm author's name is returned.", "50 likes", testQuote2.getLikes());
        assertEquals("Confirm author's name is returned.", "1 like", testQuote3.getLikes());
    }

    /**
     * This tests approximate distribution of the random indexes generated by the input array.
     * It should theoretically return 5 after enough iterations, but because it generates random
     * numbers, this test can possibly fail in outlier cases, and the method still be working properly.
     * However, it should not fail repeatedly.
     */
    @Test
    public void testRandomQuoteGeneratorDistribution() {
        Quote[] quotes = createQuoteArray();
        int sum = 0;
        int iterations = 10000;
        for (int i = 0; i < iterations; i++) {
            sum += findIndex(App.getRandomQuote(quotes), quotes) + 1;
        }
        sum /= iterations;
        //System.out.println(sum);

        assertEquals("See comment above this method if test fails, should return '5'.", 5, sum);
    }

    private static Quote[] createQuoteArray() {
        Quote[] testArray = new Quote[10];
        testArray[0] = new Quote("Quote 1", new String[] {"tag1", "tag2"}, "author1", "10 likes");
        testArray[1] = new Quote("Quote 2", new String[] {"tag1", "tag2"}, "author1", "10 likes");
        testArray[2] = new Quote("Quote 3", new String[] {"tag1", "tag2"}, "author1", "10 likes");
        testArray[3] = new Quote("Quote 4", new String[] {"tag1", "tag2"}, "author1", "10 likes");
        testArray[4] = new Quote("Quote 5", new String[] {"tag1", "tag2"}, "author1", "10 likes");
        testArray[5] = new Quote("Quote 6", new String[] {"tag1", "tag2"}, "author1", "10 likes");
        testArray[6] = new Quote("Quote 7", new String[] {"tag1", "tag2"}, "author1", "10 likes");
        testArray[7] = new Quote("Quote 8", new String[] {"tag1", "tag2"}, "author1", "10 likes");
        testArray[8] = new Quote("Quote 9", new String[] {"tag1", "tag2"}, "author1", "10 likes");
        testArray[9] = new Quote("Quote 10", new String[] {"tag1", "tag2"}, "author1", "10 likes");
        return testArray;
    }

    private static int findIndex(String quoteString, Quote[] quotes) {
        for (int i=0; i<quotes.length; i++) {
            if (quoteString.equals(quotes[i].toAuthorAndTextString())) {
                return i;
            }
        }
        return -1000000000;
    }
}