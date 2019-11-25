package es.ethcero.mca.blogcero.models;

public class Comment {

    private long id;
    private String user;
    private String body;

    public Comment() {
    }

    public Comment(String user, String body) {
        this.user = user;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
