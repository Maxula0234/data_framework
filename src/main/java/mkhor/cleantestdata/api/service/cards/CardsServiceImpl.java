package mkhor.cleantestdata.api.service.cards;

import mkhor.cleantestdata.api.dto.request.card.Card;
import mkhor.cleantestdata.api.exception.CardNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardsServiceImpl implements CardsService {

    List<Card> allCard = new ArrayList<>();

    @Override
    public Card getCard(long idCard) {
        return allCard.stream().filter(card -> card.getId() == idCard).findFirst().orElseThrow(() -> new CardNotFoundException("Card not found, id - " + idCard));
    }

    @Override
    public List<Card> getCards() {
        return allCard;
    }

    @Override
    public void updateCard(Card cardUpdate) {
        Card cardFromBd = allCard.stream()
                .filter(c -> c.getId() == cardUpdate.getId())
                .findFirst()
                .orElseThrow(() -> new CardNotFoundException("Карта не найдена"));

        if (cardFromBd.getId() != cardFromBd.getId())
            cardFromBd.setId(cardFromBd.getId());
        if (!cardFromBd.getCardProduct().equalsIgnoreCase(cardUpdate.getCardProduct()))
            cardFromBd.setCardProduct(cardUpdate.getCardProduct());
        if (cardFromBd.getDateIssued() != cardUpdate.getDateIssued())
            cardFromBd.setDateIssued(cardUpdate.getDateIssued());
        if (!cardFromBd.getOwner().equalsIgnoreCase(cardUpdate.getOwner()))
            cardFromBd.setOwner(cardUpdate.getOwner());

        addCard(cardFromBd);
    }

    @Override
    public boolean reservedCard(Card card) {
        return false;
    }

    @Override
    public Card addCard(Card card) {
        Optional<Card> first = allCard.stream()
                .filter(c -> c.getId() == card.getId())
                .findFirst();
        if (first.isPresent()) {
            return card;
        } else {
            allCard.add(card);
            return card;
        }
    }
}
