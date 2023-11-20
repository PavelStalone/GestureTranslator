package com.ortin.gesturetranslator.ui.components.cards

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.gesturetranslator.ui.R
import com.ortin.gesturetranslator.ui.components.buttons.PrimaryTextButton
import com.ortin.gesturetranslator.ui.theme.GestureTranslatorTheme
import com.ortin.gesturetranslator.ui.theme.LocalDimensions
import com.ortin.gesturetranslator.ui.theme.surfaceContainerLow
import timber.log.Timber


@Composable
fun DeveloperCard(
    title: String,
    description: String,
    gitHubUrl: String?,
    email: String?,
    modifier: Modifier  = Modifier,
    @DrawableRes iconId: Int = R.drawable.icon_ortin_logo_without_text
) {
    val localDimensions = LocalDimensions.current
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = surfaceContainerLow
        )
    ) {
        Spacer(modifier = Modifier.height(localDimensions.verticalStandard))

        Text(
            modifier = Modifier.padding(horizontal = localDimensions.horizontalMedium),
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight(600),
                fontSize = 24.sp
            )
        )

        Text(
            modifier = Modifier.padding(horizontal = localDimensions.horizontalMedium),
            text = description,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium
        )

        Row(
            modifier = Modifier.padding(
                horizontal = localDimensions.horizontalMedium,
                vertical = localDimensions.verticalTiny
            ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null
            )

            gitHubUrl?.let {
                Spacer(modifier = Modifier.width(localDimensions.horizontalSmall))
                PrimaryTextButton(
                    text = stringResource(id = R.string.github),
                    onClick = {
                        uriHandler.openUri(gitHubUrl)
                        Timber.d("Go to $title GitHub account")
                    },
                    modifier = Modifier
                        .height(localDimensions.verticalXlarge)
                        .weight(0.4f)
                )
            }

            email?.let {
                Spacer(modifier = Modifier.width(localDimensions.horizontalSmall))
                PrimaryTextButton(
                    text = stringResource(id = R.string.email),
                    onClick = {
                        context.openEmailApp(email)
                        Timber.d("Email app was opened")
                    },
                    modifier = Modifier
                        .height(localDimensions.verticalXlarge)
                        .weight(0.4f)
                )
            }

        }
    }
}

/**
 * Method for opening email app
 *
 * @param [email] email address, to which will be open sending email page
 */
fun Context.openEmailApp(email: String) {
    try {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$email")
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Timber.e("No email app is available")
    }
}

@Preview
@Composable
fun DeveloperCardPreview() {
    GestureTranslatorTheme {
        Surface {
            DeveloperCard(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                title = "PavelStalone",
                description = "Вообще красавчик машина убийца",
                gitHubUrl = "https://github.com/PavelStalone",
                email = "poroshin.info@gmail.com"
            )
        }
    }
}
