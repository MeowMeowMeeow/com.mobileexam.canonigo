

package com.mobileexample.canonigo.utils.screens



import android.content.res.Configuration
import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mobileexample.canonigo.R
import com.mobileexample.canonigo.model.Character
import com.mobileexample.canonigo.model.Location
import com.mobileexample.canonigo.model.Origin
import com.mobileexample.canonigo.ui.theme.RickAndMortyTheme
import com.mobileexample.canonigo.utils.speciesIcon
import com.mobileexample.canonigo.utils.statusColor
import com.mobileexample.canonigo.utils.statusIcon
import com.mobileexample.canonigo.utils.typeIcon
import kotlinx.coroutines.delay


@Composable
fun RickandMortyApp(
    modifier: Modifier = Modifier,
   viewmodel: RickViewModel = viewModel(factory = RickViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewmodel.rickMortyUiState.collectAsState()
    val isDarkTheme = isSystemInDarkTheme()
    val scaffoldBackgroundColor = MaterialTheme.colorScheme.onSurface

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { entry ->
            Log.d("Navigation", "Current Route: ${entry.destination.route}")
        }
    }


    Scaffold(
        topBar = {
            RickandMortyAppBar(
                navController = navController,
                navigateUp = { navController.navigateUp() }
            )
        },
        containerColor = scaffoldBackgroundColor,
        content = { innerPadding ->
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(innerPadding)
            ) {
                NavHost(navController = navController, startDestination = "character_list") {
                    composable("character_list")  {
                        when (uiState) {
                            is RickMortyUiState.Loading -> {
                                StarterScreen { }
                            }
                            is RickMortyUiState.Success -> {
                                val characters = (uiState as RickMortyUiState.Success).characters
                                LazyColumn(Modifier.padding(10.dp)) {
                                    itemsIndexed(characters) { _, character ->
                                        RickandMortyList(
                                            character = character,
                                            modifier = Modifier.padding(20.dp),
                                            navController = navController
                                        )
                                    }
                                }
                            }
                            is RickMortyUiState.Error -> {
                                ErrorScreen {}
                            }
                        }
                    }
                    composable("character_details/{characterId}") { backStackEntry ->
                        val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
                        val character = characterId?.let { id ->
                            (uiState as? RickMortyUiState.Success)?.characters?.find { it.id == id }
                        }
                        character?.let {
                            CharacterDetailsScreen( character = it, location = it.location, origin = it.origin, navController = navController)
                        } ?: Text("Character not found", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickandMortyAppBar(

    navigateUp: () -> Unit = {},
    navController: NavController,

) {
    val backStackEntry by navController.currentBackStackEntryFlow.collectAsState(initial = null)
    val isCharacterDetailsScreen = backStackEntry?.destination?.route?.startsWith("character_details/") == true
    var backButtonIcon by remember { mutableStateOf(false) }

    LaunchedEffect(isCharacterDetailsScreen) {
        if (isCharacterDetailsScreen) {
            delay(200)
            backButtonIcon = true
        } else {
            backButtonIcon = false
        }
    }
    TopAppBar(

        title = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),

            ) {

                if (isCharacterDetailsScreen && backButtonIcon  ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "Arrow Back",
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterStart)
                            .clickable { navigateUp() },
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary) ,



                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Rick and Morty Logo",
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.Center)

                )
            }
        },


        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onSurface
        ),
    )
}

@Composable
fun RickandMortyList(
    character: Character,
    modifier: Modifier = Modifier,
    navController: NavHostController =rememberNavController()


) {

    val isDarkTheme = isSystemInDarkTheme()
    var moreDetails by remember { mutableStateOf(false) }
    var displayedCharacter by remember { mutableStateOf(character) }

    val textColor = MaterialTheme.colorScheme.onSurface

  //rip evilmorty

    val cardColor by animateColorAsState(
        targetValue =  MaterialTheme.colorScheme.surface,
        animationSpec = tween(durationMillis = 500),
    )

    Card(
        colors = CardDefaults.cardColors(
            containerColor = cardColor,

            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .border(
                2.dp,
               MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                navController.navigate("character_details/${character.id}")
            } //hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
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
                AsyncImage(
                    model = character.image,
                    error = painterResource(R.drawable.error),
                    placeholder = painterResource(R.drawable.loading_screen),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Text(
                        text = character.name,
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
                            text = character.status,
                            style = MaterialTheme.typography.bodyMedium,
                            color = statusColor(character.status)
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
                            text = character.species,
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
                            text = character.type,
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
                modifier = Modifier.clickable {
                    navController.navigate("character_details/${character.id}"
                    )}
            )
        }


    }
}




