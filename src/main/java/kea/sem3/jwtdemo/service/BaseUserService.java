package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.BaseUserRequest;
import kea.sem3.jwtdemo.dto.BaseUserResponse;
import kea.sem3.jwtdemo.entity.BaseUser;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.BaseUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BaseUserService {
    BaseUserRepository baseUserRepository;

    public List<BaseUserResponse> getBaseUsers() {
        return BaseUserResponse.getBaseUsersFromEntities(baseUserRepository.findAll());
    }

    public BaseUserResponse getBaseUser(String id, boolean all) {
        return new BaseUserResponse(baseUserRepository.findById(id)
                .orElseThrow(() -> new Client4xxException("No BaseUser with this id")), all);
    }

    public BaseUserResponse addBaseUser(BaseUserRequest body) {
        BaseUser baseUser = baseUserRepository.save(new BaseUser(body));
        return new BaseUserResponse(baseUser, true); // should not return all info to users
    }

    public BaseUserResponse editBaseUser(BaseUserRequest body, String id) {
        BaseUser baseUserToEdit = new BaseUser(body);
        baseUserToEdit.setUsername(id);
        baseUserRepository.save(baseUserToEdit);
        return new BaseUserResponse(baseUserToEdit, true);
    }

    public void deleteBaseUser(String id) {
        baseUserRepository.deleteById(id);
    }

}
