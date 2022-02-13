package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.BaseUserRequest;
import kea.sem3.jwtdemo.dto.BaseUserResponse;
import kea.sem3.jwtdemo.service.BaseUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/base-user")
public class BaseUserController {
    BaseUserService baseUserService;

    @GetMapping
    public List<BaseUserResponse> getBaseUsers() {
        return baseUserService.getBaseUsers();
    }

    @GetMapping("/{id}")
    public BaseUserResponse getBaseUser(@PathVariable String id) throws Exception {
        return baseUserService.getBaseUser(id, false);
    }

    @PutMapping
    public BaseUserResponse addBaseUser(@RequestBody BaseUserRequest body) {
        return baseUserService.addBaseUser(body);
    }

    @PatchMapping("/{id}")
    public BaseUserResponse editBaseUser(@RequestBody BaseUserRequest body, @PathVariable String id) {
        return baseUserService.editBaseUser(body, id);
    }

    @DeleteMapping("{id}")
    public void deleteBaseUser(@PathVariable String id) {
        baseUserService.deleteBaseUser(id);
    }
}
