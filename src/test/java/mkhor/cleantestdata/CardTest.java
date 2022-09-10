package mkhor.cleantestdata;

import mkhor.cleantestdata.api.dto.request.card.Card;
import mkhor.cleantestdata.api.service.cards.CardsService;
import mkhor.cleantestdata.utils.DateUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class CardTest extends BaseTest {

    @Autowired
    public CardsService cardsService;

    @Test
    @DisplayName("Проверка получения карты по идентификатору")
    void getCardTest() {
        List<Card> cards = cardsService.getCards();
        Card card = cardsService.getCard(cards.get(0).getId());

        assertAll(
                () -> assertThat(card.getPan()).isNotNull(),
                () -> assertThat(card.getCardProduct()).isNotNull(),
                () -> assertThat(card.getId()).isNotNull(),
                () -> assertThat(card.getDateIssued()).isNotNull(),
                () -> assertThat(card.getOwner()).isNotNull()
        );

        logger.info("Карта получена");
    }

    @Test
    @DisplayName("Проверка получения карты по идентификатору")
    void getCardsTest() {
        List<Card> cards = cardsService.getCards();

        assertThat(cards).allSatisfy(card -> assertAll(
                () -> assertThat(card.getPan()).isNotNull(),
                () -> assertThat(card.getCardProduct()).isNotNull(),
                () -> assertThat(card.getId()).isNotNull(),
                () -> assertThat(card.getDateIssued()).isNotNull(),
                () -> assertThat(card.getOwner()).isNotNull()
        ));
        logger.info("Карты получены, в кол-ве - " + cards.size());
    }


    @Test
    @DisplayName("Проверка получения карты по идентификатору")
    void updateCardsTest() {
        List<Card> cards = cardsService.getCards();
        Card card = cards.get(0);
        card.setOwner(faker.name().firstName() + " " + faker.name().lastName());

        Card updatedCard = cardsService.updateCard(card.getId(), card);

        assertThat(updatedCard).isEqualTo(card);
    }

    @Test
    @DisplayName("Добавить карту")
    void addNewCard() {
        addCardPrecondition();
    }

    private Card addCardPrecondition() {
        Card card = Card.builder()
                .cardProduct("МИР")
                .dateIssued(DateUtils.parseDateToString(faker.date().birthday()))
                .pan("4444111" + faker.random().nextInt(1111, 9999) + "32222")
                .owner(faker.name().firstName() + " " + faker.name().lastName())
                .reservedCard(false)
                .build();

        Card newCard = cardsService.addCard(card);

        assertAll(
                () -> assertThat(newCard.getPan()).isEqualToIgnoringCase(card.getPan()),
                () -> assertThat(newCard.getCardProduct()).isEqualToIgnoringCase(card.getCardProduct()),
                () -> assertThat(newCard.getId()).isNotNull(),
                () -> assertThat(newCard.getDateIssued()).isEqualToIgnoringCase(card.getDateIssued()),
                () -> assertThat(newCard.getOwner()).isEqualToIgnoringCase(card.getOwner())
        );
        return newCard;
    }

    @Test
    @DisplayName("Удалить карту")
    void deleteCard() {
        Card card = addCardPrecondition();
        cardsService.deleteCard(card.getId());

        List<Card> cards = cardsService.getCards();
        Optional<Card> cardForCheck = cards.stream()
                .filter(c -> c.getId() == card.getId())
                .findFirst();

        assertThat(cardForCheck).isNotPresent();
    }

    @Test
    @DisplayName("Зарезервировать карту")
    void reservedCardTest() {
        List<Card> cards = cardsService.getCards();
        Card card = cards.get(0);
        cardsService.reservedCard(card.getId());

        Card cardForCheck = cardsService.getCard(card.getId());

        assertThat(cardForCheck.isReservedCard()).isTrue();
    }
}
