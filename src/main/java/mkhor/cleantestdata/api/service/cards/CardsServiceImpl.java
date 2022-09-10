package mkhor.cleantestdata.api.service.cards;

import mkhor.cleantestdata.api.dto.request.card.Card;
import mkhor.cleantestdata.api.service.BaseService;
import mkhor.cleantestdata.db.dao.cards.CardsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardsServiceImpl extends BaseService implements CardsService {
    @Autowired
    private CardsDao cardsDao;

    @Override
    public Card getCard(long idCard) {
        return cardsDao.getCard(idCard);
    }

    @Override
    public List<Card> getCards() {
        return cardsDao.getCards();
    }

    @Override
    public Card updateCard(long idCard, Card cardUpdate) {
        return cardsDao.updateCard(idCard, cardUpdate);
    }

    @Override
    public boolean reservedCard(Card card) {
        return false;
    }

    @Override
    public Card addCard(Card card) {
        return cardsDao.addCard(card);
    }

    @Override
    public void deleteCard(long idCard) {
        cardsDao.deleteCard(idCard);
    }
}
