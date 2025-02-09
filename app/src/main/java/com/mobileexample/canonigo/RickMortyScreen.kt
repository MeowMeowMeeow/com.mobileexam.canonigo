

package com.mobileexample.canonigo



import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobileexample.canonigo.data.Wubalubadubdub
import com.mobileexample.canonigo.model.Character
import com.mobileexample.canonigo.ui.theme.RickAndMortyTheme
import com.mobileexample.canonigo.utils.genderIcon
import com.mobileexample.canonigo.utils.speciesIcon
import com.mobileexample.canonigo.utils.statusIcon
import com.mobileexample.canonigo.utils.typeIcon



@Composable
fun RickandMortyApp(
    characters: List<Character>,
    modifier: Modifier = Modifier,
) {
    val isDarkTheme = isSystemInDarkTheme()
    val scaffoldBackgroundColor = if (isDarkTheme) Color(0xFFFFFFFF) else Color(0xFF97CE4C)

    Scaffold(
        topBar = {
            RickandMortyAppBar()
        },
        containerColor = scaffoldBackgroundColor,
        content = { innerPadding ->
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(innerPadding)
            ) {
                LazyColumn(Modifier.padding(10.dp)) {
                    itemsIndexed(characters) { index, character ->
                        RickandMortyList(character = character, modifier = Modifier.padding(20.dp))
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickandMortyAppBar() {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Rick and Morty Logo",
                    modifier = Modifier.size(150.dp)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ),
    )
}

@Composable
fun RickandMortyList(
    character: Character,
    modifier: Modifier = Modifier,
) {
    val isDarkTheme = isSystemInDarkTheme()
    var moreDetails by remember { mutableStateOf(false) }
    var displayedCharacter by remember { mutableStateOf(character) }
    var isPresidentMorty by remember { mutableStateOf(false) }

    val cardColor by animateColorAsState(
        targetValue = if (isPresidentMorty) Color.Black else MaterialTheme.colorScheme.surface,
        animationSpec = tween(durationMillis = 500),
    )

    val textColor = if (isPresidentMorty) Color.White else MaterialTheme.colorScheme.onSurface

    if (moreDetails && character.name == R.string.President_Morty) {
        displayedCharacter = Character(
            name = R.string.Evil_Morty,
            status = R.string.Alive,
            species = R.string.Human,
            type = R.string.type,
            gender = R.string.Male,
            nameO = R.string.nameO22,
            nameL = R.string.nameL22,
            image = R.drawable.evilmorty
        )

        isPresidentMorty = true
    } else {
        displayedCharacter = character
        isPresidentMorty = false
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .border(2.dp, if (isDarkTheme) Color(0xFFFFFFFF) else Color(0xFF97CE4C), shape = RoundedCornerShape(16.dp))
            .clickable {
                moreDetails = !moreDetails
            }
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Image(
                    painter = painterResource(displayedCharacter.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Text(
                        text = stringResource(displayedCharacter.name),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(statusIcon(displayedCharacter.status)),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(displayedCharacter.status),
                            style = MaterialTheme.typography.bodyMedium,
                            color = when (displayedCharacter.status) {
                                R.string.Alive -> Color.Green
                                R.string.Dead -> Color.Red
                                else -> Color(0xFFFFA500)
                            }
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(speciesIcon(displayedCharacter.species)),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(displayedCharacter.species),
                            style = MaterialTheme.typography.bodyMedium,
                            color = textColor
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(typeIcon(displayedCharacter.type)),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(displayedCharacter.type),
                            style = MaterialTheme.typography.bodyMedium,
                            color = textColor
                        )
                    }
                }
            }

            Text(
                text = stringResource(R.string.more_details),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.clickable { moreDetails = !moreDetails }
            )

            if (moreDetails) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(genderIcon(displayedCharacter.gender)),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            colorFilter = when(displayedCharacter.gender){
                                R.string.Male -> ColorFilter.tint(Color.Blue)
                                R.string.Female -> ColorFilter.tint(Color.Red)
                                else -> ColorFilter.tint(Color(0xFFFFA500))
                            }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(displayedCharacter.gender),
                            style = MaterialTheme.typography.bodySmall,
                            color = when (displayedCharacter.gender){
                                R.string.Male -> Color.Blue
                                R.string.Female -> Color.Red
                                else -> Color(0xFFFFA500)
                            }
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.origin),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(displayedCharacter.nameO),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.location),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(displayedCharacter.nameL),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}




@Preview(name = "Light Theme", showBackground = true)
@Composable
fun LightThemePreview() {
    val characters = Wubalubadubdub().loadCharacters()
    RickAndMortyTheme(darkTheme = false) {
        RickandMortyApp(characters = characters)
    }
}

@Preview(name = "Dark Theme", showBackground = true)
@Composable
fun DarkThemePreview() {
    val characters = Wubalubadubdub().loadCharacters()
    RickAndMortyTheme(darkTheme = true) {
        RickandMortyApp(characters = characters)
    }
}

