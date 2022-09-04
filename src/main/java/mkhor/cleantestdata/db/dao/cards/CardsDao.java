package mkhor.cleantestdata.db.dao.cards;

import mkhor.cleantestdata.api.dto.request.card.Card;

import java.util.List;

public interface CardsDao {

    Card getCard(long idCard);

    Card getCard(String pan);

    List<Card> getCards();

    Card updateCard(long idCard, Card card);

    boolean reservedCard(Card card);

    Card addCard(Card card);

    void deleteCard(long idCard);
}
