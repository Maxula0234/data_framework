package mkhor.cleantestdata.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mkhor.cleantestdata.api.dto.Result;
import mkhor.cleantestdata.api.dto.request.account.Account;
import mkhor.cleantestdata.api.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.account}")
@Tag(name = "Accounts", description = "Сервис для взаимодействия с тестовыми счетами")
public class AccountsController extends BaseController {

    @Autowired
    public AccountsService accountsService;

    @GetMapping()
    @Operation(
            summary = "Получить все счета",
            description = "Метод для получения всех сохраненных счетов в базе данных"
    )
    public List<Account> getAllAccounts() {
        return accountsService.getAllAccounts();
    }

    @PostMapping()
    @Operation(
            summary = "Добавить счет в базу",
            description = "Метод для добавления счета в базу"
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Account addAccount(@RequestBody Account account) {
        return accountsService.addAccount(account);
    }

    @GetMapping("{idAccount}")
    @Operation(
            summary = "Получить счет по идентификатору",
            description = "Метод для получения счето по идентификатору"
    )
    public Account getAccount(@PathVariable long idAccount) {
        return accountsService.getAccount(idAccount);
    }

    @DeleteMapping("{idAccount}")
    @Operation(
            summary = "Удалить счет по идентификатору",
            description = "Метод позволяет удалить счет из БД"
    )
    public Result deleteAccount(@PathVariable long idAccount) {
        return accountsService.deleteAccount(idAccount);
    }

    @PatchMapping("{idAccount}")
    @Operation(
            summary = "Обновить счет"
    )
    public Account updateAccount(@PathVariable long idAccount, @RequestBody Account account) {
        return accountsService.updateAccount(idAccount, account);
    }
}
