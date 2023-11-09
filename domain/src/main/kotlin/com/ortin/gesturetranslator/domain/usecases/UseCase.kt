package com.ortin.gesturetranslator.domain.usecases

interface UseCase<T, R> {
    operator fun invoke(data: T) : R
}
