package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) },
                ) { innerPadding ->
                    ContactDetails(
                        Contact(
                            name = "Евгений",
                            surname = "Андреевич",
                            familyName = "Лукашин",
                            isFavorite = true,
                            phone = "+7 495 495 95 95",
                            address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                            email = "ELukashin@practicum.ru",
                        ),
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
