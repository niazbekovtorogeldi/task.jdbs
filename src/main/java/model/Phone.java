package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Phone {

    private Long id;
    private String model;
    private String brand;
    private int price;
    private Long user_id;

    public Phone(String model, String brand, int price, Long user_id) {
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.user_id = user_id;
    }
}
