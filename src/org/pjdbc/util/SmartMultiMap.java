package org.pjdbc.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SmartMultiMap<K, V extends SmartMultiMap.Notifiable> extends MultiMap<K, V> {
    public interface Notifiable {
	public void notify (String methodName, SmartMultiMap m);}
    public void clear () {
	for (Notifiable n : super.allValues()) n.notify("clear", this);
	super.clear();}
    public boolean containsKey (Object key) {
	for (Notifiable n : super.allValues()) n.notify("containsKey", this);
	return super.containsKey(key);}
    public boolean containsValue (V value) {
	for (Notifiable n : super.allValues()) n.notify("containsValue", this);
	return super.containsValue(value);}
    public Set<Map.Entry<K, V>> entrySet () {
	for (Notifiable n : super.allValues()) n.notify("entrySet", this);
	return super.entrySet();}
    public boolean equals (Object o) {
	for (Notifiable n : super.allValues()) n.notify("equals", this);
	return super.equals(o);}
    public V get (Object key) {
	for (Notifiable n : super.allValues()) n.notify("get", this);
	return super.get(key);}
    public int hashCode () {
	for (Notifiable n : super.allValues()) n.notify("hashCode", this);
	return super.hashCode();}
    public boolean isEmpty () {
	for (Notifiable n : super.allValues()) n.notify("isEmpty", this);
	return super.isEmpty();}
    public Set<K> keySet () {
	for (Notifiable n : super.allValues()) n.notify("keySet", this);
	return super.keySet();}
    public V put (K key, V value) {
	for (Notifiable n : super.allValues()) n.notify("put", this);
	return super.put(key, value);}
    public void putAll (Map<? extends K, ? extends V> m) {
	for (Notifiable n : super.allValues()) n.notify("putAll", this);
	super.putAll(m);}
    public V remove (Object key) {
	for (Notifiable n : super.allValues()) n.notify("remove", this);
	return super.remove(key);}
    public int size () {
	for (Notifiable n : super.allValues()) n.notify("size", this);
	return super.size();}
    public Collection<V> values() {
	for (Notifiable n : super.allValues()) n.notify("values()", this);
	return super.values();}}
