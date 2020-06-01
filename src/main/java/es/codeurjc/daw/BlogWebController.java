package es.codeurjc.daw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

import es.codeurjc.daw.dto.CommentDTO;
import es.codeurjc.daw.dto.NewPostDTO;
import es.codeurjc.daw.dto.FullPostDTO;

@Controller
public class BlogWebController {

	@Autowired
	private PostService postService;

	@GetMapping("/")
	public String blog(Model model) {
		model.addAttribute("posts", this.postService.getPosts());
		return "blog";
	}

	@GetMapping("/post/{id}")
	public String post(HttpSession session, @PathVariable long id, Model model) {
		FullPostDTO post = this.postService.getPost(id);
		if (post == null) {
			model.addAttribute("errorMessage", "No existe un post con id " + id);
			return "error";
		}
		Object userName = session.getAttribute("userName");
		model.addAttribute("userName", userName != null ? userName : "");
		model.addAttribute("post", post);
		return "post";
	}

	@GetMapping("/post/new")
	public String post(Model model) {
		return "newPost";
	}

	@PostMapping("/post")
	public String post(Model model, NewPostDTO post) {
		this.postService.addPost(post);
		return "redirect:/post/" + post.getId();
	}

	@PostMapping("/post/{id}/comment")
	public String post(HttpSession session, @PathVariable long id, Model model, CommentDTO comment) {
		session.setAttribute("userName", comment.getAuthor());
		FullPostDTO post = this.postService.getPost(id);
		if (post == null) {
			model.addAttribute("errorMessage", "No existe un post con id " + id);
			return "error";
		}
		this.postService.saveComment(post.getId(), comment);
		return "redirect:/post/" + post.getId();
	}

	@PostMapping("/post/{postId}/comment/{commentId}/delete")
	public String post(@PathVariable long postId, @PathVariable long commentId, Model model) {
		FullPostDTO post = this.postService.getPost(postId);
		if (post == null) {
			model.addAttribute("errorMessage", "No existe un post con id " + postId);
			return "error";
		}
		CommentDTO comment = this.postService.getComment(postId, commentId);
		if (comment == null) {
			model.addAttribute("errorMessage", "No existe un comentario con id " + commentId);
			return "error";
		}
		this.postService.deleteComment(postId, commentId);
		return "redirect:/post/" + post.getId();
	}

}
