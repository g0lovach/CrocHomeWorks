package ru.croc.javaschool.tools;

import java.util.*;

/**
 * Класс, в котором описаны способы сортировки мапов
 */
public class MapUtil {
    /**
     * Сортировка по ключу и значению
     * @param map - входной мап
     * @param <K> - тип ключа
     * @param <V> - тип значиения
     * @return - отсортированный мап
     */
    public static <K extends Comparable<K>, V extends Comparable<V>> Map<K, V> sortByValueKey(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<K,V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
               if (o1.getValue().compareTo(o2.getValue()) == 1){
                  return -1;
               }
               else if (o1.getValue().equals(o2.getValue())) {
                   return o1.getKey().compareTo(o2.getKey());
               }

               return  1;
            }

        });
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * Сортировка по ключу (P.S. не используется в конечной версии проекта, но удалять жалко :)   )
     * @param map - входной мап
     * @param <K> - тип ключа
     * @param <V> - тип значения
     * @return - отсортированный мап
     */
    public static <K , V extends Comparable<V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<K,V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue())*-1;
            }

        });
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

}
