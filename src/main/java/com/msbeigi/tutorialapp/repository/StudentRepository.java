package com.msbeigi.tutorialapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.msbeigi.tutorialapp.entity.Student;

import jakarta.transaction.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);

    List<Student> findByNameContaining(String name);

    List<Student> findByLastnameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByNameAndLastname(String name, String lastname);

    @Query("select s from student s where s.email = ?1")
    Student getStudentByEmailAddress(String email);

    @Query("select s.name from student s where s.email = ?1")
    String getStudentFirstnameByEmailAddress(String email);

    @Query(value = "select * from student s where s.email = ?1", nativeQuery = true)
    Student getStudentByEmailAddressNative(String email);

    @Query(value = "select * from student s where s.email = :email", nativeQuery = true)
    Student getStudentByEmailAddressNativeNameParam(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "update student set name = ?1 where email = ?2", nativeQuery = true)
    int updateStudentNameByEmail(String name, String email);

}
