package hello.mvcitemservice.domain.item.basic;

import hello.mvcitemservice.domain.item.Item;
import hello.mvcitemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId).get();
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /*
    @ModelAttribute 는 중요한 한가지 기능이 더 있는데,
    바로 모델(Model)에 @ModelAttribute 로 지정한 객체를 자동으로 넣어준다.
     */
//    @PostMapping("/add")
//    public String addItem(@ModelAttribute("item") Item item){
//        itemRepository.save(item);
    //model.addAttribute("item",item); //이걸 자동으로 해준다.
    //Model model 도 두번째 파라미터로 필요가 없다.
//        return "redirect:/basic/item";
//    }

    /**
     *
     * @param item
     * @param redirectAttributes
     * @return
     *
     * form submit 이후에는
     * PRG(Post-Redirect-Get) 패턴을 지키면서 상태를 전달해야 하니 RedirectAttributes를 쓰는 게 좋다.
     */
    @PostMapping("/add")
    public String addItemV2(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("item", savedItem);
        redirectAttributes.addAttribute("status",true);
        return "redirect:/basic/items";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId).get();
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     * @PostConstruct : 해당 빈의 의존관계가 모두 주입되고 나면 초기화 용도로 호출된다
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }
}
