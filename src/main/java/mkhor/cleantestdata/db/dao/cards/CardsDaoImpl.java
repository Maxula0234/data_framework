package mkhor.cleantestdata.db.dao.cards;

import mkhor.cleantestdata.api.dto.request.card.Card;
import mkhor.cleantestdata.api.dto.request.card.CardMapper;
import mkhor.cleantestdata.api.exception.CardNotBeAdd;
import mkhor.cleantestdata.db.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class CardsDaoImpl extends BaseDao implements CardsDao {

    @Autowired
    public CardsDaoImpl(DataSource dataBase) {
        setDataSource(dataBase);
    }

    @Override
    public Card getCard(long idCard) {
        return getJdbcTemplate()
                .queryForObject(String.format("select * from cards where id = '%s'", idCard), new CardMapper());
    }

    @Override
    public Card getCard(String pan) {
        String query = String.format("select * from cards where pan = '%s'", pan);
        List<Card> result = getJdbcTemplate().query(query, new CardMapper());
        return result.get(0);
    }

    @Override
    public List<Card> getCards() {
        return getJdbcTemplate()
                .query("select * from cards", new CardMapper());
    }

    @Override
    public Card updateCard(long idCard, Card card) {
        String query = String.format("" +
                        "update cards " +
                        "SET " +
                        "pan='%s', " +
                        "owner='%s', " +
                        "date_issued=cast('%s' as date), " +
                        "card_product='%s', " +
                        "reserve = '%b' " +
                        "WHERE id = '%s'",
                card.getPan(),
                card.getOwner(),
                card.getDateIssued(),
                card.getCardProduct(),
                card.isReservedCard(),
                idCard);

        getJdbcTemplate().update(query);
        logger.info("Обновили карту - " + idCard);
        return getCard(idCard);
    }

    @Override
    public boolean reservedCard(Card card) {
        return false;
    }

    @Override
    public Card addCard(Card card) {
        String query = String.format("insert into cards (pan,owner,date_issued,card_product,reserve) " +
                        "VALUES ('%s','%s',cast('%s' as date),'%s',%b)",
                card.getPan(),
                card.getOwner(),
                card.getDateIssued(),
                card.getCardProduct(),
                card.isReservedCard()
        );

        getJdbcTemplate().update(query);

        Card newCard = getCard(card.getPan());
        if (newCard != null) {
            return newCard;
        } else {
            throw new CardNotBeAdd("Карта не добавлена");
        }
    }

    @Override
    public void deleteCard(long idCard) {
        String query = String.format("DELETE FROM CARDS WHERE ID='%s'", idCard);
        getJdbcTemplate().update(query);
    }
}
