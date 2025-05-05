package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private static int lastInvoiceNumber = 0;
    private final int year = LocalDate.now().getYear();
    private final String invoiceNumber;

    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    public Invoice() {
        this.invoiceNumber = String.format("FV-%s-%04d", year, ++lastInvoiceNumber);
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }

        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }



    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().add(product.getExcise()).multiply(quantity));
        }
        return totalGross;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Numer faktury: ").append(getInvoiceNumber()).append("\n");

        int count = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            sb.append(product.getName())
                    .append(", ")
                    .append(quantity)
                    .append(" szt., ")
                    .append(product.getPrice())
                    .append(" zł\n");
            count++;
        }

        sb.append("Liczba pozycji: ").append(count);
        return sb.toString();
    }

}
