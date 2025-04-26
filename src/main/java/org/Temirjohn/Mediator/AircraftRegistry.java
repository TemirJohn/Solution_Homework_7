package org.Temirjohn.Mediator;

import java.util.*;
public class AircraftRegistry {
    private static final List<Aircraft> list = new ArrayList<>();
    public static void register(Aircraft a) { list.add(a); }
    public static List<Aircraft> getAll() { return Collections.unmodifiableList(list); }
}