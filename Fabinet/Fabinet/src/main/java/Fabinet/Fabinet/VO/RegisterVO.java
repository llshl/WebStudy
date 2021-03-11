package Fabinet.Fabinet.VO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterVO {

    //AJAX측의 변수명과 같아야 받아진다
    private String u_name;
    private String u_id;
    private String u_pw;
    private String u_pw2;
    private String u_tel;
    private String u_email;
}
