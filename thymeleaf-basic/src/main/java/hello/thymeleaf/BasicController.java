package hello.thymeleaf;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data","Hello World");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data","Hello <b>World<b>");
        return "basic/text-unescaped";
    }

    //Spring EL - 스프링이 제공하는 변수 표현식 사용
    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA",10);
        User userB = new User("userB",20);
        List<User> list = new ArrayList<User>();
        list.add(userA);
        list.add(userB);

        Map<String,User> map = new HashMap<String, User>();
        map.put("userA",userA);
        map.put("userB",userB);

        model.addAttribute("user",userA);
        model.addAttribute("users",list);
        model.addAttribute("userMap",map);

        return "basic/variable";
    }

    @Data
    static class User{
        private String username;
        private int age;

        public User(String name,int age){
            this.username =name;
            this.age=age;
        }
    }

    /*
    타임리프는 기본 객체로
    ${#locale}을 제공한다.
    request, response, session, servletContext 등을 사용하기 위해선, 컨트롤러에 직접 변수를 넣어줘야 한다.

    HTTP 요청 파라미터 접근: param
    예) ${param.paramData}
    HTTP 세션 접근: session
    예) ${session.sessionData}
    스프링 빈 접근: @
    예) ${@helloBean.hello('Spring!')}
     */
    @GetMapping("/basic-objects")
    public String basicObjects(Model model, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) {
        session.setAttribute("sessionData","Hello Session");
        model.addAttribute("request",request);
        model.addAttribute("response",response);
        model.addAttribute("servletContext",request.getServletContext());
        return "basic/basic-objects";

    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }
    /*
    타임리프 유틸리티 객체들
        #message : 메시지, 국제화 처리
        #uris : URI 이스케이프 지원
        #dates : java.util.Date 서식 지원
        #calendars : java.util.Calendar 서식 지원
        #temporals : 자바8 날짜 서식 지원
        #numbers : 숫자 서식 지원
        #strings : 문자 관련 편의 기능
        #objects : 객체 관련 기능 제공
        #bools : boolean 관련 기능 제공
        #arrays : 배열 관련 기능 제공
        #lists , #sets , #maps : 컬렉션 관련 기능 제공
        #ids: 아이디 처리 관련 기능 제공

     필요할 때 찾아서 쓰면 된다.

     자바 8에서 추가된 날짜를 사용하기 위한 유틸리티 객체
     #temporals
     */
    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return  "basic/date";
    }

    /*
    타임리프에서 URL은 @ 노테이션을 사용한다.
     */
    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1","data1");
        model.addAttribute("param2","data2");
        return "basic/link";
    }

    /*
    리터럴 - 소스 코드 상에 고정된 값

    오류
    <span th:text="hello world!"></span>
    문자 리터럴은 원칙상 ' 로 감싸야 한다. 중간에 공백이 있어서 하나의 의미있는 토큰으로도 인식되지 않는다.
    <span th:text="'Hello World!'"></span> 이렇게 해야한다.

    리터럴 대체(Literal substitutions)
    <span th:text="|hello ${data}|">
    마지막의 리터럴 대체 문법을 사용하면 마치 템플릿을 사용하는 것 처럼 편리하다.
     */
    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data","Spring!");
        return "basic/literal";
    }

    /*
    연산 - 타임리프의 연산은 자바와 크게 다르지 않다.
    HTML 엔티티를 쓰는 부분만 조심하면 된다.
     */
    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData",null);
        model.addAttribute("data","Spring!");
        return "basic/operation";
    }

    /*
    타임리프에서 속성 만들기
     */
    @GetMapping("/attribute")
    public String attribute(Model model) {
        return "basic/attribute";
    }
    
    /*
    타임리프에서의 반복 - th:each
     */
    @GetMapping("/each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }

    private static void addUsers(Model model) {
        List<User> list = new ArrayList<User>();
        list.add(new User("userA",10));
        list.add(new User("userB",20));
        list.add(new User("userC",30));

        model.addAttribute("users",list);
    }

    /*
    조건부 평가
     */
    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    /*
    주석 추가하는 법
     */
    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }

    /*
    블록
    <th:block> 은 HTML 태그가 아닌 타임리프의 유일한 자체 태그다.

    타임리프의 특성상 HTML 태그안에 속성으로 기능을 정의해서 사용하는데, 위 예처럼 이렇게 사용하기 애매한 경우에
    사용하면 된다. <th:block> 은 렌더링시 제거된다.
     */
    @GetMapping("/block")
    public String block(Model model) {
        addUsers(model);
        return "basic/block";
    }

    /*
    타임리프는 자바스크립트에서 타임리프를 편리하게 사용할 수 있는 자바스크립트 인라인 기능을 제공한다.
    자바스크립트 인라인 기능은 다음과 같이 적용하면 된다.
    <script th:inline="javascript">

    인라인 사용 후 렌더링 결과를 보면 문자타입인 경우 "를 포함해주고,
    자바스크립트에서 문제가 될 수 있는 문자가 포함되어 있으면 이스케이프 처리를 해준다.

    객체
    타임리프의 자바스크립트 인라인 기능을 사용하면 객체를 JSON으로 자동으로 변환해준다.

    var user = [[${user}]];

    인라인 사용 전 var user = BasicController.User(username=userA, age=10);
    인라인 사용 후 var user = {"username":"userA","age":10};
    인라인 사용 전은 객체의 toString()이 호출된 값이다.
    인라인 사용 후는 객체를 JSON으로 변환해준다.
     */
    @GetMapping("/javascript")
    public String javascript(Model model) {
        model.addAttribute("user", new User("userA", 10));
        addUsers(model);
        return "basic/javascript";
    }
}
