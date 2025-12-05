package edu.dosw.rideci.application.events;

import edu.dosw.rideci.domain.model.enums.IdentificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {

    private Long userId;
    private String name;
    private String email;
    private IdentificationType identificationType;
    private String identificationNumber;
    private String phoneNumber;
    private String address;
    private String role;
    private String state;

}