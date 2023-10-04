package com.msbeigi.tutorialapp.repository;

import com.msbeigi.tutorialapp.entity.Course;
import com.msbeigi.tutorialapp.entity.CourseMaterial;
import com.msbeigi.tutorialapp.entity.Student;
import com.msbeigi.tutorialapp.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Test
    void findAllPagination() {
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2);

        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords)
                .getContent();
        System.out.println("courses = " + courses);

        long totalElements =
                courseRepository.findAll(firstPageWithThreeRecords)
                        .getTotalElements();
        System.out.println("totalElements = " + totalElements);

        long totalPages =
                courseRepository.findAll(firstPageWithThreeRecords)
                        .getTotalPages();
        System.out.println("totalPages = " + totalPages);
    }

    @Test
    void findAllSorting() {
        Pageable sortByTitle =
                PageRequest.of(0, 2, Sort.by("title"));
        Pageable sortByCreditDesc =
                PageRequest.of(0, 2, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        1, 2, Sort.by("title")
                                .descending().and(Sort.by("credit")));
        List<Course> courses =
                courseRepository.findAll(sortByTitle).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    void findByTitleContaining() {
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);
        List<Course> courses =
                courseRepository.findByTitleContaining("C", firstPageTenRecords)
                        .getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    void saveCourseWithStudentAndTeacher() {
        Teacher teacher = new Teacher("Mohammad", "Hassani");
        Course course = new Course("AI",12,teacher);
        Student student = new Student("Akbar", "Moradi", "akbar@gmail.com");
        course.addStudents(student);

        courseRepository.save(course);
    }
}