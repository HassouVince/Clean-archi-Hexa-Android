package fr.systemathicdev.commons

interface ApiProvider<S> {
    fun client(): S
}