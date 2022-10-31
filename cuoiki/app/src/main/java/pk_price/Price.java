package pk_price;

import java.io.Serializable;

public class Price implements Serializable {
    String price;
    int min;
    int max;

    public Price(String price, int min, int max) {
        this.price = price;
        this.min = min;
        this.max = max;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
