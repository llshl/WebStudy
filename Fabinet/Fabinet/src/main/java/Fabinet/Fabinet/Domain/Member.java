package Fabinet.Fabinet.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {


    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    @Id
    private String loginId;
    private String loginPassword;
    private String tel;
    private String Email;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Cabinet> cabinets = new ArrayList<>();
}
