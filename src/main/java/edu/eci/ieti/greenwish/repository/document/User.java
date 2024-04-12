package edu.eci.ieti.greenwish.repository.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import edu.eci.ieti.greenwish.controller.user.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String passwordHash;
    private int points;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.passwordHash = BCrypt.hashpw(password, "$2a$10$qXKfZmQrQOyvgY/gTnMgHeOcLqZa6u9wPb7s6XUa7/yK6rci60CyC");
        this.points = 0;
    }

    public User(UserDto userDto) {
        this.id = null;
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.passwordHash = BCrypt.hashpw(userDto.getPassword(), "$2a$10$qXKfZmQrQOyvgY/gTnMgHeOcLqZa6u9wPb7s6XUa7/yK6rci60CyC");
        this.points = 0;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

}