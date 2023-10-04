package com.msbeigi.tutorialapp.conntroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msbeigi.tutorialapp.entity.Tutorial;
import com.msbeigi.tutorialapp.exception.ResourceNotFoundException;
import com.msbeigi.tutorialapp.model.TutorialRequestBody;
import com.msbeigi.tutorialapp.service.TutorialService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1")
public class TutorialController {

    private final TutorialService tutorialService;

    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();
        if (title == null) {
            tutorialService.findAllTutorials().forEach(tutorials::add);
        } else {
            tutorialService.findByTitleContaining(title).forEach(tutorials::add);
        }
        if (tutorials.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> findTutorialById(@PathVariable("id") Long tutorialId) {
        Tutorial tutorial = tutorialService.findTutorialById(tutorialId)
                .orElseThrow(() -> new ResourceNotFoundException("id not found"));
        return new ResponseEntity<Tutorial>(tutorial, HttpStatus.OK);
    }

    @PostMapping(value = "/tutorials")
    public ResponseEntity<HttpStatus> postMethodName(@RequestBody TutorialRequestBody tutorialRequestBody) {
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle(tutorialRequestBody.title());
        tutorial.setDescription(tutorialRequestBody.description());
        tutorial.setPublished(tutorialRequestBody.published());
        tutorialService.saveTutorial(tutorial);
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/tutorials/{id}")
    public ResponseEntity<Tutorial> putMethodName(@PathVariable("id") Long tutorialId,
            @RequestBody TutorialRequestBody tutorialRequestBody) {

        Tutorial tutorial = tutorialService.findTutorialById(tutorialId)
                .orElseThrow(() -> new ResourceNotFoundException("id not found"));

        tutorial.setTitle(tutorialRequestBody.title());
        tutorial.setDescription(tutorialRequestBody.description());
        tutorial.setPublished(tutorialRequestBody.published());

        tutorialService.saveTutorial(tutorial);

        return new ResponseEntity<Tutorial>(tutorial, HttpStatus.OK);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorialById(@PathVariable("id") Long tutorialId) {
        tutorialService.deleteTutorialById(tutorialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        tutorialService.deleteAllTutorials();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> getAllPublished() {
        List<Tutorial> publishedTutorials = tutorialService.findPublished(true);
        if (publishedTutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(publishedTutorials, HttpStatus.OK);
    }

}
