package com.bryanvalc.pluginTemplateKt.data

import kotlinx.serialization.Serializable

@Serializable
data class DummyRTag(
    val someNestedStuff: SomeNestedStuff
)


@Serializable
data class SomeNestedStuff(
    val someData: Int
)
