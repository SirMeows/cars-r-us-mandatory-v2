package kea.sem3.jwtdemo.entity;

import kea.sem3.jwtdemo.dto.BaseUserRequest;
import kea.sem3.jwtdemo.security.UserWithPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**********************************************************************************/
/********** IMPORTANT! DON'T CHANGE ANYTHING IN THIS FILE *************************/
/***** EXTEND IT TO CREATE SPECIALIZED USERS (LIKE Members) ***********************/
/********************************************************************************/

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "user")
public class BaseUser implements UserWithPassword {

   @Id
   private String username;

   @Email
   @Column(nullable = false, unique = true,length = 50)
   private String email;

   // 72 == Max length of a bcrypt encoded password
   // https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html
   @Column(nullable = false, length = 72)
   private String password;

   boolean enabled;

   @Enumerated(EnumType.STRING)
   @Column(columnDefinition = "ENUM('USER','ADMIN')")
   @ElementCollection(fetch = FetchType.EAGER)
   @CollectionTable(name="user_role")
   List<Role> roles = new ArrayList<>();

   @Column(name="date_created")
   @CreationTimestamp
   private LocalDateTime dateCreated;

   @Column(name = "date_edited")
   @UpdateTimestamp
   private LocalDateTime dateEdited;

//   private boolean isActive; Potentially add this to keep track of active users

   public BaseUser(String username, String email, String password) {
      this.username = username;
      this.email = email;
      this.password = pwEncoder.encode(password);
      this.enabled = true;
   }

   public BaseUser(BaseUserRequest body) {
      this.username = body.getUsername();
      this.email = body.getEmail();
      this.password = body.getPassword();
   }

   @Override
   public void setPassword(String password) {
      this.password = pwEncoder.encode(password);
   }

   @Override
   public List<Role> getRoles() {
      return roles;
   }

   @Override
   public void addRole(Role role){
      roles.add(role);
   }

}
