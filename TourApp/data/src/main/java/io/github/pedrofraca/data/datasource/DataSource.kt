package io.github.pedrofraca.data.datasource

interface ReadOnlyDataSource<T> {
    fun get(): List<T>
}

interface ReadOnlyDataSourceWithParam<T, S> {
    fun get(param : S): T
}

interface WriteDataSource<T> : ReadOnlyDataSource<T> {
    fun save(item: T)
}