package es.codeurjc.daw;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import es.codeurjc.daw.dto.BasicPostDTO;
import es.codeurjc.daw.dto.CommentDTO;
import es.codeurjc.daw.dto.FullPostDTO;
import es.codeurjc.daw.dto.NewPostDTO;

@RestController
@RequestMapping("/api")
public class BlogRestController {


	@Autowired
	private PostService postService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/post")
	public ResponseEntity<List<BasicPostDTO>> listPosts() {

		List<BasicPostDTO> list = this.postService.getPosts().stream().map(fullPostDTO -> modelMapper.map(fullPostDTO, BasicPostDTO.class)).collect(Collectors.toList());

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/post/{id}")
	public ResponseEntity<FullPostDTO> getPost(@PathVariable long id) {
		FullPostDTO post = this.postService.getPost(id);
		if (post == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(this.postService.getPost(id), HttpStatus.OK);
	}

	@PostMapping("/post")
	public ResponseEntity<BasicPostDTO> newPost(@RequestBody NewPostDTO post) {
		this.postService.addPost(post);
		BasicPostDTO basc = modelMapper.map(post, BasicPostDTO.class);
		return new ResponseEntity<>(basc, HttpStatus.CREATED);
	}

	@PostMapping("/post/{postId}/comment")
	public ResponseEntity<CommentDTO> newComment(@PathVariable long postId, @RequestBody CommentDTO comment) {
		FullPostDTO post = this.postService.getPost(postId);
		if (post == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.postService.saveComment(postId, comment);

		return new ResponseEntity<>(comment, HttpStatus.CREATED);
	}

	@DeleteMapping("/post/{postId}/comment/{commentId}")
	public ResponseEntity<CommentDTO> deleteComment(@PathVariable long postId, @PathVariable long commentId) {
		FullPostDTO post = this.postService.getPost(postId);
		if (post == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		CommentDTO comment = this.postService.getComment(postId, commentId);
		if (comment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.postService.deleteComment(postId, commentId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
