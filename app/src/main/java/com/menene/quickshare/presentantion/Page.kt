package com.menene.quickshare.presentantion

import androidx.annotation.DrawableRes
import com.menene.quickshare.R

data class Page(
    val title: String,
    var description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Welcome to NoteHub",
        description = "NoteHub is a note taking app that allows you to",
        image = R.drawable.onboarding_1
    ),
    Page(
        title = "Create Notes",
        description = "Create notes and organize them into categories.",
        image = R.drawable.onboarding_2
    ),
    Page(
        title = "Set Reminders",
        description = "Set reminders for your notes and to-do lists.",
        image = R.drawable.onboarding_3
    ),
)
