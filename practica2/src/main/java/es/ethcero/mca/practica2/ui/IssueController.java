
package es.ethcero.mca.practica2.ui;

import es.ethcero.mca.practica2.model.Issue;
import es.ethcero.mca.practica2.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fran
 */
@RestController
@RequestMapping("/issues")
public class IssueController {


    @Autowired
    private IssueRepository issueRepository;

    @GetMapping("")
    ResponseEntity<List<Issue>> issues() {
        return ResponseEntity.ok().body(issueRepository.findAll());
    }
}
