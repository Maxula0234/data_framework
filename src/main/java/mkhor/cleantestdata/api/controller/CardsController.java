package mkhor.cleantestdata.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mkhor.cleantestdata.api.dto.Result;
import mkhor.cleantestdata.api.dto.request.card.Card;
import mkhor.cleantestdata.api.service.cards.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${application.endpoint.cards}")
@Tag(name = "Cards", description = "Сервис для взаимодействия с тестовыми картами")
public class CardsController extends BaseController {

    @Autowired
    private CardsService cardsService;

    @GetMapping("{idCard}")
    @Operation(
            summary = "Получить карту по идентификатору",
            description = "Позволяет получить карту клиента"
    )
    public ResponseEntity<Card> getCard(@PathVariable long idCard) {
        Card card = cardsService.getCard(idCard);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", UUID.randomUUID().toString());
        return new ResponseEntity<>(card, httpHeaders, HttpStatus.OK);
    }

    @GetMapping()
    @Operation(
            summary = "Получить все карты",
            description = "Позволяет получить карты, сохраненные в базе"
    )
    public List<Card> getCards(@RequestParam(required = false) String pan, @RequestParam(required = false) String cardProduct) {
        List<Card> allCards = cardsService.getCards();

        if (pan != null && cardProduct != null) {
            return allCards.stream()
                    .filter(f -> f.getPan().equalsIgnoreCase(pan))
                    .filter(f -> f.getCardProduct().equalsIgnoreCase(cardProduct))
                    .collect(Collectors.toList());
        } else if (pan != null) {
            return allCards.stream()
                    .filter(f -> f.getPan().equalsIgnoreCase(pan))
                    .collect(Collectors.toList());
        } else if (cardProduct != null) {
            return allCards.stream()
                    .filter(f -> f.getCardProduct().equalsIgnoreCase(cardProduct))
                    .collect(Collectors.toList());
        } else {
            return allCards;
        }
    }

    @PostMapping()
    @Operation(
            summary = "Добавить карту в базу",
            description = "Позволяет добавить карту в базу данных"

    )
    public Card addCard(@RequestBody Card card) {
        return cardsService.addCard(card);
    }

    @PostMapping("{idCard}")
    @Operation(
            summary = "Обновить карту"
    )
    public Card updateCard(@RequestBody Card card, @PathVariable long idCard) {
        return cardsService.updateCard(idCard, card);
    }

    @DeleteMapping("{idCard}")
    @Operation(
            summary = "Удалить карту"
    )
    public void deleteCard(@PathVariable long idCard) {
        cardsService.deleteCard(idCard);
    }

    @PatchMapping(value = "{idCard}")
    @Operation(summary = "Зарезервировать карту")
    public Result reserveCard(@PathVariable long idCard) {
        boolean b = cardsService.reservedCard(idCard);
        if (b) {
            return Result.builder()
                    .message("Карта " + idCard + " зарезервирована")
                    .httpStatus(HttpStatus.OK)
                    .build();
        } else {
            return Result.builder()
                    .message("Карта " + idCard + " не зарезервирована")
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
