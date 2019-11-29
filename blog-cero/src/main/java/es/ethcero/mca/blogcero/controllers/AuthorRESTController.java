package es.ethcero.mca.blogcero.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import es.ethcero.mca.blogcero.models.Author;
import es.ethcero.mca.blogcero.models.Comment;
import es.ethcero.mca.blogcero.responses.ResponseObjectOrNotFound;
import es.ethcero.mca.blogcero.services.PostService;

/**
 * @author fran
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorRESTController {

    @Autowired
    private PostService service;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Author postPost(@RequestBody Author author){

        return this.service.addAuthor(author);
    }

    @GetMapping("/{authorId}/comments")
    @JsonView(Comment.NoAuthor.class)
    ResponseEntity<List<Comment>> getComments(@PathVariable long authorId) {

        Optional<List<Comment>> comments = this.service.getAuthorComments(authorId);

        return (ResponseEntity<List<Comment>>) new ResponseObjectOrNotFound(comments).response();
    }

    @GetMapping("")
    List<Author> getAuthors() {

        return this.service.getAuthors();
    }


}
