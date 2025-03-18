package on.logistics.orderservice.infrastructure.clients.product.feign.dtos;

public record GetProductResponse(
    String name,
    int stock
) {

}
