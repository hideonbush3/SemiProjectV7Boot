package hideonbush3.springboot.semiprojectv7.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "galattach")
@Entity
@Data
public class GalAttach{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gano;

    private String fname;
    private String fsize;

    // gallery의 gno와 연결 - 외래키
    private Integer gno;

    @OneToOne(optional = false)  // outer join을 inner join으로!
    @JoinColumn(name="gno", insertable = false, updatable = false)
    private Gallery gallery;

}




