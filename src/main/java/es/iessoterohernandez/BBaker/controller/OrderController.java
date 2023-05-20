package es.iessoterohernandez.BBaker.controller;

import java.util.List;
import org.json.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iessoterohernandez.BBaker.DTO.OrderODTO;
import es.iessoterohernandez.BBaker.DTO.StatusChangeDTO;
import es.iessoterohernandez.BBaker.model.OrderO;
import es.iessoterohernandez.BBaker.model.OrderProducts;
import es.iessoterohernandez.BBaker.model.OrderStatus;
import es.iessoterohernandez.BBaker.service.OrderService;
import es.iessoterohernandez.BBaker.service.StatusService;

@RestController
@RequestMapping("/api/BBorder")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    StatusService statusService;

    @PostMapping("add")
    public OrderO addNew(@RequestBody OrderODTO orderODTO) {
        OrderO order = orderService.mapToOrder(orderODTO);
        OrderO returnedOrder = orderService.addNewOrder(order);
        List<OrderProducts> orderProducts = orderService.mapToOrderProducts(orderODTO, returnedOrder);
        orderService.saveOrderProducts(orderProducts);
        return order;
    }

    @PostMapping("paid")
    public OrderO setPaid(@RequestBody String json) throws Exception {
        System.out.println("ESTO ES EL BODY" + json);
        JSONObject obj;
        Long orderId = (long) 0;
        Long paidDate = (long) 0;
            obj = new JSONObject(json);
            orderId = obj.getLong("order_id");
            paidDate = obj.getLong("paidDate");
            System.out.println("orderID " + orderId);
            System.out.println("paidDate " + paidDate);
        return orderService.setPaid(orderId, paidDate);
    }

    @PostMapping("change_status")
    public OrderO changeStatus(@RequestBody StatusChangeDTO scDTO) throws Exception {
        return statusService.setOrderStatus(scDTO.getStatus_id(), scDTO.getOrder_id());
    }

    @PostMapping("/delete")
    public int deleteOrderO(@RequestBody String json) {
        System.out.println("ESTO ES EL BODY" + json);
        JSONObject obj;
        Long orderId = (long) 0;
        try {
            obj = new JSONObject(json);
            orderId = obj.getLong("order_id");
            System.out.println("orderID " + orderId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return orderService.delete(orderId);
    }

    @GetMapping("myorders/{id}")
    public List<OrderODTO> listMyOrders(@PathVariable Long id) {
        return orderService.getMyOrders(id);
    }

    @GetMapping
    public List<OrderO> getAll() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderO getByID(@PathVariable Long id) {
        return orderService.getByID(id);
    }

    @GetMapping("/products/{id}")
    public List<OrderProducts> getProductsByOrderID(@PathVariable Long id) {
        return orderService.getProductsByOrderID(id);
    }

    @GetMapping("/all_statuses")
    public List<OrderStatus> getAllStatuses() {
        return statusService.getAllStatuses();
    }
}
