import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

/**
 * @author Tatiana Ermolitskaya
 */
public class FactoryTest {

    @DataProvider(name = "Data for spend some money tests")
    public Object[][] dataForSpendSomeMoney() {

        return new Object[][] {
                {1_000_000, 1_000_000, 0.0},
                {10_000, 1_000, 9_000},
                {99_999, 10_000, 89_999}
        };
    }

    @Factory(dataProvider = "Data for spend some money tests")
    public Object[] test (double wealth, double spendingAmount, double expected) {
        return new Object[] {new BoyTest(wealth, spendingAmount, expected)};
    }
}
