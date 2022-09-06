package mkhor.cleantestdata.api.service.accounts;

import mkhor.cleantestdata.api.dto.Result;
import mkhor.cleantestdata.api.dto.request.account.Account;
import mkhor.cleantestdata.db.dao.account.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    public AccountDao accountDao;

    @Override
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @Override
    public Account getAccount(long idAccount) {
        return accountDao.getAccount(idAccount);
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountDao.getAccount(accountNumber);
    }

    @Override
    public Result deleteAccount(long idAccount) {
        return accountDao.deleteAccount(idAccount);
    }

    @Override
    public Account addAccount(Account account) {
        return accountDao.addAccount(account);
    }

    @Override
    public Account updateAccount(long idAccount, Account account) {
        return accountDao.updateAccount(idAccount, account);
    }
}
