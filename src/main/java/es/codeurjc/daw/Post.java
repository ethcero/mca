package es.codeurjc.daw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Post {


	private long id = -1;

	private String title;

	private String content;

	private Map<Long, Comment> commentsMap = new HashMap<>();

	public Post() {
	}

	public Post(long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<Long, Comment> getCommentsMap() {
		return commentsMap;
	}

	public void setCommentsMap(Map<Long, Comment> commentsMap) {
		this.commentsMap = commentsMap;
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

	public String getContent() {
		return content;
	}

	public List<Comment> getComments() {
		return new ArrayList<>(this.commentsMap.values());
	}

	public Comment getComment(long id) {
		return this.commentsMap.get(id);
	}

	public void addComment(Comment comment) {
		this.commentsMap.put(comment.getId(), comment);
	}

	public void deleteComment(long commentId) {
		this.commentsMap.remove(commentId);
	}

	@Override
	public String toString() {
		return this.title + " (" + commentsMap.size() + " comments)";
	}

}
