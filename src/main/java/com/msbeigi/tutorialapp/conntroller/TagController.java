package com.msbeigi.tutorialapp.conntroller;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msbeigi.tutorialapp.entity.Tag;
import com.msbeigi.tutorialapp.entity.Tutorial;
import com.msbeigi.tutorialapp.exception.ResourceNotFoundException;
import com.msbeigi.tutorialapp.model.TagRequestBody;
import com.msbeigi.tutorialapp.service.TagService;
import com.msbeigi.tutorialapp.service.TutorialService;

@RestController
@RequestMapping("/api/v1")
public class TagController {

    private final TagService tagService;
    private final TutorialService tutorialService;

    public TagController(TagService tagService, TutorialService tutorialService) {
        this.tagService = tagService;
        this.tutorialService = tutorialService;
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = new ArrayList<>();

        tagService.findAllTags().forEach(tags::add);

        if (tags.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
    }

    @GetMapping("/tutorials/{tutorialId}/tags")
    public ResponseEntity<List<Tag>> getAllTagsByTutorialId(@PathVariable("tutorialId") Long tutorialId) {
        if (!tutorialService.existsById(tutorialId)) {
            throw new ResourceNotFoundException("id not found");
        }

        List<Tag> tags = tagService.findTagsByTutorialsId(tutorialId);
        return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getTagsById(@PathVariable("id") Long tagId) {
        Tag tag = tagService.findTagById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("tag not found"));

        return new ResponseEntity<Tag>(tag, HttpStatus.OK);
    }

    @GetMapping("/tags/{tagId}/tutorials")
    public ResponseEntity<List<Tutorial>> findTutorialsByTagId(@PathVariable("tagId") Long tagId) {
        if (!tagService.existsById(tagId)) {
            throw new ResourceNotFoundException("tag not found!");
        }

        List<Tutorial> tutorials = tutorialService.findTutorialsByTagId(tagId);

        return new ResponseEntity<List<Tutorial>>(tutorials, HttpStatus.OK);
    }

    @PostMapping("/tutorials/{tutorialId}/tags")
    public ResponseEntity<Tag> addTag(@PathVariable("tutorialId") Long tutorialId, TagRequestBody tagRequestBody) {
        Tutorial tutorial = tutorialService.findTutorialById(tutorialId)
                .orElseThrow(() -> new ResourceNotFoundException("tutorial not found."));
        if (tagRequestBody.id() != 0L) {
            Tag tag = tagService.findTagById(tagRequestBody.id())
                    .orElseThrow(() -> new ResourceNotFoundException("tag not found"));
            tutorial.addTag(tag);
            tutorialService.saveTutorial(tutorial);

            return new ResponseEntity<>(tag, HttpStatus.CREATED);
        }

        Tag tag = new Tag();
        tag.setId(tagRequestBody.id());
        tag.setName(tagRequestBody.name());
        tutorial.addTag(tag);

        tutorialService.saveTutorial(tutorial);

        return new ResponseEntity<Tag>(tag, HttpStatus.CREATED);
    }

}
