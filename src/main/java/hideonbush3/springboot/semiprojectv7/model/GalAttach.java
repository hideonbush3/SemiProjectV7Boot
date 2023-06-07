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
public class GalAttach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gano;

    private String fname;
    private String fsize;

    private Integer gno;
}




