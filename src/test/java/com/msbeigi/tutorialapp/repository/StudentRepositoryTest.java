package com.msbeigi.tutorialapp.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.msbeigi.tutorialapp.entity.Guardian;
import com.msbeigi.tutorialapp.entity.Student;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        /*
         * Student student = new Student("Mohsen", "Sadegh",
         * "mohsen@gmail.com", "Ali",
         * "ali@gmail.com", "99999999");
         * 
         * studentRepository.save(student);
         */
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = new Guardian("Ali", "ali@gmail.com", "99999999");
        Student student = new Student("Mohsen", "sadegh", "mohsen2@gmail.com", guardian);

        studentRepository.save(student);
    }

    @Test
    public void printAllStudents() {
        List<Student> students = studentRepository.findAll();

        students.forEach(System.out::println);
    }

    @Test
    public void findStudentByFirstname() {
        studentRepository.findByName("Mohsen")
                .forEach(System.out::println);
    }

    @Test
    public void findStudentByNameContaining() {
        studentRepository.findByNameContaining("mo")
                .forEach(System.out::println);
    }

    @Test
    public void printStudentBasedOnGuardianName() {
        studentRepository.findByGuardianName("ali")
                .forEach(System.out::println);
    }

    @Test
    public void findStudentByFirstnameAndLastname() {
        Student student = studentRepository.findByNameAndLastname("Mohsen", "Sadegh");
        System.out.println(student);
    }
}
