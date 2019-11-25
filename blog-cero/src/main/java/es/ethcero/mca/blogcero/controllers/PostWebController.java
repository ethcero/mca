package es.ethcero.mca.blogcero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

import es.ethcero.mca.blogcero.models.Comment;
import es.ethcero.mca.blogcero.models.Post;
import es.ethcero.mca.blogcero.services.PostService;

/**
 * @author fran
 */
@Controller
public class PostWebController {

    @Autowired
    PostService service;

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", this.service.getPosts());
        return "posts_template";
    }

    @GetMapping("/posts/new")
    public String newPost() {
        return "post_new_template";
    }

    @PostMapping(value = "/posts/new")
    public RedirectView createPost( Post post) {
        this.service.addPost(post);
        return new RedirectView("/posts/"+post.getId());
    }

    @RequestMapping("/posts/{postId}")
    public String post(HttpSession session, Model model, @PathVariable long postId) {

        Post post = this.service.getPost(postId);
        model.addAttribute("post", post);
        String user = (String) session.getAttribute("user");
        model.addAttribute("user", user != null ? user : "");
        return "post_view_template";
    }

    @PostMapping("/posts/{postId}/comments/new")
    public RedirectView addComment(HttpSession session, Model model, @PathVariable long postId, Comment comment) {
        session.setAttribute("user", comment.getUser());
        this.service.addComment(postId, comment);
        return new RedirectView("/posts/"+postId);
    }

    @RequestMapping("/posts/{postId}/comments/{commentId}/delete")
    public RedirectView deleteComment(Model model, @PathVariable long postId, @PathVariable long commentId) {
        this.service.deleteComment(postId,commentId);
        return new RedirectView("/posts/"+postId);
    }
}
