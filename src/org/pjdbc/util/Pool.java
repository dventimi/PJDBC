package org.pjdbc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Pool<K,V> {
    private static Random r = new Random();

    private Map<K, List<V>> p = new HashMap<K, List<V>>();

    public boolean put (K key, V value) {
	return ((p.containsKey(key)) ? p.get(key) : new ArrayList<V>()).add(value);}

    public V take (K key) {
	return (p.containsKey(key) && p.get(key).size()>0) ? p.get(key).get(r.nextInt(p.get(key).size())) : null;}}
