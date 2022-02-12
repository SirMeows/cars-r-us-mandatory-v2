package kea.sem3.jwtdemo.dto;

import kea.sem3.jwtdemo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserRequest {

        private String username;
        private String email;
        private String password;
        private boolean enabled;
        private List<Role> roles; // TODO: Should this be in the request?
}

