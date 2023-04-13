package com.mark1.Common;

import com.mark1.Models.XxlUser;

public class TestRand {
    public static void main(String[] args) {
        RandInfo rand = new RandInfo();
        XxlUser person = new XxlUser();

        for (int i = 1;i <= 10; i++){
            String familyName = rand.getFamilyName();
            String[] nameAndSex = rand.getNameAndSex(rand.getSex());
            String name = nameAndSex[0];
            String sex = nameAndSex[1];
            int age = rand.getAge();
            String phoneNumber = rand.getPhoneNum();
            String address = rand.getRoad();

            person.setName(familyName.concat(name));
            person.setSex(sex);
            person.setAge(""+age);
            person.setPhonenumber(phoneNumber);
            person.setAddress(address);
            System.out.println(person);
        }
    }
}
