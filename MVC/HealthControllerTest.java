import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

/**
 * Created by Zach on 4/30/14.
 */
public class HealthControllerTest {
    @Test
    public void testAddHealthMetric() throws Exception {
        BeHealthy health = new BeHealthy();

        HealthController controller = health.healthController;
        Date date = new Date();

        assertEquals("Success should return true", true, controller.addHealthMetric(1, 8.0, date));
        assertEquals("Success should return true", true, controller.addHealthMetric(2, 8.0, date));
        assertEquals("Success should return true", true, controller.addHealthMetric(3, 8.0, date));
        assertEquals("Success should return true", true, controller.addHealthMetric(4, 8.0, date));
    }

    @Test
    public void testRemoveHealthMetric() throws Exception{

    }
}
