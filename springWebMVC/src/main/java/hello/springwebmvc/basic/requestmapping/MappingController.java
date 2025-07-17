package hello.springwebmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log =  LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello-basic")
    public String helloBasic(){
        log.info("hellobasic");
        return "ok";
    }
    //그냥 @RequestMapping은 메서드에 무관하게 호출된다.
    //이렇게 메서드를 지정할 수 있지만...
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    //GET, POST, PUT, DELETE, PATCH 지원
    //이렇게 적는 것이 훨씬 편리하다.
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    //경로 변수 사용하기
    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable String userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}", data);
        return "ok";
        //@PathVariable 을 사용하면 매칭 되는 부분을 편리하게 조회할 수 있다.
    }

    //다중 경로 변수 사용하기
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId){
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    //특정 파라미터 조건 매핑 - 잘 사용하지는 않는다
    //쿼리 파라미터에 특정 정보가 있으면 호출된다.
    //여러 가지가 다 가능하다.
    //실행조건 - http://localhost:8080/mappping-param?mode=debug
    @GetMapping(value="/mapping-param",params = "mode=debug")
    public String mappingParam(){
        log.info("mapping-param");
        return "ok";
    }

    //특정 헤더 조건 매핑 - POSTMAN으로 테스트해야 한다.
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    //미디어 타입 조건 매핑 -  Content-Type 헤더를 기반으로 미디어 타입으로 매핑
    //맞지 않으면 HTTP 415 상태코드(Unsupported Media Type)을 반환
    //"application/json"
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    //HTTP 요청 Accept, produce - HTTP 요청의 Accept 헤더를 기반으로 미디어 타입으로 매핑
    //맞지 않으면 HTTP 406 상태코드(Not Acceptable)을 반환
    //컨트롤러가 생산해내는 컨텐트 타입
    //"text/html" 보단 스프링에서 사용하는 static 값이 나을 수 있다.
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}