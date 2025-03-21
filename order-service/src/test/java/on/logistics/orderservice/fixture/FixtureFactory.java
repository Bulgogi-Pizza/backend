package on.logistics.orderservice.fixture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import on.logistics.orderservice.domain.entity.Order;
import on.logistics.orderservice.domain.entity.OrderProduct;
import on.logistics.orderservice.domain.entity.Orderer;
import on.logistics.orderservice.domain.entity.Vendor;
import on.logistics.orderservice.domain.entity.VendorOrder;
import on.logistics.orderservice.domain.entity.dtos.CreateOrderDto;
import on.logistics.orderservice.domain.entity.dtos.CreateOrderProductDto;
import on.logistics.orderservice.domain.entity.dtos.CreateOrdererDto;
import on.logistics.orderservice.domain.entity.dtos.CreateVendorDto;
import on.logistics.orderservice.domain.entity.dtos.CreateVendorOrderDto;

public class FixtureFactory {

    private static Long seq = 1L;

    public static Order getOrder() {
        CreateOrderDto createOrderDto = new CreateOrderDto(
            "destination", 1000L);
        Order order = Order.create(createOrderDto);
        List<VendorOrder> vendorOrderList = List.of(getVendorOrder(order), getVendorOrder(order));
        order.addDependencies(getOrderer(order), vendorOrderList);
        return order;
    }

    public static Orderer getOrderer(Order order) {
        CreateOrdererDto createOrdererDto = new CreateOrdererDto(
            UUID.randomUUID(),
            "companyName" + seq++,
            UUID.randomUUID(),
            "userNickname",
            UUID.randomUUID(),
            "ordererHubName",
            order
        );
        return Orderer.create(createOrdererDto);
    }

    public static VendorOrder getVendorOrder(Order order) {
        CreateVendorOrderDto createVendorOrderDto = new CreateVendorOrderDto(
            order,
            1000L,
            LocalDateTime.now().plusDays(1)
        );
        VendorOrder vendorOrder = VendorOrder.create(createVendorOrderDto);
        Vendor vendor = getVendor(vendorOrder);
        List<OrderProduct> orderProductList = List.of(
            getOrderProduct(vendorOrder), getOrderProduct(vendorOrder));
        vendorOrder.addDependencies(vendor, orderProductList);
        return vendorOrder;
    }

    public static Vendor getVendor(VendorOrder vendorOrder) {
        CreateVendorDto createVendorDto1 = new CreateVendorDto(
            UUID.randomUUID(),
            "vendorName" + seq++,
            UUID.randomUUID(),
            "vendorHubName",
            vendorOrder
        );
        return Vendor.create(createVendorDto1);
    }

    public static OrderProduct getOrderProduct(VendorOrder vendorOrder) {
        CreateOrderProductDto createOrderProductDto1 = new CreateOrderProductDto(
            vendorOrder,
            UUID.randomUUID(),
            10L,
            1000L,
            "name" + seq++
        );
        return OrderProduct.create(createOrderProductDto1);
    }
}
