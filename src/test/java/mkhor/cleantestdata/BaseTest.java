package mkhor.cleantestdata;

import com.github.javafaker.Faker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseTest {
    protected final Log logger = LogFactory.getLog(this.getClass());
    Faker faker = new Faker();
}
