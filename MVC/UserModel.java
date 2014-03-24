/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package behealthy;

/**
 *
 * @author Brenden
 */
public class UserModel {
    //=================== Private properties/methods ===================//
    private String name;
    private float height_metric;
    private String birthdate;
    private boolean useMetric;

    
    //=================== Public properties/methods ====================//
    public UserModel(String name, float height_metric, String birthdate, boolean useMetric) {
        this.setName(name);
        this.setHeight_metric(height_metric);
        this.setBirthdate(birthdate);
        this.setUseMetric(useMetric);
    } //__constructor
    
    
    public String getName() {
        return name;
    } //getName
    

    public void setName(String name) {
        this.name = name;
    } //setName
    

    public float getHeight() {
        float tempHeight = this.height_metric;
        
        // Change to inches
        if (!(this.useMetric)) {
            tempHeight = tempHeight * .3937f;
        } //if
        
        return tempHeight;
    } //getHeight_metric

    
    public void setHeight_metric(float height_metric) {
        this.height_metric = height_metric;
    } //setHeight_metric
    

    public String getBirthdate() {
        return birthdate;
    } //getBirthdate
    

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    } //setBirthdate
    

    public boolean getUseMetric() {
        return useMetric;
    } //getUseMetric

    
    public void setUseMetric(boolean useMetric) {
        this.useMetric = useMetric;
    } //setUseMetric
} //User
