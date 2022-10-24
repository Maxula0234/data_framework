package mkhor.cleantestdata;

import mkhor.cleantestdata.api.dto.request.account.Account;
import mkhor.cleantestdata.api.service.accounts.AccountsService;
import mkhor.cleantestdata.utils.DateUtils;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class AccountTest extends BaseTest {

    @Autowired
    public AccountsService accountsService;

    public static Stream<Arguments> getAcc() {
        return Stream.of(Arguments.of("id"), Arguments.of("accountNumber"));
    }

    public Account addAccountPrecondition() {
        var amount = new BigDecimal(String.valueOf(faker.random().nextInt(1111, 9999)));
        var owner = new StringBuilder();
        owner.append(faker.name().firstName()).append(" ").append(faker.name().lastName());

        String product = "МИР";
        String date_create = DateUtils.parseDateToString(faker.date().birthday());
        String number = "40702810" + faker.random().nextInt(1111, 9999) + "60657001";
        long owner_id = faker.number().randomNumber();

        var account = Account.builder().account_product(product).amount(amount).date_create(date_create).number(number).owner(owner.toString()).owner_id(owner_id).reserved(false).build();

        Account acc = accountsService.addAccount(account);

        Account newAccount = accountsService.getAccount(acc.getID());
        assertAll(() -> assertThat(newAccount.getAccount_product()).isEqualToIgnoringCase(product), () -> assertThat(newAccount.getNumber()).isEqualToIgnoringCase(number), () -> assertThat(newAccount.getAmount()).isEqualTo(amount), () -> assertThat(newAccount.getOwner()).isEqualToIgnoringCase(owner), () -> assertThat(newAccount.getOwner_id()).isEqualTo(owner_id), () -> assertThat(newAccount.getDate_create()).isEqualTo(date_create));

        return newAccount;
    }

    @Test
    void addAccount() {
        addAccountPrecondition();
        logger.info("Счет добавлен в базу");
    }

    @Test
    @DisplayName("Проверяем получение всех счетов в базе")
    void getAllAccounts() {
        List<Account> allAccounts = accountsService.getAllAccounts();
        assertThat(allAccounts).allSatisfy(acc -> {
            assertAll(() -> assertThat(acc.getAccount_product()).isNotNull(), () -> assertThat(acc.getNumber()).isNotNull(), () -> assertThat(acc.getAmount()).isNotNull(), () -> assertThat(acc.getOwner()).isNotNull(), () -> assertThat(acc.getOwner_id()).isNotNull(), () -> assertThat(acc.getDate_create()).isNotNull());
        });
    }

    @ParameterizedTest
    @MethodSource("getAcc")
    @DisplayName("Проверяем получение одного счета из базы")
    void getAccount(String caseT) {
        List<Account> allAccounts = accountsService.getAllAccounts();
        Assumptions.assumeFalse(allAccounts.isEmpty(), "в базе нет счетов");
        Collections.shuffle(allAccounts);
        Account account = null;

        switch (caseT) {
            case "id": {
                account = accountsService.getAccount(allAccounts.get(0).getID());
                break;
            }
            case "accountNumber": {
                account = accountsService.getAccount(allAccounts.get(0).getNumber());
                break;
            }
        }

        var accFinal = account;
        assertAll(() -> assertThat(accFinal.getAccount_product()).isNotNull(), () -> assertThat(accFinal.getNumber()).isNotNull(), () -> assertThat(accFinal.getAmount()).isNotNull(), () -> assertThat(accFinal.getOwner()).isNotNull(), () -> assertThat(accFinal.getOwner_id()).isNotNull(), () -> assertThat(accFinal.getDate_create()).isNotNull());
    }

    @Test
    @DisplayName("Удалим счет из базы")
    void deleteAccount() {
        Account account = addAccountPrecondition();
        accountsService.deleteAccount(account.getID());
        List<Account> allAccounts = accountsService.getAllAccounts();

        Optional<Account> finAcc = allAccounts.stream().filter(acc -> acc.getID() == account.getID()).findFirst();

        assertThat(finAcc).as("Счет не удален").isNotPresent();
    }

    @Test
    @DisplayName("Обновим счет")
    void updateClient() {
        List<Account> allAccounts = accountsService.getAllAccounts();
        Account account = allAccounts.get(0);

        account.setOwner(faker.name().firstName() + " " + faker.name().lastName());

        accountsService.updateAccount(account.getID(), account);
        Account newAccount = accountsService.getAccount(account.getID());

        assertThat(account).isEqualTo(newAccount);
    }

    @Test
    public void teststts() {
        /**
         * Есть коллеция с объявлениями
         * создать функцию, которая отфильтрует мапу и выдаст только те цены которые указанны пользователем
         *
         */

        Map<String, Integer> ds = Map.of("Petr", 100, "Max", 123, "ivan", 123123, "sergey", 43343, "Stepan", 554, "Enot", 553);


        filter(ds, 200);
        filter(ds, 600);


    }

    private void filter(Map<String, Integer> ds, int amount) {
        Map<String, Integer> collect = ds.entrySet().stream().filter(item -> item.getValue() > amount).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

        System.out.println(collect);
    }
}
