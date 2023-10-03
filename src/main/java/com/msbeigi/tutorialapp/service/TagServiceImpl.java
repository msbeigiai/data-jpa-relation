package com.msbeigi.tutorialapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.msbeigi.tutorialapp.entity.Tag;
import com.msbeigi.tutorialapp.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> findTagsByTutorialsId(Long tutorialId) {
        return tagRepository.findTagsByTutorialId(tutorialId);
    }

    @Override
    public Optional<Tag> findTagById(Long tagId) {
        return tagRepository.findById(tagId);
    }

    @Override
    public boolean existsById(Long tagId) {
        return tagRepository.existsById(tagId);
    }

}
