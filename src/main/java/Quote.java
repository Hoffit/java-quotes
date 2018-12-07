public class Quote {

    //TODO comment on the properties
    private String text;
    private String[] tags;
    private String author;
    private String likes;

    public Quote(String text, String[] tags, String author, String likes) {
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
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @return
     */
    public String[] getTags() {
        return tags;
    }

    /**
     *
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @return
     */
    public String getLikes() {
        return likes;
    }
}
