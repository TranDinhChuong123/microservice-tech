package com.programingtech.studentservice.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Faculty {
//   kHoa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


//    @OneToMany(mappedBy = "faculty" ,cascade = CascadeType.ALL)
//    private List<Major> majors;




    // Getters and setters
}
