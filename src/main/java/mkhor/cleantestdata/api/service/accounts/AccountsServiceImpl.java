package mkhor.cleantestdata.api.service.accounts;

import mkhor.cleantestdata.api.dto.Result;
import mkhor.cleantestdata.api.dto.request.account.Account;
import mkhor.cleantestdata.api.service.BaseService;
import mkhor.cleantestdata.db.dao.account.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsServiceImpl extends BaseService implements AccountsService {

    @Autowired
    public AccountDao accountDao;

    @Override
    public List<Account> getAllAccounts() {
        logger.info("Получим все счета");
        return accountDao.getAllAccounts();
    }

    @Override
    public Account getAccount(long idAccount) {
        logger.info("Ищем счет по идентификатору - " + idAccount);
        return accountDao.getAccount(idAccount);
    }

    @Override
    public Account getAccount(String accountNumber) {
        logger.info("Ищем счет по номеру счета - " + accountNumber);
        return accountDao.getAccount(accountNumber);
    }

    @Override
    public Result deleteAccount(long idAccount) {
        logger.info("Удалим счет, ид - " + idAccount);
        return accountDao.deleteAccount(idAccount);
    }

    @Override
    public Account addAccount(Account account) {
        logger.info("Добавим счет");
        return accountDao.addAccount(account);
    }

    @Override
    public Account updateAccount(long idAccount, Account account) {
        logger.info("Обновим счет - " + idAccount);
        return accountDao.updateAccount(idAccount, account);
    }
}
