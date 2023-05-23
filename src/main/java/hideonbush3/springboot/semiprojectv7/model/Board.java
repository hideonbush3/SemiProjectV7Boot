package hideonbush3.springboot.semiprojectv7.model;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    // 문자열 길이 0인 것과 공백 문자열도 체크
    @NotBlank(message = "제목은 필수 입력항목입니다!!")
    private String title;

    @NotBlank(message = "아이디는 필수 입력항목입니다!!")
    private String userid;

    @Column(insertable = false, updatable = false)
    private Integer thumbs;

    @Column(insertable = false, updatable = false)
    private Integer views;

    @NotBlank(message = "본문은 필수 입력항목입니다!!")
    private String content;

    @CreatedDate
    @Column(insertable = false, updatable = false, name =  "REGDATE")
    private LocalDateTime regdate;
}