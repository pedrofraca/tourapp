package io.github.pedrofraca.data.datasource

interface ReadOnlyDataSource<T> {
    fun get(): List<T>
}

interface ReadOnlyDataSourceSingle<T> {
    fun get(): T
}

interface ReadOnlyDataSourceWithParam<T, S> {
    fun get(param : S): T
}

interface WriteDataSourceSingleWithParams<T, S> : ReadOnlyDataSourceWithParam<T, S> {
    fun save(item: T)
}

interface WriteDataSource<T> : ReadOnlyDataSource<T> {
    fun save(item: T)
}