package com.msbeigi.tutorialapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.msbeigi.tutorialapp.entity.Tutorial;
import com.msbeigi.tutorialapp.repository.TutorialRepository;

@Service
public class TutorialServiceImpl implements TutorialService {

    private final TutorialRepository tutorialRepository;

    public TutorialServiceImpl(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    @Override
    public List<Tutorial> findAllTutorials() {
        return tutorialRepository.findAll();
    }

    @Override
    public List<Tutorial> findByTitleContaining(String title) {
        return tutorialRepository.findByTitleContaining(title);
    }

    @Override
    public Optional<Tutorial> findTutorialById(Long tutorialId) {
        return tutorialRepository.findById(tutorialId);
    }

    @Override
    public void saveTutorial(Tutorial tutorial) {
        tutorialRepository.save(tutorial);
    }

    @Override
    public void deleteTutorialById(Long tutorialId) {
        tutorialRepository.deleteById(tutorialId);
    }

    @Override
    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }

    @Override
    public List<Tutorial> findPublished(boolean published) {
        return tutorialRepository.findByPublished(published);
    }

    @Override
    public boolean existsById(Long tutorialId) {
        return tutorialRepository.existsById(tutorialId);
    }

    @Override
    public List<Tutorial> findTutorialsByTagsId(Long tagId) {
        return tutorialRepository.findTutorialsByTagsId(tagId);
    }

}
