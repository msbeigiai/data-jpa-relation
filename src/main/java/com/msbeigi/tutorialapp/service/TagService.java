package com.msbeigi.tutorialapp.service;

import java.util.List;
import java.util.Optional;

import com.msbeigi.tutorialapp.entity.Tag;

public interface TagService {

    List<Tag> findAllTags();

    List<Tag> findTagsByTutorialsId(Long tutorialId);

    Optional<Tag> findTagById(Long tagId);

    boolean existsById(Long tagId);

}
