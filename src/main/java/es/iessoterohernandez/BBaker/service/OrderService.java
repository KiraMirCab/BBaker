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

    @Autowired
    StatusService statusSetService;

    public OrderO mapToOrder(OrderODTO orderODTO) {
        OrderO order = new OrderO();
        order.setCreationDate(orderODTO.getCreationDate());
        order.setPaidDate(orderODTO.getPaidDate());
        order.setTotal(orderODTO.getTotal());
        if (orderODTO.getUser_id() == 0) {
            order.setUser(null);
        } else {
            order.setUser(userRepository.findById(orderODTO.getUser_id()));
        }
        return order;        
    }

    public OrderODTO mapToOrderDTO(OrderO order) {
        OrderODTO odto = new OrderODTO();
        odto.setCreationDate(order.getCreationDate());
        odto.setPaidDate(order.getPaidDate());
        odto.setTotal(order.getTotal());
        if (order.getUser() != null) {
            odto.setUser_id(order.getUser().getId());
        } 
        odto.setOrderProductsDTO(this.mapToOrderProductsDTO(odto, order));
        return odto;
    }

    public OrderO addNewOrder(OrderO order){
        orderRepository.save(order);
        Long status_id = (long) 1;
        return statusSetService.setOrderStatus(status_id, order.getId());
    }
    
    public String saveOrderProducts(List<OrderProducts> orderProducts){
        orderProductsRepository.saveAll(orderProducts);
        return "Order products correctly saved";
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

    public List<OrderProductDTO> mapToOrderProductsDTO(OrderODTO orderODTO, OrderO order){
        List<OrderProducts>orderProducts = orderProductsRepository.findByOrderId(order.getId());
        List<OrderProductDTO>orderProductDTO = new ArrayList<>();
       
        for (OrderProducts op : orderProducts) {
            OrderProductDTO opDTO = new OrderProductDTO();
            opDTO.setProduct_id(op.getProduct().getId());
            opDTO.setPrice(op.getPrice());
            opDTO.setQuantity(op.getQuantity());
            orderProductDTO.add(opDTO);
        }
        return orderProductDTO;
    }

    public List<OrderO> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<OrderODTO> getMyOrders(Long id) {
        List<OrderO> list = orderRepository.findByUser_id(id);
        List<OrderODTO> myList = new ArrayList<OrderODTO>();
        for (OrderO order : list) {
            OrderODTO myOrder = this.mapToOrderDTO(order);
            myList.add(myOrder);
        }
        return myList;
    }

    public OrderO getByID(Long id){
        return orderRepository.getById(id);
    }

    public List<OrderProducts> getProductsByOrderID(Long id) {
        return orderProductsRepository.findByOrderId(id);
    }

    /* Este metodo combierte la fecha del formato Long recibido del frontend
     * al fromato Timestamp necesario para guardar en la BD,
     * se asigna al pedido extraído de la BD, y se vuelve a guardar
     * a través del método 'setOrderStatus' que también actualiza
     * el estado del pedido
     */
    public OrderO setPaid(Long order_id, Long paidDate) {
        Date newDate = new Date(paidDate);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(newDate);
        Timestamp paidDateT = Timestamp.valueOf(str);
        OrderO order = orderRepository.getById(order_id);
        order.setPaidDate(paidDateT);
        return statusSetService.setOrderStatus((long) 2, order.getId());
     
        // statusSetService.addNewStatusChange((long) 2, getByID(order_id));
        // return orderRepository.setPaidDate(paidDateT, order_id);
    }

    public int delete(Long id) {
        int deletedOrderProducts = orderProductsRepository.deleteByOrderId(id);
        int deletedStatusChanges = statusSetService.deleteByOrderId(id);
        return deletedOrderProducts + deletedStatusChanges + orderRepository.deleteById(id);
    }

}
