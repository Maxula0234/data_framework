package mkhor.cleantestdata.db.dao.account;

import mkhor.cleantestdata.api.dto.Result;
import mkhor.cleantestdata.api.dto.request.account.Account;

import java.util.List;

public interface AccountDao {

    List<Account> getAllAccounts();

    Account getAccount(long idAccount);

    Account getAccount(String accountNumber);

    Result deleteAccount(long idAccount);

    Account addAccount(Account account);

    Account updateAccount(long idAccount,Account account);
}
