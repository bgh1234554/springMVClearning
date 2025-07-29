package hello.mvcitemservice.domain.item;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @AfterEach
    void afterEach(){
        itemRepository.deleteAll();
    }

    @Test
    void save(){
        //given
        Item item = new Item();
        item.setName("test");
        item.setPrice(10000); item.setQuantity(10);
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(savedItem.getId()).get();
        assertEquals(findItem.getId(),savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll(){
        Item item1 = new Item();
        item1.setName("test");
        item1.setPrice(10000); item1.setQuantity(10);
        Item item2 = new Item();
        item2.setName("test2");
        item2.setPrice(20000); item2.setQuantity(10);

        //스프링 데이터 JPA에서는 save로도 업데이트가 된다.
        itemRepository.save(item1); itemRepository.save(item2);

        List<Item> items = itemRepository.findAll();

        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1,item2);
    }

}