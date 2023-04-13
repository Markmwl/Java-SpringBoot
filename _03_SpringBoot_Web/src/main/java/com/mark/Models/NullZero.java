package com.mark.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mark.Aop.ModelZero;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@ModelZero
@Data
public class NullZero {

    @JsonProperty("ID")
    public BigDecimal Id;

    @JsonProperty("NAME")
    public String Name;

    @JsonProperty("SEX")
    public String Sex;

    @JsonProperty("AGE")
    private BigDecimal Age;

    @JsonProperty("ADDRESS")
    public String Address;

    @JsonProperty("COUNT")
    private BigDecimal Count;
}
