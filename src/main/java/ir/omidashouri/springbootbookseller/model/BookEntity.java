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
@Table(name = "TBL_BOOKS")
public class BookEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE",length = 100)
    private String title;

    @Column(name = "DESCRIPTION",length = 100)
    private String description;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;
}

