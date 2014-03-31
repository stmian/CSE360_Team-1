
import javax.swing.JPanel;

public class HealthController {

    //=================== Private properties/methods ===================//
    private HealthModel model;
    private HealthView view;

    //=================== Public properties/methods ====================//
    public HealthController(HealthModel model) {
        this.model = model;
        this.view = new HealthView(this, this.model);
        this.view.createView();
    }

    public JPanel getPanel() {
        return view.getPanel();
    } //getPanel

}
