package mkhor.cleantestdata.service.cards;

import mkhor.cleantestdata.CardExpection;
import mkhor.cleantestdata.dto.request.card.Card;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardsServiceImpl implements CardsService {

    List<Card> allCard = new ArrayList<>();

    {
        allCard.add(new Card(1, "4444555566667777", "Max Ironov", LocalDateTime.now(), "Мир"));
        allCard.add(new Card(2, "4444555566668888", "Petr Ionov", LocalDateTime.now(), "Мир"));
        allCard.add(new Card(3, "4444555566669999", "Evgeniy Inov", LocalDateTime.now(), "Мир Акционная"));
        allCard.add(new Card(4, "4444555566661111", "Stepan Irov", LocalDateTime.now(), "Мир Тревел"));
    }

    @Override
    public Card getCard(long idCard) {
        return allCard.stream()
                .filter(card -> card.getId() == idCard)
                .findFirst()
                .orElseThrow(() -> new CardExpection("Card not found, id - " + idCard));
    }

    @Override
    public List<Card> getCards() {
        return allCard;
    }

    @Override
    public Card updateCard() {
        return null;
    }

    @Override
    public boolean reservedCard(Card card) {
        return false;
    }
}
