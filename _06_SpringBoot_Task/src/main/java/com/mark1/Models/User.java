package com.mark1.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public String id;
    public String name;
    public String sex;
    public String age;
    public String address;
    public String phonenumber;
}
