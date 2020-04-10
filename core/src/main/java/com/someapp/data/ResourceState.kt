package com.someapp.data

class ResourceState<T> private constructor(
    private val status: Status,
    val data: T? = null,
    val t: Throwable? = null
) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    fun isLoading() = status == Status.LOADING

    fun isSuccess() = status == Status.SUCCESS

    fun isError() = status == Status.ERROR

    companion object {

        fun <T> success(data: T): ResourceState<T> {
            return ResourceState(
                status = Status.SUCCESS,
                data = data
            )
        }

        fun <T> error(t: Throwable? = null): ResourceState<T> {
            return ResourceState(
                status = Status.ERROR,
                t = t
            )
        }

        fun <T> loading(): ResourceState<T> {
            return ResourceState(
                status = Status.LOADING
            )
        }
    }
}