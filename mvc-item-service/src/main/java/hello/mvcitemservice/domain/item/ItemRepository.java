package hello.mvcitemservice.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

}
/*
예제에서는 Map을 이용해 수동으로 Repository를 구현했는데,
이때는 HashMap 대신 ConcurrentHashMap, Long 대신 AtomicLong 등을 사용해
스레드가 한 변수에 동시에 접근할 때 발생할 수 있는 상황에 대해 대처할 수 있도록 하는 것이 좋다.

 */
