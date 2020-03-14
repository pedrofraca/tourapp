package io.github.pedrofraca.data.datasource

import io.reactivex.Observable

interface ReadOnlyDataSource<T> {
    fun get(): Observable<List<T>>
}

interface WriteDataSource<T> : ReadOnlyDataSource<T> {
    fun save(item: T)
}