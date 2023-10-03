package com.msbeigi.tutorialapp.service;

import java.util.List;
import java.util.Optional;

import com.msbeigi.tutorialapp.entity.Tutorial;

public interface TutorialService {

    List<Tutorial> findAllTutorials();

    List<Tutorial> findByTitleContaining(String title);

    Optional<Tutorial> findTutorialById(Long tutrorialId);

    void saveTutorial(Tutorial tutorial);

    void deleteTutorialById(Long tutorialId);

    void deleteAllTutorials();

    List<Tutorial> findPublished(boolean published);

    boolean existsById(Long tutorialId);

    List<Tutorial> findTutorialsByTagId(Long tagId);

}
