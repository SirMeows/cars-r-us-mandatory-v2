package kea.sem3.jwtdemo.dto;

import kea.sem3.jwtdemo.entity.BaseUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserResponse {
    private String username;
    private String email;
    private String password;
    boolean enabled;
    private LocalDateTime dateCreated;
    private LocalDateTime dateEdited;

    public BaseUserResponse(BaseUser baseUser, boolean includeAll) {
        this.username = baseUser.getUsername();
        this.email = baseUser.getEmail();
        this.password = baseUser.getPassword();
        if(includeAll) {
            this.enabled = baseUser.isEnabled();
            this.dateCreated = baseUser.getDateCreated();
            this.dateEdited = baseUser.getDateEdited();
        }
    }

    public static List<BaseUserResponse> getBaseUsersFromEntities(List<BaseUser> baseUsers) {
        return baseUsers.stream().map(baseUser -> new BaseUserResponse(baseUser,false)).collect(Collectors.toList());
    }
}
