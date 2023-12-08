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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import timber.log.Timber

@Composable
fun DeveloperCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    gitHubUrl: String? = null,
    secondContact: String? = null,
    @DrawableRes iconId: Int = R.drawable.icon_ortin_logo_without_text
) {
    val localDimensions = LocalDimensions.current
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Spacer(modifier = Modifier.height(localDimensions.verticalStandard))

        Text(
            modifier = Modifier.padding(horizontal = localDimensions.horizontalMedium),
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight(600),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        Text(
            modifier = Modifier.padding(horizontal = localDimensions.horizontalMedium),
            text = description,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = localDimensions.verticalTiny,
                    horizontal = localDimensions.horizontalMedium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(localDimensions.horizontalSmall)
        ) {
            Image(
                modifier = Modifier
                    .size(localDimensions.horizontalXLarge)
                    .clip(CircleShape),
                painter = painterResource(id = iconId),
                contentDescription = null
            )

            gitHubUrl?.let {
                PrimaryTextButton(
                    text = stringResource(id = R.string.github),
                    onClick = {
                        uriHandler.openUri(gitHubUrl)
                        Timber.d("Go to $title GitHub account")
                    },
                    modifier = Modifier.weight(0.45f)
                )
            }

            secondContact?.let {
                val buttonText = when (secondContact.substring(0, 12)) {
                    "https://t.me" -> stringResource(id = R.string.tg)
                    "https://vk.c" -> stringResource(id = R.string.vk)
                    else -> stringResource(id = R.string.email)
                }

                if (secondContact.indexOf("@") < 0) {
                    PrimaryTextButton(
                        text = buttonText,
                        onClick = {
                            uriHandler.openUri(secondContact)
                            Timber.d("Go to $secondContact")
                        },
                        modifier = Modifier.weight(0.45f)
                    )
                } else {
                    PrimaryTextButton(
                        text = buttonText,
                        onClick = {
                            context.openEmailApp(secondContact)
                        },
                        modifier = Modifier.weight(0.45f)
                    )
                }
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
        Timber.d("Email app was opened with mail to $email")
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
                secondContact = "Poroshin.info@gmail.com"
            )
        }
    }
}
