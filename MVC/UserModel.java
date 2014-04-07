import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * @author Brenden
 */
public class UserModel {
    //=================== Private properties/methods ===================//
    private int id;
    private String name;
    private double height;
    private Date birthdate;

    //=================== Public properties/methods ====================//
    public UserModel() {
        Statement query = null;
        ResultSet resultSet = null;

        try {
            query = BeHealthy.conn.createStatement();
            resultSet = query.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                this.id = resultSet.getInt("id");
                this.name = resultSet.getString("firstName") + " " + resultSet.getString("lastName");
                this.height = resultSet.getDouble("height");
                this.birthdate = resultSet.getDate("birthdate");
            } //while
        } catch (SQLException ex) {
            // TODO: Handle exceptions
        } //try-catch
    } //__constructor

    public int getId() {
        return id;
    } //getId

    public void setId(int id) {
        this.id = id;
    } //setId

    public String getName() {
        return name;
    } //getName

    public void setName(String name) {
        this.name = name;
    } //setName

    public double getHeight() {
        return height;
    } //getHeight

    public void setHeight(double height) {
        this.height = height;
    } //setHeight

    public Date getBirthdate() {
        return birthdate;
    } //getBirthdate

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    } //setBirthdate
} //User
