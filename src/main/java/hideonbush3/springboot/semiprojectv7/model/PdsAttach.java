package hideonbush3.springboot.semiprojectv7.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "pdsattach")
@Entity
@Data
public class PdsAttach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pano;

    private String fname;
    private String ftype;
    private String fsize;
    @Column(insertable = false, updatable = false)
    private Integer fdown;
    private Integer pno;
}
