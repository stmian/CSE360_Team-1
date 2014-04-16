
import javax.swing.JPanel;

public class HomeController {

    //=================== Private properties/methods ===================//
    private HomeModel model;
    private HomeView view;

    //=================== Public properties/methods ====================//
    public HomeController(HomeModel model) {
        this.model = new HomeModel();
        this.view = new HomeView(this, this.model);
        this.view.createView();
    }

    public JPanel getPanel() {
        return view.getPanel();
    } //getPanel

}
