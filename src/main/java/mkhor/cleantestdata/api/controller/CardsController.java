package mkhor.cleantestdata.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mkhor.cleantestdata.api.dto.request.card.Card;
import mkhor.cleantestdata.api.service.cards.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("cards")
@Tag(name = "Cards", description = "Сервис для взаимодействия с тестовыми картами")
public class CardsController {

    @Autowired
    private CardsService cardsService;

    @GetMapping("{idCard}")
    @Operation(
            summary = "Получить карту по идентификатору",
            description = "Позволяет получить карту клиента"
    )
    public Card getCard(@PathVariable long idCard) {
        return cardsService.getCard(idCard);
    }

    @GetMapping()
    @Operation(
            summary = "Получить все карты",
            description = "Позволяет получить карты, сохраненные в базе"
    )
    public List<Card> getAllCards() {
        return cardsService.getCards();
    }
}
