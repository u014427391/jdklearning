package com.example.jdkexample.core.optional.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String name;
    private String mobiTel;
    private String email;
    private Address address;
    private String position;
    public Optional<String> getPosition() {
        return Optional.ofNullable(position);
    }
}
