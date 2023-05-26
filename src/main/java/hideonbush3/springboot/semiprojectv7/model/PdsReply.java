package hideonbush3.springboot.semiprojectv7.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Setter
@Getter
@Table(name = "pdsreply")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PdsReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rpno;

    @NotBlank(message = "댓글은 필수항목 입니다")
    private String reply;

    @NotBlank(message = "작성자는 필수항목 입니다")
    private String userid;

    private Long pno;
    private Long refno;

    @Column(insertable = false, updatable = false)
    @CreatedDate
    private LocalDateTime regdate;

}
