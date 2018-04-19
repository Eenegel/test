import com.epam.gomel.homework.Boy;
import com.epam.gomel.homework.Girl;
import com.epam.gomel.homework.Month;
import com.epam.gomel.homework.Mood;
import com.epam.gomel.homework.TestListener;

import static org.hamcrest.Matchers.both;
import static org.testng.Assert.*;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Tatiana Ermolitskaya
 */

@Listeners(TestListener.class)
public class GirlTest extends GirlConstructorMatcher {

    @Test(groups = {"Girl", "Constructor"}, description = "Girl's constructor must return girl's boyfriend/bug was found " +
            "with the help of this test", priority = 1)
    public void fullGirlConstructorTest() {
        Boy boy1 =  new Boy(Month.JANUARY, 1000.0);
        Girl girl1 = new Girl(true, false, boy1);
        boy1.setGirlFriend(girl1);
        assertEquals(girl1.getBoyFriend(), boy1, "Invalid setGirlfriend to boyfriend in constructor");
    }

    @Test(groups = {"Girl", "Constructor"}, description = "Girl's constructor must return pretty - true, " +
            "isSlimFriendGotAFewKilos - false, getBoyFriend - null", priority = 1)
    public void girlConstructorTest() {
        Girl girl1 = new Girl(true);
        long id = Thread.currentThread().getId();
        System.out.println("girl2. Thread id is: " + id);
        /*assertEquals(girl1.isPretty(), true, "Invalid data in constructor");
        assertEquals(girl1.isSlimFriendGotAFewKilos(), false, "Invalid data in constructor");
        assertEquals(girl1.getBoyFriend(), null, "Invalid constructor");*/
        assertThat(girl1, both(pretty()).and(slimFriendGotAFewKilos()).and(boyFriend(null)));
    }

    @Test(groups = {"Girl", "Constructor"}, description = "Girl's constructor must return pretty - false, " +
            "isSlimFriendGotAFewKilos - true, getBoyFriend - null", priority = 1)
    public void girlEmptyConstructorTest() {
        Girl girl1 = new Girl();
        assertEquals(girl1.isPretty(), false, "Invalid data in constructor");
        assertEquals(girl1.isSlimFriendGotAFewKilos(), true, "Invalid data in constructor");
        assertEquals(girl1.getBoyFriend(), null, "Invalid constructor");
    }

    @Test(groups = {"Girl"}, description = "Girl's constructor must return pretty - true, isSlimFriendGotAFewKilos - true, " +
            "getBoyFriend - null", priority = 1)
    public void girlSetterTest() {
        Girl girl1 = new Girl();
        girl1.setPretty(true);
        assertEquals(girl1.isPretty(), true, "Invalid data in constructor");
        assertEquals(girl1.isSlimFriendGotAFewKilos(), true, "Invalid data in constructor");
        assertEquals(girl1.getBoyFriend(), null, "Invalid constructor");
    }

    @Test(groups = {"Girl", "IsBoyfriendRich"}, description = "Girl has rich boyfriend. must return true/bug was found" +
            "with the help of this test")
    public void richBoyFriendTrueTest() {
        Boy boy1 = new Boy(Month.JANUARY, 1000000);
        Girl girl1 = new Girl(true,false, boy1);
        boy1.setGirlFriend(girl1);
        boolean actual = girl1.isBoyfriendRich();
        assertEquals(actual, true, "Invalid return result");
    }

    @Test(groups = {"Girl", "Mood"}, description = "Girl whose boyfriend can buy new shoes for her must have excellent mood",
            dependsOnGroups = {"IsBoyfriendRich"})
    public void getExcellentMoodTest() {
        Boy boy1 = new Boy(Month.JANUARY, 1_000_000);
        Girl girl1 = new Girl(true,false, boy1);
        boy1.setGirlFriend(girl1);
        Mood actual = girl1.getMood();
        assertEquals(actual, Mood.EXCELLENT, "Invalid result of mood");
    }

    @Test(groups = {"Girl", "Mood"}, description = "Girl who has a pretty appearance must have good mood")
    @Parameters({"prettyAppearance"})
    public void getGoodMoodGirlTest(boolean prettyAppearance ) {
        Boy boy1 = new Boy(Month.AUGUST);
        Girl girl1 = new Girl(prettyAppearance,false, boy1);
        boy1.setGirlFriend(girl1);
        Mood actual = girl1.getMood();
        assertEquals(actual, Mood.GOOD, "Invalid result of mood");
    }

    @Test(groups = {"Girl", "Mood"}, description = "Girl who has a rich boyfriend must have good mood",
            dependsOnGroups = {"IsBoyfriendRich"})
    public void getGoodMoodTest() {
        Boy boy1 = new Boy(Month.JANUARY, 1_000_000);
        Girl girl1 = new Girl(false,false, boy1);
        boy1.setGirlFriend(girl1);
        Mood actual = girl1.getMood();
        assertEquals(actual, Mood.GOOD, "Invalid result of mood");
    }

    @Test(groups = {"Girl", "Mood"}, description = "Girl who has a fat friend and not pretty appearance must have neutral mood")
    public void getNeutralMoodTest() {
        Girl girl1 = new Girl(false,true);
        Mood actual = girl1.getMood();
        assertEquals(actual, Mood.NEUTRAL, "Invalid result of mood");
        girl1.setPretty(true);
        actual = girl1.getMood();
        assertEquals(actual, Mood.GOOD, "Invalid result of mood");
    }

    @Test(groups = {"Girl", "Mood"}, description = "Girl who hasn't a boyfriend and a pretty appearance must have hating all mood",
            dependsOnGroups = {"IsBoyfriendRich"})
    public void getHatingAllMoodTest() {
        Girl girl1 = new Girl(false,false);
        Mood actual = girl1.getMood();
        assertEquals(actual, Mood.I_HATE_THEM_ALL, "Invalid result of mood");
    }

    @Test(groups = {"Girl", "SpendBoyFriendMoney"}, description = "Amount of wealth of the girl's rich boyfriend must decrease",
            dependsOnGroups = {"IsBoyfriendRich"})
    public void spendMoneyTest() {
        Boy boy1 = new Boy(Month.JANUARY, 1_000_000);
        Girl girl1 = new Girl(false,false, boy1);
        girl1.spendBoyFriendMoney(1_000);
        assertEquals(boy1.getWealth(), 999_000.0, "Invalid result of current amount");
    }
}
