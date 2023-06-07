package hideonbush3.springboot.semiprojectv7.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "gallery")
@Entity
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;

    @NotBlank(message = "제목은 필수 입력항목입니다!!")
    private String title;
    private String userid;
    @Column(insertable = false, updatable = false)
    private Integer thumbs;
    @Column(insertable = false, updatable = false)
    private Integer views;

    @NotBlank(message = "본문은 필수 입력항목입니다!!")
    private String content;
    private String uuid;

    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime regdate;

}
