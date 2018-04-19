import com.epam.gomel.homework.Boy;
import com.epam.gomel.homework.Girl;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author Tatiana Ermolitskaya
 */
public class GirlConstructorMatcher {

    public static Matcher<Girl> pretty() {
        return new FeatureMatcher<Girl, Boolean>(is(true), "Girl should be pretty", "Pretty -") {
            @Override
            protected Boolean featureValueOf(Girl girl1) {
                return girl1.isPretty();
            }
        };
    }

    public static Matcher<Girl> slimFriendGotAFewKilos() {
        return new FeatureMatcher<Girl, Boolean>(is(false), "A slim friend shouldn't have got a few kilos", "SlimFriendGotAFewKilos -") {
            @Override
            protected Boolean featureValueOf(Girl girl1) {
                return girl1.isSlimFriendGotAFewKilos();
            }
        };
    }

    public static Matcher<Girl> boyFriend(Boy boy1) {
        return new FeatureMatcher<Girl, Boy>(equalTo(boy1), "Girl shouldn't have a boyfriend", "BoyFriend -") {
            @Override
            protected Boy featureValueOf(Girl girl1) {
                return girl1.getBoyFriend();
            }
        };
    }
}