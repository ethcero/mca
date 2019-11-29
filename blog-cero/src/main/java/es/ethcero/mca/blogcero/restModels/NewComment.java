package es.ethcero.mca.blogcero.restModels;

public class NewComment {

    private long authorId;
    private String body;

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
