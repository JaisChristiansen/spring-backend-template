package com.jaisgroup.dnd.util;

import java.util.Collection;

/**
 * @author Jais Christiansen
 */
public class CollectionUtil {

    public static <T> boolean isNullOrEmpty(Collection<T> col) {
        return (col == null || col.isEmpty());
    }

    public static <T> boolean isNotNullOrEmpty(Collection<T> col) {
        return !isNullOrEmpty(col);
    }
}
