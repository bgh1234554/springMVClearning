package hello.springwebmvc.basic.request;

import hello.springwebmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

//스프링으로 요청 파라미터를 조회하는 방법

@Slf4j
@Controller
public class RequestParamController {

    /**
     * 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view 조회X
     */
    //V1 - 단순히 HttpServletRequest가 제공하는 방식으로 요청 파라미터 조회
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    /**
     * @RequestParam 사용
     * - 파라미터 이름으로 바인딩
     * @ResponseBody 추가
     * - View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
     */
    @RequestMapping("/request-param-v2")
    @ResponseBody //뷰 이름을 찾지 않도록 하기 위해
    public String requestParamV2(@RequestParam("username") String username, @RequestParam("age") int memberAge){
        log.info("username={}, age={}", username, memberAge);
        return "ok";
    }

    /*
    V3부터 어노테이션에 이름이 없다. -parameters 옵션 필요

    해결 방안1(권장)
    애노테이션에 이름을 생략하지 않고 다음과 같이 이름을 항상 적어준다. 이 방법을 권장한다.
    @RequestParam("username") String username
    @PathVariable("userId") String userId
     */
    /**
     * @RequestParam 사용
     * HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requstParamV3(@RequestParam String username, @RequestParam int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam 사용
     * String, int 등의 단순 타입이면 @RequestParam 도 생략 가능
     * 아예 어노테이션도 없어서 너무 과하다는 느낌
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //파라미터 필수 여부 - requestParamRequired
    /**
     * @RequestParam.required
     * /request-param-required -> username이 없으므로 예외
     *
     * 주의!
     * /request-param-required?username= -> <b>빈문자로 통과</b>
     *
     * 주의!
     * /request-param-required
     * int age -> null을 int에 입력하는 것은 불가능, 따라서 Integer 변경해야 함(또는 다음에 나오는
    defaultValue 사용)
     */
//    @ResponseBody
//    @RequestMapping("/request-param-required")
//    public String requestParamRequired(@RequestParam(required = true) String username,
//                                       @RequestParam(required = false) int age) {
//        log.info("username={}, age={}", username, age);
//        return "ok";
//    }
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {
        //Integer는 객체라서 null을 넣을 수 있다.
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam
     * - defaultValue 사용
     *
     * 참고: defaultValue는 빈 문자의 경우에도 적용
     * /request-param-default?username=
     *
     * 파라미터에 값이 없는 경우 defaultValue 를 사용하면 기본 값을 적용할 수 있다.
     * 이미 기본 값이 있기 때문에 required 는 의미가 없다
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ){
        //사실 defaultValue가 있으면 required=false가 필요가 없다.
        //빈문자인 경우에도 defaultValue가 적용된다.
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //파라미터를 Map 또는 MultiValueMap으로 조회하기
    /**
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     *
     * 파라미터의 값이 1개가 확실하다면 Map 을 사용해도 되지만, 그렇지 않다면 MultiValueMap 을 사용하자.
     * 어차피 보통 파라미터의 값은 하나로 정한다. 두개를 쓸 일은 거의 없다
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    //요청 파라미터를 받아 필요한 객체에 데이터를 담아 다시 보낼 때,
    //@ModelAttribute를 사용할 수 있다.
    //
    //@ModelAttribute 는 생략할 수 있다.
    //그런데 @RequestParam 도 생략할 수 있으니 혼란이 발생할 수 있다.
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        //log.info("HelloData={}",helloData);
        //@Data에서 자동으로 toString 메서드를 만들어준다.
        return "ok";
    }
    /*
    요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다.
    그리고 해당 프로퍼티의 setter를 호출해서, 파라미터의 값을 입력(바인딩) 한다.
     */

    /**
     * @ModelAttribute 생략 가능
     * String, int 같은 단순 타입 = @RequestParam
     * argument resolver 로 지정해둔 타입 외 = @ModelAttribute
     * (추후에 argument resolver 관련 학습 예정)
     *
     * 개인적인 생각으로는 굳이 생략해야 하나 싶다...
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(),
                helloData.getAge());
        return "ok";
    }
}
