package org.pjdbc.util;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MultiMap<K, V> implements Map<K, V> {
    private static Random r = new Random();
    private final Map<K, List<V>> delegate = new HashMap<K, List<V>>();
    public void clear () {
	delegate.clear();}
    public boolean containsKey (Object key) {
	return delegate.containsKey(key);}
    public boolean containsValue (Object value) {
	for (List<V> bucket : delegate.values()) if (bucket.contains(value)) return true;
	return false;}
    public Set<Map.Entry<K, V>> entrySet () {
	Set<Map.Entry<K, V>> entries = new HashSet<Map.Entry<K, V>>();
	for (K key : delegate.keySet()) entries.add(new AbstractMap.SimpleEntry<K,V>(key, get(key)){});
	return entries;}
    public boolean equals (Object o) {
	return delegate.equals(o);}
    public V get (Object key) {
	return (containsKey(key) && delegate.get(key).size()>0) ? delegate.get(key).get(r.nextInt(delegate.get(key).size())) : null;}
    public int hashCode () {
	return delegate.hashCode();}
    public boolean isEmpty () {
	return delegate.isEmpty();}
    public Set<K> keySet () {
	return delegate.keySet();}
    public V put (K key, V value) {
	V returnValue = containsKey(key) ? get(key) : null;
	if (!containsKey(key)) delegate.put(key, new ArrayList<V>());
	if (!delegate.get(key).contains(value)) delegate.get(key).add(value);
	return returnValue;}
    public void putAll (Map<? extends K, ? extends V> m) {
	for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) put(e.getKey(), e.getValue());}
    public V remove (Object key) {
	V returnValue = containsKey(key) ? get(key) : null;
	delegate.remove(key);
	return returnValue;}
    public int size () {
	return delegate.size();}
    public Collection<V> values() {
	Collection<V> view = new ValuesCollection<V>();
    	for (Map.Entry<K, V> e : entrySet()) view.add(e.getValue());
	return view;}
    private class ValuesCollection<V> extends ArrayList<V> {
	public boolean remove (Object o) {
	    MultiMap.this.remove(o);
	    return super.remove(o);}}}
