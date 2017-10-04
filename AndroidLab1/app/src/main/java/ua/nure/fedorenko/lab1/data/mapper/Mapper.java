package ua.nure.fedorenko.lab1.data.mapper;

public interface Mapper<T, K> {

    T convert(K k);
}
