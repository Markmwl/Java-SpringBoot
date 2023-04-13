package com.mark.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "school")
public class School {
    public String name;
    public Integer age;
    public String sex;
    public Boolean teacher;

    public Date birthday;
    public Map<String,String> maps;
    public Map<String,String> maps2;
    public List<dog> lisDog;

    public dog dog;
    public String[] arr;
    public String[] arr2;

    public Map<String,dog> dogMap;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class dog{
    public String name;
    public Integer age;
}
