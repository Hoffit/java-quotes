/**
 * This class represent a quote. At a minimum, it will have the quote text.
 */
public class Quote {

    /**
     * The quote.
     */
    private String text;

    /**
     * Keywords from the quote.
     */
    private String[] tags;

    /**
     * The author of the quote.
     */
    private String author;

    /**
     * The count of likes String.
     */
    private String likes;

    /**
     * The Quote Constructor. The quote must be non-null, otherwise it's an input exception.
     * All other values are optional.
     * @param text The quote text. Must be non-null, and a non-empty String.
     * @param tags The tags.
     * @param author The author.
     * @param likes The likes.
     */
    public Quote(String text, String[] tags, String author, String likes) {
        if (text == null || text.length() == 0) {
            throw new IllegalArgumentException("Error: quote text must be non-null, and a non-empty String.");
        }
        this.text = text;
        this.tags = tags;
        this.author = author;
        this.likes = likes;
    }

    /**
     *
     * @return
     */
    public String toAuthorAndTextString() {
        return text + " - " + author;
    }

    /**
     * Getter.
     * @return The quote.
     */
    public String getText() {
        return text;
    }

    /**
     * Getter.
     * @return The tags.
     */
    public String[] getTags() {
        return tags;
    }

    /**
     * Getter.
     * @return The author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Getter.
     * @return The likes.
     */
    public String getLikes() {
        return likes;
    }
}
