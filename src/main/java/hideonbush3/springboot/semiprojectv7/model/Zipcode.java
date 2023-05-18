package hideonbush3.springboot.semiprojectv7.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ZIPCODE")
public class Zipcode {

    private String zipcode;
    private String sido;
    private String gugun;
    private String dong;
    private String ri;
    private String bunji;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

}
