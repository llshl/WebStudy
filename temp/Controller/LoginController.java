package com.example.BootStrap_AdminTest.Controller;

/*
* 폼으로 이동은 xxxFrom
* 프로시저로 이동은 doxxx
* */


import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.BootStrap_AdminTest.Service.MemberService;
import com.example.BootStrap_AdminTest.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberService memberService;
    BasicAWSCredentials awsCreds = new BasicAWSCredentials("key", "key");
    private final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
        .withRegion(Regions.fromName("ap-northeast-2"))
        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
        .disableChunkedEncoding()
        .build();



    //로그인폼으로
    @GetMapping("/loginForm")
    public String toLoginForm(Model model){
        model.addAttribute("loginForm", new LoginForm()); //MemberForm이라는 객체를 보내줌. 여기에 회원정보 담기위해
        log.info("toLoginForm");
        return "login";
    }
    //@@@@로그인할때 필수 입력요소들 강제해야함@@@@
    //로그인하기
    @PostMapping("/doLogin")
    public String doLogin(HttpSession session, HttpServletResponse response, LoginForm form, Model model) throws IOException {
        String msg = "";
        String url = "";
        Member member = memberService.findOne(form.getUserID());  //입력된 id로 DB뒤져서 해당 member가져옴
        String loginResult = memberService.isLoginAvailable(member, form.getUserID(), form.getUserPW());
        log.info("로그인 가능여부 판별");

        //이 로직을 Service로 옮겨야할까?
        if(loginResult.startsWith("S-")){
            session.setAttribute("loginMemberId",form.getUserID()); //세션에 로그인된 회원아이디 저장
            model.addAttribute("isLogined",form.getUserID());
            msg = "로그인 되었습니다";
            url = "index";
        }
        else{
            url = "login";
            msg = "회원정보가 일치하지 않습니다.";
            viewAlert(response,msg);
        }
        return url;
    }

    @GetMapping("/doLogout")
    public String doLogout(HttpSession session, HttpServletResponse response, Model model) throws IOException {
        session.removeAttribute("loginMemberId");   //세션해제
        model.addAttribute("loginForm", new LoginForm());
        String msg = "로그아웃 되었습니다";
        viewAlert(response,msg);
        return "login";
    }

    //View에 로그인 관련 알림창 출력
    private void viewAlert(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('"+msg+"');</script>");//location.href='loginForm'
        out.flush();
    }

    @PostMapping("/imgToDb")
    public String imgToDb(@RequestParam("uploadFile") MultipartFile file) throws IOException, SQLException {
        System.out.println(file);
        System.out.println(file.getContentType());
        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());
        File realFile = new File(file.getOriginalFilename());
        InputStream is = file.getInputStream();
        String bucketName = "lsh-rds-s3";
        String keyName = "test3";   //이부분을 들어오는 사진의 이름으로 바꾸자 - 근데 이 이름을 PK값인 회원ID랑 연관시켜야함
        try{
            s3.putObject(new PutObjectRequest(bucketName,keyName,is, new ObjectMetadata()));
        }catch (AmazonServiceException e){
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        return "index"; //이거 redirect로 해야할듯, 주소창에 이게 남아있다 계속
    }

    @GetMapping("/memberList")
    public String memberList(Model model) {
        List<Member> members = memberService.findMembers(); //컨트롤러단이니 서비스단의 findMember를쓴다(리포지토리단의 findAll을 바로쓰는것이아니라)
        model.addAttribute("members", members); //이걸 넘겨주는것
        return "/memberList";
    }
}

