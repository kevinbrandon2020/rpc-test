package com.myjson;

import com.service.Request;
import com.service.ServiceDescriptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
//        String[] strs = {"抑郁", "抑郁症", "依依", "一夜情"};
//        Arrays.sort(strs);
//        List<Integer> collect = Arrays.stream(strs).map(String::hashCode).collect(Collectors.toList());
//        System.out.println(collect);
//        Collections.reverse(Arrays.asList(strs));
//        System.out.println(Arrays.toString(strs));
        JsonDecoder jsonDecoder = new JsonDecoder();
        JsonEncoder jsonEncoder = new JsonEncoder();
        Request request = new Request();
        request.setParams(new Object[]{1,2});
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setParamTypes(new String[]{"int","int"});
        serviceDescriptor.setMethod("add");
        serviceDescriptor.setReturnType("int");
        serviceDescriptor.setClazz("com.example.CalcServiceImp");
        request.setService(serviceDescriptor);
        byte[] encode = jsonEncoder.encode(serviceDescriptor);
        System.out.println(Arrays.toString(encode));
        byte[] bytes = {123, 34, 112, 97, 114, 97, 109, 115, 34, 58, 91, 49, 44, 50, 93, 44, 34, 115, 101, 114, 118, 105, 99, 101, 34, 58, 123, 125, 125};
        System.out.println(jsonDecoder.decode(encode, ServiceDescriptor.class).toString());
    }
}
