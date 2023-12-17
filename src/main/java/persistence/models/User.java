package persistence.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "AspNetUsers", schema = "public")

public class User implements Serializable {

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "NormalizedUserName")
    private String normalizedUserName;

    @Column(name = "Email")
    private String email;

    @Column(name = "NormalizedEmail")
    private String normalizedEmail;

    @Column(name = "emailConfirmed")
    private boolean emailConfirmed;

    @Column(name = "PasswordHash")
    private String passwordHash;

    @Column(name = "SecurityStamp")
    private String securityStamp;

    @Column(name = "ConcurrencyStamp")
    private String concurrencyStamp;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "PhoneNumberConfirmed")
    private boolean phoneNumberConfirmed;

    @Column(name = "TwoFactorEnabled")
    private boolean twoFactorEnabled;

    @Column(name = "LockoutEnd")
    private String lockoutEnd;

    @Column(name = "LockoutEnabled")
    private boolean lockoutEndEnabled;

    @Column(name = "AccessFailedCount")
    private int accessFailedCount;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;
}