package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: Check whether this repo is needed. Probably overlaps with UserRepositoryb in Security

public interface BaseUserRepository extends JpaRepository<BaseUser,String> {
}
