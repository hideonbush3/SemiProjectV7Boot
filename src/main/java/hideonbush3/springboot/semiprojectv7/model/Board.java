package hideonbush3.springboot.semiprojectv7.model;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOARD")
@Entity
@Getter
@Setter
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BNO")
    private Long bno;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "USERID")
    private String userid;

    @Column(insertable = false, updatable = false, name = "THUMBS")
    private Integer thumbs;

    @Column(insertable = false, updatable = false, name = "VIEWS")
    private Integer views;
    @Column(name = "CONTENT")
    private String content;

    @CreatedDate
    @Column(insertable = false, updatable = false, name =  "REGDATE")
    private LocalDateTime regdate;
}