package enpalmer.intellimem.domain.dummy;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyGenerator {
    private final DummyService dummyService;

    @PostConstruct
    private void generateDummy() {
        dummyService.initHost();
        dummyService.initTodo();
    }
}