package es.codeurjc.daw;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import es.codeurjc.daw.dto.CommentDTO;
import es.codeurjc.daw.dto.NewPostDTO;
import es.codeurjc.daw.dto.FullPostDTO;

@Service
public class PostService {

	private Map<Long, Post> posts = new ConcurrentHashMap<>();
	private AtomicLong lastPostId = new AtomicLong();
	private AtomicLong lastCommentId = new AtomicLong();

	@Autowired
	private ModelMapper modelMapper;

	public List<FullPostDTO> getPosts() {

		return this.posts.values().stream()
				.map(post -> modelMapper.map(post, FullPostDTO.class))
				.collect(Collectors.toList());

	}

	public FullPostDTO getPost(long id) {

		return modelMapper.map(this.posts.get(id), FullPostDTO.class);

	}

	public void addPost(NewPostDTO postDTO) {
		postDTO.setId(lastPostId.incrementAndGet());
		Post post = modelMapper.map(postDTO, Post.class);
		this.posts.put(post.getId(), post);

	}

	public void saveComment(long postid, CommentDTO commentDTO) {

		commentDTO.setId(this.lastCommentId.incrementAndGet());

		Comment comment = modelMapper.map(commentDTO, Comment.class);

		this.posts.get(postid).addComment(comment);
	}

	public CommentDTO getComment(long postId, long commentId) {
		return modelMapper.map(this.posts.get(postId).getComment(commentId), CommentDTO.class);
	}

	public void deleteComment(long postId, long commentId) {
		this.posts.get(postId).deleteComment(commentId);
	}

/*	public void setCommentId(CommentDTO comment) {
		comment.setId(this.lastCommentId.incrementAndGet());
	}
*/

}
