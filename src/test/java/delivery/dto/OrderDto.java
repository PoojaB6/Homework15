package delivery.dto;

public class OrderDto {

    String Status;
    int courierId;
    String customerName;
    String customerPhone;
    String comment;
    long id;

//Constructor

    public OrderDto(String customerName, String customerPhone, String comment) {
        Status ="OPEN";
        this.courierId = 0;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.comment = comment;
        this.id = 0;
    }
}
