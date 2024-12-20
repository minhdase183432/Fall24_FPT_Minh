/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;
import java.io.Serializable;


/**
 *
 * @author admin
 */
public class RAMItem implements Serializable{
    private static final long serialVersionUID = 1L; // Ensure serial compatibility
    private String code;
    private String type;
    private String bus;
    private String brand;
    private int quantity;
    private String productionMonthYear;
    private boolean active;

    public RAMItem(String code, String type, String bus, String brand, int quantity, String production_month_year) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.productionMonthYear = production_month_year;
        this.active = true;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductionMonthYear() {
        return productionMonthYear;
    }

    public void setProductionMonthYear(String productionMonthYear) {
        this.productionMonthYear = productionMonthYear;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "RAMItem{" + "code=" + code + ", type=" + type + ", bus=" + bus + ", brand=" + brand + ", quantity=" + quantity + ", production_month_year=" + productionMonthYear + ", active=" + active + '}';
    }
   
    
    
    
    
    
}
