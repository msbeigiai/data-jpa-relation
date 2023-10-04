package com.msbeigi.tutorialapp.conntroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

        List<Tutorial> tutorials = tutorialService.findTutorialsByTagsId(tagId);

        return new ResponseEntity<List<Tutorial>>(tutorials, HttpStatus.OK);
    }

    @PostMapping("/tutorials/{tutorialId}/tags")
    public ResponseEntity<HttpStatus> addTag(@PathVariable("tutorialId") Long tutorialId,
            @RequestBody TagRequestBody tagRequestBody) {
        Tutorial tutorial = tutorialService.findTutorialById(tutorialId)
                .orElseThrow(() -> new ResourceNotFoundException("tutorial not found."));
        /*
         * if (tagRequestBody.id() != 0L) {
         * Tag tag = tagService.findTagById(tagRequestBody.id())
         * .orElseThrow(() -> new ResourceNotFoundException("tag not found"));
         * tutorial.addTag(tag);
         * tutorialService.saveTutorial(tutorial);
         * 
         * return new ResponseEntity<>(tag, HttpStatus.CREATED);
         * }
         */

        Tag tag = new Tag();
        // tag.setId(tagRequestBody.id());
        tag.setName(tagRequestBody.name());
        tutorial.addTag(tag);

        tutorialService.saveTutorial(tutorial);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") Long tagId, @RequestBody TagRequestBody tagRequestBody) {
        Tag tag = tagService.findTagById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("tag  not found"));
        tag.setName(tagRequestBody.name());

        tagService.saveTag(tag);

        return new ResponseEntity<Tag>(tag, HttpStatus.OK);
    }

    @DeleteMapping("/tutorials/{tutorialId}/tags/{tagId}")
    public ResponseEntity<HttpStatus> deleteTagFromTutorial(@PathVariable("tutorialId") Long tutorialId,
            @PathVariable("tagId") Long tagId) {
        Tutorial tutorial = tutorialService.findTutorialById(tutorialId)
                .orElseThrow(() -> new ResourceNotFoundException("tutorial not found"));
        tutorial.removeTag(tagId);
        tutorialService.saveTutorial(tutorial);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") Long tagId) {
        tagService.deleteById(tagId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
