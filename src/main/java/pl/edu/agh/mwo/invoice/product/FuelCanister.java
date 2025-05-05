package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanister extends Product {
    private static final BigDecimal nonTax = BigDecimal.ZERO;
    private static final BigDecimal excise = new BigDecimal("5.56");

    public FuelCanister(String name, BigDecimal price) {
        super(name, price, nonTax);
    }

    @Override
    public BigDecimal getExcise() {
        return excise;
    }
}
