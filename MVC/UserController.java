
import javax.swing.JPanel;

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

    public String getName() {
        return model.getName();
    } //getName

    public void setName(String name) {
        model.setName(name);
    } //setName

    public float getHeight() {
        return model.getHeight();
    } //getHeight_metric

    public void setHeight_metric(float height_metric) {
        model.setHeight_metric(height_metric);
    } //setHeight_metric

    public String getBirthdate() {
        return model.getBirthdate();
    } //getBirthdate

    public void setBirthdate(String birthdate) {
        model.setBirthdate(birthdate);
    } //setBirthdate

    public boolean getUseMetric() {
        return model.getUseMetric();
    } //getUseMetric

    public void setUseMetric(boolean useMetric) {
        model.setUseMetric(useMetric);
    } //setUseMetric

    public JPanel getPanel() {
        return view.getPanel();
    } //getPanel
} //UserController
