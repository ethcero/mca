
package io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi;

/**
 * @author fran
 */
public class GetProductResponse {
    private Long productId;
    private String name;
    private Integer stock;

    public GetProductResponse() {
    }

    public GetProductResponse(Long productId, String name, Integer stock) {
        this.productId = productId;
        this.name = name;
        this.stock = stock;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
