package com.mobileexample.canonigo.utils.screens

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mobileexample.canonigo.R
import com.mobileexample.canonigo.model.Character
import com.mobileexample.canonigo.model.Location
import com.mobileexample.canonigo.model.Origin
import com.mobileexample.canonigo.ui.theme.RickAndMortyTheme
import com.mobileexample.canonigo.utils.genderIcon

import com.mobileexample.canonigo.utils.statusDetailsColor


@Composable
fun CharacterDetailsScreen(
    character: Character,
    origin: Origin,
    location: Location,
    navController: NavController
) {
    var originexpand by remember { mutableStateOf(false) }
    var locationexpand by remember { mutableStateOf(false) }
    var episodeexpand by remember { mutableStateOf(false) }
    val cardColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.surface,
        animationSpec = tween(durationMillis = 500),
    )

    Surface(color = MaterialTheme.colorScheme.background) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(MaterialTheme.colorScheme.onSurface)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(50.dp))


                AsyncImage(
                    model = character.image,
                    error = painterResource(R.drawable.error),
                    placeholder = painterResource(R.drawable.loading_screen),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(4.dp, statusDetailsColor(character.status), CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Dimension ID: ${character.id}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )

                Row {
                    Image(
                        painter = painterResource(genderIcon(character.gender)),
                        contentDescription = "gender",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary)
                    )
                    Text(
                        text = character.gender,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }


                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = cardColor),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(16.dp)
                            )
                            .fillMaxWidth()
                            .padding(8.dp)
                            .then(
                                if (origin.name.isBlank() || origin.name == "unknown") Modifier
                                else Modifier.clickable { originexpand = !originexpand }
                            )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.originimage),
                                contentDescription = "Origin Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(20.dp))
                            )

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(R.string.lblOrigin),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = origin.name,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }


                            if (origin.name.isNotBlank() && origin.name != "unknown") {
                                Icon(
                                    painter = if (originexpand) {
                                        painterResource(id = R.drawable.arrow_dropup)
                                    } else {
                                        painterResource(id = R.drawable.drop_down)
                                    },
                                    contentDescription = "Dropdown",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        if (originexpand) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(
                                    text = origin.url,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(start = 10.dp)

                                )
                            }

                        }
                    }

                    Card(
                        colors = CardDefaults.cardColors(containerColor = cardColor),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(16.dp)
                            )
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { locationexpand = !locationexpand }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.locationimage),
                                contentDescription = "Location Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(20.dp))
                            )

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(R.string.lblLocation),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = location.name,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                         

                            if (location.name.isNotBlank() && location.name != "unknown") {
                                Icon(
                                    painter = if (locationexpand) {
                                        painterResource(id = R.drawable.arrow_dropup)
                                    } else {
                                        painterResource(id = R.drawable.drop_down)
                                    },
                                    contentDescription = "Dropdown",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                    if (locationexpand) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = location.url,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(start = 10.dp)

                            )
                        }

                    }
                }}

                    Card(
                        colors = CardDefaults.cardColors(containerColor = cardColor),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(16.dp)
                            )
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { episodeexpand = !episodeexpand }
                    ) {
                        Row (verticalAlignment = Alignment.CenterVertically , modifier = Modifier.padding(15.dp)){
                            Text(
                                text = stringResource(R.string.lblEpisodes),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,

                            )
                            Icon(
                                painter = when (episodeexpand){
                                    true ->  painterResource(id = R.drawable.arrow_dropup)
                                    false -> painterResource(id = R.drawable.drop_down)
                                },
                                contentDescription = "Dropdown",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(start = 150.dp)
                            )
                        }
                        if (episodeexpand) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(8.dp)
                            ) {
                                character.episode.map { it.substringAfterLast("/") }
                                    .forEach { episodeNumber ->
                                        Text(
                                            text = "Episode $episodeNumber",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                    }
                            }
                        }

                        }
                    }


                }
            }
}








