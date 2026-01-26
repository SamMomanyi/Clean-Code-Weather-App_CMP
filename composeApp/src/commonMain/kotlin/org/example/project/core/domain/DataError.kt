package org.example.project.core.domain

sealed interface DataError : Error{
    enum class Remote : DataError{
        REQUEST_TIMEOUT,
        TOO_MANY_REQUEST,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN,

        UNKNOWN_PLACE
    }
    enum class Local: DataError{
        NOT_SUPPORTED,
        PERMISSION_ERROR,
        GEOLOCATIONFAILED,
        NOT_FOUND,
        DISK_FULL,
        UNKNOWN
    }


}