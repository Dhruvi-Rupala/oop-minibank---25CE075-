package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.annotation.Id;

public class Repository<T> {

    private HashMap<String, T> data = new HashMap<>();

    public T save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null.");
        }

        try {
            Class<?> type = entity.getClass();

            while (type != null) {
                for (Field field : type.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        field.setAccessible(true);
                        Object value = field.get(entity);

                        if (value == null) {
                            throw new IllegalArgumentException("ID cannot be null.");
                        }

                        data.put(value.toString(), entity);
                        return entity;
                    }
                }

                type = type.getSuperclass();
            }

            throw new IllegalArgumentException("No @Id field found.");

        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Unable to read entity ID.", e);
        }
    }

    public T findById(String id) {
        return data.get(id);
    }

    public ArrayList<T> findAll() {
        return new ArrayList<>(data.values());
    }

    public boolean delete(String id) {
        return data.remove(id) != null;
    }

    @SafeVarargs
    public final void saveAll(T... items) {
        for (T item : items) {
            save(item);
        }
    }

    public int count() {
        return data.size();
    }

    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        double total = 0;

        for (N value : values) {
            total += value.doubleValue();
        }

        return total / values.length;
    }

    public Map<String, T> getAllAsMap() {
        return new HashMap<>(data);
    }
}