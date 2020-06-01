
package es.codeurjc.daw.dto;

/**
 * @author fran
 */
public class BasicPostDTO {

    private long id;

    private String title;

    public BasicPostDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
