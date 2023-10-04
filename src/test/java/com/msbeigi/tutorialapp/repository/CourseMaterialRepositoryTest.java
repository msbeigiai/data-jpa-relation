package com.msbeigi.tutorialapp.repository;

import com.msbeigi.tutorialapp.entity.Course;
import com.msbeigi.tutorialapp.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial() {
        Course course = new Course(".Net", 3);

        CourseMaterial courseMaterial = new CourseMaterial(
                "https://yahoo.com",
                course
        );

        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    void getAllCourseMaterials() {
        courseMaterialRepository.findAll()
                .forEach(System.out::println);
    }
}