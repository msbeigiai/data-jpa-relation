package com.msbeigi.tutorialapp.entity;

import jakarta.persistence.*;

@Entity(name = "course")
@Table(name = "course")
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;
    private String title;
    private Integer credit;

    @OneToOne(
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id"
    )
    private Teacher teacher;

    public Course() {
    }

    public Course(String title, Integer credit) {
        this.title = title;
        this.credit = credit;
    }

    public Course(String title, Integer credit, CourseMaterial courseMaterial) {
        this.title = title;
        this.credit = credit;
        this.courseMaterial = courseMaterial;
    }

    public Course(String title, Integer credit, Teacher teacher) {
        this.title = title;
        this.credit = credit;
        this.teacher = teacher;
    }

    public Course(String title, Integer credit, CourseMaterial courseMaterial, Teacher teacher) {
        this.title = title;
        this.credit = credit;
        this.courseMaterial = courseMaterial;
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public CourseMaterial getCourseMaterial() {
        return courseMaterial;
    }

    public void setCourseMaterial(CourseMaterial courseMaterial) {
        this.courseMaterial = courseMaterial;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", credit=" + credit +
                ", courseMaterial=" + courseMaterial +
                ", teacher=" + teacher +
                '}';
    }
}
