package org.example.project.core.domain

sealed class Response <out  T>
    data class Success<T>(val data :  T): Response<T>()
    data class Failed(val ex: Exception?):Response<Nothing>()
    data object isLoading : Response<Nothing>()
