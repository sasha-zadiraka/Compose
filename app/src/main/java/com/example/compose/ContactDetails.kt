package com.example.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ContactDetails(contact: Contact, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ContactPhoto(contact)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = listOfNotNull(contact.name, contact.surname).joinToString(" "),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = contact.familyName,
                style = MaterialTheme.typography.h5,
            )
            if (contact.isFavorite) {
                Image(
                    painter = painterResource(android.R.drawable.star_big_on),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(24.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        InfoRow(label = stringResource(R.string.phone), value = contact.phone)
        InfoRow(label = stringResource(R.string.address), value = contact.address)
        contact.email?.let {
            InfoRow(label = stringResource(R.string.email), value = it)
        }
    }
}

@Composable
fun ContactPhoto(contact: Contact) {
    if (contact.imageRes != null) {
        Image(
            painter = painterResource(contact.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(width = 128.dp, height = 80.dp),
        )
    } else {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(R.drawable.circle),
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(72.dp),
            )
            Text(
                text = contact.name.take(1) + contact.familyName.take(1),
                style = MaterialTheme.typography.h6,
            )
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.subtitle1.copy(
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Normal,
            ),
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.weight(1f),
        )
    }
}

@Preview(name = "ProfileWithoutPhotoPreview", showSystemUi = true)
@Composable
fun ProfileWithoutPhotoPreview() {
    ContactDetails(
        Contact(
            name = "Евгений",
            surname = "Андреевич",
            familyName = "Лукашин",
            isFavorite = true,
            phone = "+7 495 495 95 95",
            address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
            email = "ELukashin@practicum.ru",
        )
    )
}

@Preview(name = "ProfileWithPhotoPreview", showSystemUi = true)
@Composable
fun ProfileWithPhotoPreview() {
    ContactDetails(
        Contact(
            name = "Василий",
            familyName = "Кузякин",
            imageRes = R.drawable.photo,
            phone = "---",
            address = "Ивановская область, дер. Крутово, д. 4",
        )
    )
}
