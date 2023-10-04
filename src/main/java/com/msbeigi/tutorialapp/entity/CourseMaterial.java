package com.msbeigi.tutorialapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(
        name = "course_material",
        uniqueConstraints = @UniqueConstraint(name = "url_unique", columnNames = "url")
)
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long id;
    private String url;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "id"
    )
    private Course course;

    public CourseMaterial() {
    }

    public CourseMaterial(String url) {
        this.url = url;
    }

    public CourseMaterial(String url, Course course) {
        this.url = url;
        this.course = course;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "CourseMaterial{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
