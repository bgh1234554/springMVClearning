package hello.servletdemo.web.springmvc.v3;

import hello.servletdemo.domain.member.Member;
import hello.servletdemo.domain.member.MemberRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
실무에서 실제로 사용하는 방식
Model 도입, ViewName 직접 반환, ModelView 반환할 필요가 없다.
@RequestParam 사용, @RequestMapping 세분화
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //그냥 @ReqeustMapping으로 하면 HTTP 메서드에 상관없이 호출된다.
    //그런 상황을 방지할 수 있다.
    @GetMapping("/new-form")
    public String newForm(){
        return "new-form";
    }

    //모델을 파라미터로 받을 수 있다.
    //HTTP 요청 파라미터를 @RequestParam으로 받을 수 있다.
    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model){
        //이제 getParameter가 필요 없다. 매개변수에서 알아서 해준다.

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member",member);
        return "save-result";
    }

    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }
}
