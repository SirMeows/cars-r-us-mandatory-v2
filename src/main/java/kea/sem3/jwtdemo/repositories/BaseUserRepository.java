package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseUserRepository extends JpaRepository<BaseUser,String> {
}
