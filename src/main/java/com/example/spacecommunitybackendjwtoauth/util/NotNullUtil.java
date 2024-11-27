package com.example.spacecommunitybackendjwtoauth.util;

import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.Objects;

public class NotNullUtil {
    public static void hasNullFields(Object record) {
        boolean isNull = Arrays.stream(record.getClass().getRecordComponents())
                .map(component -> getFieldValue(record, component))
                .anyMatch(Objects::isNull);
        if(isNull) throw new IllegalArgumentException();
    }

    private static Object getFieldValue(Object record, RecordComponent component) {
        try {
            return component.getAccessor().invoke(record);
        }
        catch(Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
