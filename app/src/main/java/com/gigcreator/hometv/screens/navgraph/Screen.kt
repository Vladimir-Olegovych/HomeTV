package com.gigcreator.hometv.screens.navgraph

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Main : Screen

}