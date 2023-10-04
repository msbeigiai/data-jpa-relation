package com.msbeigi.tutorialapp.repository;

import com.msbeigi.tutorialapp.entity.Course;
import com.msbeigi.tutorialapp.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Course course = new Course("Scala", 5);
        Course course2 = new Course("C++", 6);
        Teacher teacher = new Teacher(
                "Mohsen",
                "Sadeghbeigi"
//                List.of(
//                        course,
//                        course2
//                )
        );
        teacherRepository.save(teacher);
    }
}