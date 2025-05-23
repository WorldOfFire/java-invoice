package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class TaxFreeProduct extends Product {
    public TaxFreeProduct(String name, BigDecimal price) {
        super(name, price, BigDecimal.ZERO);
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(price.intValue() < 2){
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
