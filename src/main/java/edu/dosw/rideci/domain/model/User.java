package edu.dosw.rideci.domain.model;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private Long userId;
    private String name;
    private String email;
    private String identificationNumber;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;
    
}