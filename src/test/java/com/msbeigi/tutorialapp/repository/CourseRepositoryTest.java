package com.msbeigi.tutorialapp.repository;

import com.msbeigi.tutorialapp.entity.Course;
import com.msbeigi.tutorialapp.entity.CourseMaterial;
import com.msbeigi.tutorialapp.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void getAllCourses() {
        courseRepository.findAll()
                .forEach(System.out::println);
    }

    @Test
    void saveCourseWithTeacher() {
        Teacher teacher = new Teacher("Ali", "Ahmadi");
        CourseMaterial courseMaterial = new CourseMaterial("http://www.tesla.com");
        Course course = new Course("C#", 4, teacher);
        courseRepository.save(course);
    }
}