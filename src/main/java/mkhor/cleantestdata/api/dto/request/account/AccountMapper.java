package mkhor.cleantestdata.api.dto.request.account;

import mkhor.cleantestdata.utils.ResultSetUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();

        if (ResultSetUtils.containsColumn(rs, "id")) {
            account.setID(rs.getLong("id"));
        }
        if (ResultSetUtils.containsColumn(rs, "number")) {
            account.setNumber(rs.getString("number"));
        }
        if (ResultSetUtils.containsColumn(rs, "owner")) {
            account.setOwner(rs.getString("owner"));
        }
        if (ResultSetUtils.containsColumn(rs, "owner_id")) {
            account.setOwner_id(rs.getLong("owner_id"));
        }
        if (ResultSetUtils.containsColumn(rs, "reserved")) {
            account.setReserved(rs.getBoolean("reserved"));
        }
        if (ResultSetUtils.containsColumn(rs, "amount")) {
            account.setAmount(rs.getFloat("amount"));
        }
        if (ResultSetUtils.containsColumn(rs, "date_issued")) {
            account.setDate_create(rs.getString("date_issued"));
        }
        if (ResultSetUtils.containsColumn(rs, "account_product")) {
            account.setAccount_product(rs.getString("account_product"));
        }

        return account;
    }
}
