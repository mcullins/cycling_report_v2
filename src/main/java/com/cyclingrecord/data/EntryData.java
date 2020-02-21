package com.cyclingrecord.data;

import com.cyclingrecord.models.Entry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EntryData {

    private static final Map<Integer, Entry> entires = new HashMap<>();

    public static Collection<Entry> getAll() {
        return entires.values();
    }

    public static void add(Entry entry) {
        entires.put(entry.getId(), entry);
    }
}

