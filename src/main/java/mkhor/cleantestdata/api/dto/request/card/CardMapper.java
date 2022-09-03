package mkhor.cleantestdata.api.dto.request.card;

import mkhor.cleantestdata.utils.ResultSetUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardMapper implements RowMapper<Card> {

    @Override
    public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
        Card card = new Card();

        if (ResultSetUtils.containsColumn(rs, "id")) {
            card.setId(rs.getLong("id"));
        }
        if (ResultSetUtils.containsColumn(rs, "pan")) {
            card.setPan(rs.getString("pan"));
        }
        if (ResultSetUtils.containsColumn(rs, "owner")) {
            card.setOwner(rs.getString("owner"));
        }
        if (ResultSetUtils.containsColumn(rs, "date_issued")) {
            card.setDateIssued(rs.getString("date_issued"));
        }
        if (ResultSetUtils.containsColumn(rs, "card_product")) {
            card.setCardProduct(rs.getString("card_product"));
        }

        return card;
    }
}
