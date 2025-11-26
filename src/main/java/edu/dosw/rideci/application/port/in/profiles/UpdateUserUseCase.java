package edu.dosw.rideci.application.port.in.profiles;


import edu.dosw.rideci.application.events.command.UpdateUserCommand;

import edu.dosw.rideci.domain.model.User;

public interface UpdateUserUseCase {

    User updateUser(UpdateUserCommand event);

}