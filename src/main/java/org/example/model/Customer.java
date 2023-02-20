package org.example.model;

import lombok.Data;
import org.example.util.MonthEnum;

import java.util.UUID;

@Data
public class Customer extends BaseModel{
    private String name;
    private String sector;
    private MonthEnum monthEnum;

    public Customer(UUID id, String name, String sector, MonthEnum monthEnum) {
        super(id);
        this.name = name;
        this.sector = sector;
        this.monthEnum = monthEnum;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", monthEnum=" + monthEnum +
                '}';
    }
}
