package mkhor.cleantestdata.api.service.cards;

import mkhor.cleantestdata.api.dto.request.card.Card;

import java.util.List;

public interface CardsService {

    Card getCard(long idCard);

    List<Card> getCards();

    void updateCard(Card card);

    boolean reservedCard(Card card);

    Card addCard(Card card);
}
