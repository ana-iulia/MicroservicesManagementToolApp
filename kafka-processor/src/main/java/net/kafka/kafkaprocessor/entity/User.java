package net.kafka.kafkaprocessor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Long id;
    private String lastName;
    private String firstName;
    private String description;
    private String email;

}
