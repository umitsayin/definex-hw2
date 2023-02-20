package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 6284431162632557786L;
    private UUID id;

    public BaseModel(UUID id){
        this.id = id;
    }
}
