package mkhor.cleantestdata.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mkhor.cleantestdata.api.dto.request.card.Card;
import mkhor.cleantestdata.api.service.cards.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("cards")
@Tag(name = "Cards", description = "Сервис для взаимодействия с тестовыми картами")
public class CardsController extends BaseController {

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

    @PostMapping()
    @Operation(
            summary = "Добавить карту в базу",
            description = "Позволяет добавить карту в базу данных"

    )
    public Card addCard(@RequestBody Card card) {
        return cardsService.addCard(card);
    }

    @PatchMapping("{idCard}")
    @Operation(
            summary = "Обновить карту"
    )
    public Card updateCard(@RequestBody Card card, @PathVariable long idCard) {
        return cardsService.updateCard(idCard, card);
    }
}
