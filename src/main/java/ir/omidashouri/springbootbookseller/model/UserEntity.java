package ir.omidashouri.springbootbookseller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TBL_USERS")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME",length = 100)
    private String username;

    @Column(name = "PASSWORD",length = 100)
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @Transient
    private String token;
}

