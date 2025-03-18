package on.logistics.orderservice.application.clients.product.feign.dtos;

public record GetProductResponse(
    String name,
    int stock
) {

}
