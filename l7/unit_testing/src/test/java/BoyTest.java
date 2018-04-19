import com.epam.gomel.homework.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * @author Tatiana Ermolitskaya
 */

@Listeners(TestListener.class)
public class BoyTest extends CommonTestFixtures {

    private double wealth;
    private double spendingAmount;
    private double expected;


    @DataProvider(name = "Data for spend some money exception test")
    public Object[][] dataForSpendSomeMoneyExcept() {

        return new Object[][] {
                {1_000_000, 10_000_000, RuntimeException.class},
                {1_000, 10_000, RuntimeException.class},
                {9_999, 10000, RuntimeException.class}
        };

    }
    @DataProvider(name = "Data for spend some money test")
    public Object[][] dataForSpendSomeMoney() {

        return new Object[][] {
                {1_000_000, 1_000_000, 0.0},
                {10_000, 1_000, 9_000},
                {99_999, 10_000, 89_999}
        };
    }
    public BoyTest(double wealth, double spendingAmount, double expected) {

        this.wealth = wealth;
        this.spendingAmount = spendingAmount;
        this.expected = expected;
    }

    @Test(groups = {"Boy", "Constructor"}, description = "Boy's constructor must return boy's girlfriend/bug was " +
            "found with the help of this test", priority = 0)
    public void fullBoyConstructorTest() {
        Girl girl1 =  new Girl(true, false);
        Boy boy1 = new Boy(Month.JANUARY,1000.0, girl1);
        girl1.setBoyFriend(boy1);
        long id = Thread.currentThread().getId();
        System.out.println("boy2. Thread id is: " + id);
        Assert.assertEquals(boy1.getGirlFriend(), girl1, "Invalid setBoyfriend to girlfriend in constructor");
    }

    @Test(groups = {"Boy", "Constructor"}, description = "Boy's constructor must return his birthday's month, " +
            "wealth - 0, girlfriend - null", priority = 0)
    public void boyConstructorTest() {
        Boy boy1 = new Boy(Month.JANUARY);
        Assert.assertEquals(boy1.getBirthdayMonth(), Month.JANUARY, "Invalid constructor");
        Assert.assertEquals(boy1.getWealth(), 0.0, "Invalid wealth data in constructor");
        Assert.assertEquals(boy1.getGirlFriend(), null, "Invalid girlfriend data in constructor");
    }

    @Test(groups = {"Boy", "IsSummerMonth"}, description = "Boy's B'day is January. IsSummerMonth must return false")
    public void januaryIsSummerMonthTest() {
        Boy boy1 = new Boy(Month.JANUARY);
        boolean actual = boy1.isSummerMonth();
        Assert.assertEquals(actual, false, "Invalid method");
    }

    @Test(groups = {"Boy", "IsSummerMonth"}, description = "Boy's B'day is June. IsSummerMonth must return true")
    public void juneIsSummerMonthTest() {
        Boy boy1 = new Boy(Month.JUNE);
        boolean actual = boy1.isSummerMonth();
        Assert.assertEquals(actual, true, "Invalid method");
    }

    @Test(groups = {"Boy", "IsSummerMonth"}, description = "Boy's B'day is July. IsSummerMonth must return true" +
            "/bug was found with the help of this test")
    public void julyeIsSummerMonthTest() {
        Boy boy1 = new Boy(Month.JULY);
        boolean actual = boy1.isSummerMonth();
        Assert.assertEquals(actual, true, "Invalid method");
    }

    @Test(groups = {"Boy", "IsSummerMonth"}, description = "Boy's B'day is August. IsSummerMonth must return true" +
            "/bug was found with the help of this test")
    public void augusteIsSummerMonthTest() {
        Boy boy1 = new Boy(Month.AUGUST);
        boolean actual = boy1.isSummerMonth();
        Assert.assertEquals(actual, true, "Invalid method");
    }

    @Test(groups = {"Boy", "IsRich"}, description = "Boy's wealth is more than 1000000. method IsRich must return true")
    public void boyIsRichTrueTest() {
        Boy boy1 = new Boy(Month.AUGUST, 1_000_000.0001);
        boolean actual = boy1.isRich();
        Assert.assertEquals(actual, true, "Invalid wealth data in method");
    }

    @Test(groups = {"Boy", "IsRich"}, description = "Boy's wealth is less than 1000000. method IsRich must return false")
    public void boyIsRichFalseTest() {
        Boy boy1 = new Boy(Month.AUGUST, 999_999);
        boolean actual = boy1.isRich();
        Assert.assertEquals(actual, false, "Invalid wealth data in method");
    }

    @Test(groups = {"Boy", "IsPrettyGirlFriend"}, description = "Boy has a pretty girlfriend. must return true")
    public void prettyGirlfriendTrueTest() {
        Girl girl1 = new Girl(true);
        Boy boy1 = new Boy(Month.APRIL, 1000, girl1);
        boolean actual = boy1.isPrettyGirlFriend();
        Assert.assertEquals(actual, true, "Invalid data in constructor");
    }

    @Test(groups = {"Boy", "IsPrettyGirlFriend"}, description = "Boy has an ugly girlfriend. must return false")
    public void prettyGirlfriendFalseTest() {
        Girl girl1 = new Girl(false);
        Boy boy1 = new Boy(Month.APRIL, 1000, girl1);
        boolean actual = boy1.isPrettyGirlFriend();
        Assert.assertEquals(actual, false, "Invalid data in constructor");
    }

    @Test(groups = {"Boy","IsPrettyGirlFriend"}, description = "Boy hasn't a girlfriend. must return false")
    public void prettyGirlBoyDoNotHaveGirlAtAllTest() {
        Boy boy1 = new Boy(Month.APRIL, 1000);
        boolean actual = boy1.isPrettyGirlFriend();
        Assert.assertEquals(actual, false, "Invalid data in constructor");
    }

    @Test(groups = {"Boy", "SpendSomeMoney"}, description = "Boy can spend some money. Boy has 1000 and spends 1000. " +
            "The remaining sum must be 0/bug was found with the help of this test", dataProvider = "Data for spend some money test")
    public void spendSomeMoneyTest(double a, double b, double expected) {
        Boy boy1 = new Boy(Month.APRIL, a);
        boy1.spendSomeMoney(b);
        double actual = boy1.getWealth();
        Assert.assertEquals(actual, expected,"Wrong balance of the amount");
    }

    @Test(groups = {"Boy", "SpendSomeMoney"}, description = "Boy can't spend some money. Boy has no money. " +
            "Exception must be returned", expectedExceptions = RuntimeException.class)
    public void spendSomeMoneyExceptionTest() {
        Boy boy1 = new Boy(Month.APRIL, 1000);
        boy1.spendSomeMoney(10000);
        double actual = boy1.getWealth();
    }

    @Test(groups = {"Boy", "Mood"}, description = "Boy who has pretty girl, enough money and was born in the summer must" +
            "have excellent mood", dependsOnGroups = {"IsRich", "IsSummerMonth", "IsPrettyGirlFriend"})
    public void getExcellentMoodTest() {
        Boy boy1 = new Boy(Month.AUGUST, 1_000_000);
        Girl girl1 = new Girl(true, false, boy1);
        boy1.setGirlFriend(girl1);
        Mood actual =  boy1.getMood();
        Assert.assertEquals(actual, Mood.EXCELLENT, "Invalid result of mood");
    }

    @Test(groups = {"Boy", "Mood"}, description = "Boy who has pretty girl and enough money, but wasn't born in the " +
            "summer must have good mood", dependsOnGroups = {"IsRich", "IsPrettyGirlFriend"})
    public void getGoodMoodTest() {
        Boy boy1 = new Boy(Month.MARCH, 1_000_000);
        Girl girl1 = new Girl(true, false, boy1);
        boy1.setGirlFriend(girl1);
        Mood actual =  boy1.getMood();
        Assert.assertEquals(actual, Mood.GOOD, "Invalid result of mood");
    }

    @Test(groups = {"Boy", "Mood"}, description = "Boy who has and enough money and was born in the summer, but hasn't" +
            "got a girl or has not very pretty girl must have neutral mood", dependsOnGroups = {"IsRich", "IsSummerMonth"})
    public void getNeutralMoodTest() {
        Boy boy1 = new Boy(Month.JULY, 1_000_000);
        Mood actual =  boy1.getMood();
        Assert.assertEquals(actual, Mood.NEUTRAL, "Invalid result of mood");
        Girl girl1 = new Girl(false, false, boy1);
        boy1.setGirlFriend(girl1);
        actual =  boy1.getMood();
        Assert.assertEquals(actual, Mood.NEUTRAL, "Invalid result of mood");
    }

    @Test(groups = {"Boy", "Mood"}, description = "Boy who has only enough money must have bad mood",
            dependsOnGroups = {"IsRich", "IsSummerMonth", "IsPrettyGirlFriend"})
    public void getBadMoodRichTest() {
        Boy boy1 = new Boy(Month.FEBRUARY, 1_000_000);
        Girl girl1 = new Girl(false, false, boy1);
        boy1.setGirlFriend(girl1);
        Mood actual =  boy1.getMood();
        Assert.assertEquals(actual, Mood.BAD, "Invalid result of mood");
    }

    @Test(groups = {"Boy", "Mood"}, description = "Boy who has only pretty girl must have bad mood",
            dependsOnGroups = {"IsRich", "IsSummerMonth", "IsPrettyGirlFriend"})
    public void getBadMoodPrettyGirlTest() {
        Boy boy1 = new Boy(Month.SEPTEMBER, 1_000);
        Girl girl1 = new Girl(true, false, boy1);
        boy1.setGirlFriend(girl1);
        Mood actual =  boy1.getMood();
        Assert.assertEquals(actual, Mood.BAD, "Invalid result of mood");
    }

    @Test(groups = {"Boy", "Mood"}, description = "Boy who was born in the summer must have bad mood",
            dependsOnGroups = {"IsRich", "IsSummerMonth", "IsPrettyGirlFriend"})
    public void getBadMoodSummerMonthTest() {
        Boy boy1 = new Boy(Month.AUGUST, 100);
        Girl girl1 = new Girl(false, false, boy1);
        boy1.setGirlFriend(girl1);
        Mood actual =  boy1.getMood();
        Assert.assertEquals(actual, Mood.BAD, "Invalid result of mood");
    }

    @Test(groups = {"Boy", "Mood"}, description = "Boy who isn't rich, hasn't got a pretty girl and wasn't born in the " +
            "summer must have horrible mood", dependsOnGroups = {"IsRich", "IsSummerMonth", "IsPrettyGirlFriend"})
    public void getHorribleMoodSummerMonthTest() {
        Boy boy1 = new Boy(Month.MAY, 100);
        Girl girl1 = new Girl(false, false, boy1);
        boy1.setGirlFriend(girl1);
        Mood actual =  boy1.getMood();
        Assert.assertEquals(actual, Mood.HORRIBLE, "Invalid result of mood");
    }
}
