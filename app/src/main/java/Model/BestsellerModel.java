package Model;

public class BestsellerModel {

    Integer bestseller_food_image;
    String bestseller_pizza_store, bestseller_pizza_type, bestseller_pizza_rate;

    public BestsellerModel(Integer bestseller_food_image, String bestseller_pizza_store, String bestseller_pizza_type, String bestseller_pizza_rate) {
        this.bestseller_food_image = bestseller_food_image;
        this.bestseller_pizza_store = bestseller_pizza_store;
        this.bestseller_pizza_type = bestseller_pizza_type;
        this.bestseller_pizza_rate = bestseller_pizza_rate;
    }

    public Integer getBestseller_food_image() {
        return bestseller_food_image;
    }

    public void setBestseller_food_image(Integer bestseller_food_image) {
        this.bestseller_food_image = bestseller_food_image;
    }

    public String getBestseller_pizza_store() {
        return bestseller_pizza_store;
    }

    public void setBestseller_pizza_store(String bestseller_pizza_store) {
        this.bestseller_pizza_store = bestseller_pizza_store;
    }

    public String getBestseller_pizza_type() {
        return bestseller_pizza_type;
    }

    public void setBestseller_pizza_type(String bestseller_pizza_type) {
        this.bestseller_pizza_type = bestseller_pizza_type;
    }

    public String getBestseller_pizza_rate() {
        return bestseller_pizza_rate;
    }

    public void setBestseller_pizza_rate(String bestseller_pizza_rate) {
        this.bestseller_pizza_rate = bestseller_pizza_rate;
    }
}
