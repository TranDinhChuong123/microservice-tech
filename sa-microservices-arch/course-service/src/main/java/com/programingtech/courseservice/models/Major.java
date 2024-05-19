    package com.programingtech.courseservice.models;


    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.List;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "majors")
    @Entity

    public class Major {
    //    ngành học
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String description;

        @ManyToOne
        @JoinColumn(name = "faculty_id")
        private Faculty faculty;

        @JsonIgnore
        @ManyToMany
        @JoinTable(
                name = "major_courses",
                joinColumns = @JoinColumn(name = "major_id"),
                inverseJoinColumns = @JoinColumn(name = "course_id")
        )

        private List<Course> courses;

        @JsonIgnore
        @ManyToMany
        @JoinTable(
                name = "major_elective_courses", // môn học tự chọn
                joinColumns = @JoinColumn(name = "major_id"),
                inverseJoinColumns = @JoinColumn(name = "course_id")
        )
        private List<Course> electiveCourses;

        // Getters and setters
    }
