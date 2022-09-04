package mkhor.cleantestdata.api.service.accounts;

import mkhor.cleantestdata.api.dto.Result;
import mkhor.cleantestdata.api.dto.request.account.Account;

import java.util.List;

public interface AccountsService {

    List<Account> getAllAccounts();

    Account getAccount(long idAccount);

    Account getAccount(String accountNumber);

    Result deleteAccount(long idAccount);

    Account addAccount(Account account);
}
