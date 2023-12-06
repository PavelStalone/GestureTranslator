package com.ortin.gesturetranslator.main.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.buttons.PrimaryTextButton
import com.ortin.gesturetranslator.ui.components.cards.DeveloperCard
import com.ortin.gesturetranslator.ui.models.DeveloperCardInfo
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions
import timber.log.Timber

private val developersList = listOf(
    DeveloperCardInfo(
        "PavelStalone",
        "Android developer and ML engineer",
        R.drawable.pavel_stalone,
        "https://github.com/PavelStalone",
        "https://vk.com/pasha_just_pasha"
    ),
    DeveloperCardInfo(
        "Glebix",
        "ML engineer, Android and Web developer",
        R.drawable.gleb,
        "https://github.com/PoroshinGA",
        "https://t.me/PoroshinG"
    ),
    DeveloperCardInfo(
        "qondeeter", "UI/UX - designer and data engineer",
        R.drawable.qondeeter,
        "https://github.com/kond1ter",
        "https://t.me/qondeeter"
    ),
    DeveloperCardInfo(
        "Sova",
        "UI/UX - writer," +
                " Android developer and data engineer",
        R.drawable.sova,
        "https://github.com/N1kySSS",
        "https://vk.com/sova___666"
    ),
    DeveloperCardInfo(
        "Sever",
        "Teach lead and mentor",
        R.drawable.sever,
        "https://github.com/jacksever",
        "https://t.me/jasever"
    ),
    DeveloperCardInfo(
        "LittlePony00",
        "Android developer",
        R.drawable.little_pony00,
        "https://github.com/LittlePony00",
        "https://t.me/littlepony01"
    ),
    DeveloperCardInfo(
        "AccessAndrei",
        "ML engineer",
        R.drawable.access_andrei,
        "https://github.com/AccessAndrei",
        "https://t.me/AxAndrei"
    )
)

@Composable
fun AppInfoScreen(
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val uriHandler = LocalUriHandler.current

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(dimensions.horizontalMedium),
        verticalArrangement = Arrangement.spacedBy(dimensions.verticalStandard)
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_ortin_logo_without_text),
                    contentDescription = null
                )
            }
        }
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.info_screen_text),
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 15.sp),
                    textAlign = TextAlign.Center
                )
            }
        }
        item {
            PrimaryTextButton(
                text = stringResource(id = R.string.application_website),
                onClick = { /*TODO*/ }
            )
        }
        item {
            PrimaryTextButton(
                text = stringResource(id = R.string.github),
                onClick = {
                    uriHandler.openUri("https://github.com/PavelStalone/GestureTranslator")
                    Timber.d("Go to https://github.com/PavelStalone/GestureTranslator")
                }
            )
        }
        item {
            Text(
                text = stringResource(id = R.string.developers),
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 32.sp)
            )
        }
        items(developersList.size) {
            DeveloperCard(
                title = developersList[it].name,
                description = developersList[it].description,
                iconId = developersList[it].icon,
                gitHubUrl = developersList[it].gitHub,
                secondContact = developersList[it].contact
            )
        }
    }
}

@Preview
@Composable
fun AppInfoScreenPreview() {
    GestureTranslatorTheme {
        Surface {
            AppInfoScreen()
        }
    }
}
