package hideonbush3.springboot.semiprojectv7.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "MEMBER")
@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbno;
    private String name;
    private String jumin1;
    private String jumin2;
    private String userid;
    private String passwd;
    private String zipcode;
    private String addr1;
    private String addr2;
    private String email;
    private String phone;

    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime regdate;

}
