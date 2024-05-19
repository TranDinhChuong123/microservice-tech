package com.programingtech.courseservice.payloadRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailMessage {
    private String to;
    private String toName;
    private String subjectCourses;
}
