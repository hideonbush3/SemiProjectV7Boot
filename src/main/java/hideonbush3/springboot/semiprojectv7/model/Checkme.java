package hideonbush3.springboot.semiprojectv7.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class Checkme {
    @NotBlank(message = "필수항목")
    private String name;
    @NotBlank(message = "필수항목")
    private String jumin1;
    @NotBlank(message = "필수항목")
    private String jumin2;
}
