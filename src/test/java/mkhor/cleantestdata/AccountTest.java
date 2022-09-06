package mkhor.cleantestdata;

import mkhor.cleantestdata.api.dto.request.account.Account;
import mkhor.cleantestdata.api.service.accounts.AccountsService;
import mkhor.cleantestdata.utils.DateUtils;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class AccountTest extends BaseTest {

    @Autowired
    public AccountsService accountsService;

    @Test
    @RepeatedTest(20)
    void addAccount() {
        BigDecimal amount = new BigDecimal(String.valueOf(faker.random().nextInt(1111, 9999)));
        StringBuilder owner = new StringBuilder();
        owner.append(faker.name().firstName())
                .append(" ")
                .append(faker.name().lastName());

        String product = "МИР";
        String date_create = DateUtils.parseDateToString(faker.date().birthday());
        String number = "40702810" + faker.random().nextInt(1111, 9999) + "60657001";
        long owner_id = faker.number().randomNumber();

        Account account = Account.builder()
                .account_product(product)
                .amount(amount)
                .date_create(date_create)
                .number(number)
                .owner(owner.toString())
                .owner_id(owner_id)
                .reserved(false)
                .build();

        Account acc = accountsService.addAccount(account);

        Account newAccount = accountsService.getAccount(acc.getID());

        assertAll(
                () -> assertThat(newAccount.getAccount_product()).isEqualToIgnoringCase(product),
                () -> assertThat(newAccount.getNumber()).isEqualToIgnoringCase(number),
                () -> assertThat(newAccount.getAmount()).isEqualTo(amount),
                () -> assertThat(newAccount.getOwner()).isEqualToIgnoringCase(owner),
                () -> assertThat(newAccount.getOwner_id()).isEqualTo(owner_id),
                () -> assertThat(newAccount.getDate_create()).isEqualTo(date_create)
        );

        logger.info("Счет добавлен в базу");
    }
}
