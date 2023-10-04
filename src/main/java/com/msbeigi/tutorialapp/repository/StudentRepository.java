package com.msbeigi.tutorialapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msbeigi.tutorialapp.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);

    List<Student> findByNameContaining(String name);

    List<Student> findByLastnameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByNameAndLastname(String name, String lastname);
}
