package com.example.service.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestUtil {

    public static <T> List<T> getListOfObjects(T ... objects) {
        return Arrays.stream(objects).collect(Collectors.toList());
    }
}
