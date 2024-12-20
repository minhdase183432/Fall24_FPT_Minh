// ==========================================================================
// Do NOT modify this file 
// ==========================================================================

class Office {

    String location;
    int price, area; 
    
    // Default constructure
    Office () {}
    
    // Constructor with full parameter
    Office (String xLocation, int xPrice, int xArea) {
        this.location = xLocation;
        this.price = xPrice; 
        this.area = xArea;
    }
    
    @Override
    public String toString(){
        return "(" +location+","+price + "," + area + ")";
    }

    public int getPrice() {
        return this.price;
    }

    public int getArea() {
        return this.area;
    }

    public String getLocation() {
        return this.location;
    }

    public void setArea(int inArea) {
        this.area = inArea;
    }

    public void setPrice(int inPrice) {
        this.price = inPrice;
    }

    public void setLocation(String inLocation) {
        this.location = inLocation;
    }

}
