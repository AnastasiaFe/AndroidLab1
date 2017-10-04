package ua.nure.fedorenko.lab1.data.mapper;

import android.support.annotation.NonNull;

public interface Mapper<T, K> {

    @NonNull
    T convert(@NonNull K k);
}
