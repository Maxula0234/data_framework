package mkhor.cleantestdata.service.cards;

import mkhor.cleantestdata.dto.request.card.Card;

import java.util.List;

public interface CardsService {

    Card getCard(long idCard);

    List<Card> getCards();

    Card updateCard();

    boolean reservedCard(Card card);
}
