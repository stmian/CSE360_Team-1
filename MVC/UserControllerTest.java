import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

/**
 * Tests User classes and integration via the resetUserProfile() method
 *
 * @author Brenden
 */
public class UserControllerTest {
    @Test
    public void testResetUserProfile() throws Exception {
        BeHealthy mainDriver = new BeHealthy();
        ArrayList<HealthMetric> healthArray;
        Date date = new Date();
        java.sql.Date date_sql = new java.sql.Date(date.getTime());

        // Create dummy user
        Statement query = null;
        try {
            query = BeHealthy.conn.createStatement();
            query.executeUpdate("INSERT INTO users (`firstName`, `lastName`, `height`, `birthdate`) VALUES ('Bobby', 'Joe', '25', NOW())");
        } catch (SQLException ex) {
            // TODO: Handle exceptions
        } //try-catch

        // Set to dummy user
        BeHealthy.userController.fetchUserProfile();
        assertEquals("Testing fetchUserProfile()", "Bobby Joe", BeHealthy.userController.getName());

        // Integration testing
        assertEquals("Testing integration: Activity", true, BeHealthy.activitiesController.addActivity(1, "Sleep", date_sql, 8.0));
        assertEquals("Testing integration: Health Metric", true, BeHealthy.healthController.addHealthMetric(1, 8.0, date));
        healthArray = BeHealthy.healthController.gethealthMetrics();
        assertEquals("Testing integration: Health Metric Verified", true, healthArray.get(healthArray.size() - 1).date == date);

        // Deletes dummy user and all activities/metrics
        BeHealthy.userController.resetUserProfile();
        assertEquals("Testing resetUserProfile()", "Brenden Brennan", BeHealthy.userController.getName());
        healthArray = BeHealthy.healthController.gethealthMetrics();
        assertEquals("Testing integration: resetUserProfile()", false, healthArray.get(healthArray.size() - 1).date == date);
    } //testResetUserProfile
} //UserControllerTest
