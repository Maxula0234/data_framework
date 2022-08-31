package mkhor.cleantestdata;

import mkhor.cleantestdata.db.dao.clients.ClientsDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientTest {
    @Autowired
    public ClientsDao clientsDao;

    @Test
    void contextLoads() {
        clientsDao.getAllClient();
    }

}
