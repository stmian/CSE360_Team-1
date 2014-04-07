
import javax.swing.JPanel;
import java.util.Date;

/**
 * @author Brenden
 */
public class UserController {

    //=================== Private properties/methods ===================//
    private UserModel model;
    private UserView view;

    //=================== Public properties/methods ====================//
    public UserController(UserModel model) {
        this.model = model;
        this.view = new UserView(this, this.model);
        this.view.createView();
    } //__constructor

    public int getId() {
        return model.getId();
    } //getId

    public String getName() {
        return model.getName();
    } //getName

    public void setName(String name) {
        model.setName(name);
    } //setName

    public double getHeight() {
        return model.getHeight();
    } //getHeight_metric

    public void setHeight(double height) {
        model.setHeight(height);
    } //setHeight_metric

    public Date getBirthdate() {
        return model.getBirthdate();
    } //getBirthdate

    public void setBirthdate(Date birthdate) {
        model.setBirthdate(birthdate);
    } //setBirthdate

    public JPanel getPanel() {
        return view.getPanel();
    } //getPanel
} //UserController
