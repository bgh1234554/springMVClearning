package hello.springwebmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    //ModelAndView 객체 직접 반환
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mv = new ModelAndView("response/hello")
                .addObject("data","hello");
        return mv;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data","hello");
        return "response/hello";
    }

    //void를 반환하는 경우 - 권장하지 않음.
    //HTTP 바디를 처리하는 파라미터가 없으면, 요청URL을 참고해서 논리 뷰로 사용
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data","hello");
    }
}
