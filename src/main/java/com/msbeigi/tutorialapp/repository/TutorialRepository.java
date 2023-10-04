package com.msbeigi.tutorialapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msbeigi.tutorialapp.entity.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findTutorialsByTagsId(Long tagId);

    List<Tutorial> findByTitleContaining(String title);

    List<Tutorial> findByPublished(boolean published);
}
