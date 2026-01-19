package org.example.project.core.domain

sealed interface Response <out  D, out E: Error> {
    data class Success<out D>(val data: D) : Response<D, Nothing>
    data class Failed<out E: org.example.project.core.domain.Error>(val error: E) : Response<Nothing, E>


}