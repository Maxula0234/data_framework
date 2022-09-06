package mkhor.cleantestdata.db.dao.account;

import mkhor.cleantestdata.api.dto.Result;
import mkhor.cleantestdata.api.dto.request.account.Account;
import mkhor.cleantestdata.api.dto.request.account.AccountMapper;
import mkhor.cleantestdata.api.exception.AccountIsNotDelete;
import mkhor.cleantestdata.api.exception.AccountNotBeAdd;
import mkhor.cleantestdata.api.exception.AccountNotFound;
import mkhor.cleantestdata.db.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class AccountDaoImpl extends BaseDao implements AccountDao {

    @Autowired
    public AccountDaoImpl(DataSource dataBase) {
        setDataSource(dataBase);
    }

    @Override
    public List<Account> getAllAccounts() {
        String query = "SELECT * FROM ACCOUNTS";
        List<Account> accountList = getJdbcTemplate().query(query, new AccountMapper());

        if (accountList.isEmpty())
            throw new AccountNotFound("Счета не найдены");
        return accountList;
    }

    @Override
    public Account getAccount(long idAccount) {
        String query = String.format("SELECT * FROM ACCOUNTS WHERE ID = %s", idAccount);
        List<Account> accounts = getJdbcTemplate().query(query, new AccountMapper());

        if (accounts.isEmpty())
            throw new AccountNotFound("Счет " + idAccount + " не найден");
        return accounts.get(0);
    }

    @Override
    public Account getAccount(String accountNumber) {
        String query = String.format("SELECT * FROM ACCOUNTS WHERE NUMBER = '%s'", accountNumber);
        List<Account> accounts = getJdbcTemplate().query(query, new AccountMapper());

        if (accounts.isEmpty())
            throw new AccountNotFound("Счет " + accountNumber + " не найден");
        return accounts.get(0);
    }

    @Override
    public Result deleteAccount(long idAccount) {
        String query = String.format("DELETE FROM ACCOUNTS WHERE id = %s", idAccount);

        try {
            Account account = getAccount(idAccount);
            getJdbcTemplate().update(query);

            return Result.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Успешно")
                    .build();

        } catch (DataAccessException e) {
            throw new AccountIsNotDelete("Счет " + idAccount + " не удален");
        }
    }

    @Override
    public Account addAccount(Account account) {
        String query = String.format(
                "INSERT INTO ACCOUNTS " +
                        " (NUMBER,OWNER,OWNER_ID,RESERVED,AMOUNT,DATE_CREATE,ACCOUNT_PRODUCT) " +
                        " VALUES ('%s','%s','%s','%s','%s','%s','%s')",
                account.getNumber(),
                account.getOwner(),
                account.getOwner_id(),
                account.isReserved(),
                account.getAmount(),
                account.getDate_create(),
                account.getAccount_product());

        try {
            getJdbcTemplate().update(query);
        } catch (DataAccessException e) {
            throw new AccountNotBeAdd("Счет не добавлен");
        }
        return getAccount(account.getNumber());
    }

    @Override
    public Account updateAccount(long idAccount, Account account) {

        Account acc = getAccount(idAccount);

        String query = String.format("UPDATE accounts " +
                        " SET " +
                        "number='%s', " +
                        "owner='%s', " +
                        "owner_id='%s', " +
                        "reserved='%s', " +
                        "amount='%s', " +
                        "date_create=cast('%s' as date), " +
                        "account_product='%b' " +
                        "WHERE id = '%s' ",
                account.getNumber(),
                account.getOwner(),
                account.getOwner_id(),
                account.isReserved(),
                account.getAmount(),
                account.getDate_create(),
                account.getAccount_product(),
                idAccount
        );

        getJdbcTemplate().update(query);
        return getAccount(idAccount);
    }

}
