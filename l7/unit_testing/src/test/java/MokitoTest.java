import com.epam.gomel.homework.Boy;
import com.epam.gomel.homework.Girl;
import com.epam.gomel.homework.TestListener;
import org.mockito.Mockito;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

/**
 * @author Tatiana Ermolitskaya
 */

@Listeners(TestListener.class)
public class MokitoTest {
    @Test
    public void GirlMockTest() {

        Boy boy = Mockito.mock(Boy.class);
        Girl girl = new Girl(true, false, boy);
        Mockito.when(boy.getGirlFriend()).thenReturn(girl);
        assertEquals(girl, boy.getGirlFriend());
        verify(boy.getGirlFriend().getBoyFriend());
    }
}
