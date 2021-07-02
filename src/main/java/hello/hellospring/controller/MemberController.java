package hello.hellospring.controller;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
//화면을 붙인다.
//member controller가 member service를 통해 회원가입하고 조회할 수 있어야함
//그런거를 의존관계가 있다고 표현

//spring container가 있음
//container에 memberContoller 객체를 생성해서 넣어 두고 spring에서 관리함
//
@Controller
public class MemberController {

    //spring container에서 받아와서 사용하도록 변경해야함
    //하나만 생성해서 공유하는 것이 좋다.
    //그래서 new 보다 container에 등록해서 사용하는 것이 좋다
    private MemberService memberService;

    //생성자를 오출할 때 autowired가 있으면
    //spring이 spring container의 memberService와 연결함
    //memberService를 찾을 수 없어서 오류 발생 -> service, repository 어노테이션 사용
    //spring bean에 등록된 memberservice 객체를 넣어줌 (dependency Injection)
    //conponent 스캔과 의존관계 설정 방법
    //왠만한 것은 spring bean에 등록해서 사용하는 것이 좋다
//    @Autowired
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    } // 생성자 주입

//    @Autowired private MemberService memberService; // 필드 주입 별로 좋지 않다.

    //setter주입
    // 그러나 아무 개발자나 호출 가능
    //권장하지 않음
    @Autowired
    public  void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public  String creatForm(){
        return "members/createMemberForm";
    }
    //Get 방식
    //url을 직접 넘김
    //회원가입 폼으로 이동
    //template의 createMemberForm 실행

    //post 방식
    //주로 form에 넣어서 전달할 때 사용
    //(get은 주로 조회할 때 사용)
    //create 메서드 실행
    //setName으로 이름 저장
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 홈 화면으로 보낸다
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
    //model에 key member와 value 저장
    //html에서 값을 가져와서 값을 출력한다..
    //서버를 내렸다가 올리면 데이터가 삭제된다.
    //DB를 이용해야함

    // h2 설치 및 실행
    // home 디렉토리에 h2.mv.db 파일 생성
    // 파일로 접근시 동시 접근에서 충돌 발생
    // 이후로는 소켓을 통해 접근해야한다. jdbc:h2:tcp://localhost/~/test


}

