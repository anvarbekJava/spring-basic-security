package uz.pdp.basicsecurity.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ProductDto {

    @NotNull(message = "Product nomini kiriting")
    private String name;

    @NotNull(message = "Product price ni kiriting")
    private Double price;
}
