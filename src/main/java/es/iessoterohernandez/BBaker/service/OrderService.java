package es.iessoterohernandez.BBaker.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.BBaker.DTO.OrderODTO;
import es.iessoterohernandez.BBaker.DTO.OrderProductDTO;
import es.iessoterohernandez.BBaker.model.OrderO;
import es.iessoterohernandez.BBaker.model.OrderProducts;
import es.iessoterohernandez.BBaker.repository.OrderProductsRepository;
import es.iessoterohernandez.BBaker.repository.OrderRepository;
import es.iessoterohernandez.BBaker.repository.ProductRepository;
import es.iessoterohernandez.BBaker.repository.UserRepository;

@Service
public class OrderService {
    
    @Autowired
    OrderRepository orderRepository;
    
    @Autowired
    OrderProductsRepository orderProductsRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public OrderO mapToOrder(OrderODTO orderODTO) {
        //ObjectMapper om = new ObjectMapper();
		//om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        OrderO order = new OrderO();
        order.setCreationDate(orderODTO.getCreationDate());
        order.setPaidDate(orderODTO.getPaidDate());
        order.setTotal(orderODTO.getTotal());
        if (orderODTO.getUser_id() == 0) {
            order.setUser(null);
        } else {
            order.setUser(userRepository.findById(orderODTO.getUser_id()));
        }

        //TypeReference<OrderO> typeReference = new TypeReference <OrderO>(){};
        //try {
        //   order = om.readValue(json, typeReference);
        //} catch (Exception e) {
        //    System.out.println("Unable to save features: " + e.getMessage());
        //}
        return order;        
    }


    public OrderO addNewOrder(OrderO order){
        return orderRepository.save(order);
    }
    
    public String saveOrderProducts(List<OrderProducts> orderProducts){
    System.out.println("voy a guarder ESSSSSSSSSTA LISTA");
    List<OrderProducts> returnedProducts = orderProductsRepository.saveAll(orderProducts);
    System.out.println(returnedProducts.toString());
    return "Yuppi";
    }

    public List<OrderProducts> mapToOrderProducts(OrderODTO orderODTO, OrderO order){
        List<OrderProductDTO>orderProductDTO = orderODTO.getOrderProductsDTO();
        List<OrderProducts>orderProducts = new ArrayList<>();
        for (OrderProductDTO opDAO : orderProductDTO) {
            OrderProducts op = new OrderProducts();
            op.setOrder(order);
            op.setProduct(productRepository.getById(opDAO.getProduct_id()));
            op.setPrice(opDAO.getPrice());
            op.setQuantity(opDAO.getQuantity());
            orderProducts.add(op);
        }
        return orderProducts;
    }

    public List<OrderO> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderO getByID(Long id){
        return orderRepository.getById(id);
    }

    public List<OrderProducts> getProductsByOrderID(Long id) {
        return orderProductsRepository.findByOrderId(id);
    }

    public int setPaid(Long order_id, Long paidDate) {
        Date newDate = new Date(paidDate);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(newDate);
        Timestamp paidDateT = Timestamp.valueOf(str);
        return orderRepository.setPaidDate(paidDateT, order_id);
    }

    public int delete(Long id) {
        int deletedOrderProducts = orderProductsRepository.deleteByOrderId(id);
        return deletedOrderProducts += orderRepository.deleteById(id);
    }

}
