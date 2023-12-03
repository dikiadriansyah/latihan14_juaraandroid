package com.example.latihan14

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.latihan14.data.Hero
import com.example.latihan14.data.HeroesRepository
import com.example.latihan14.ui.theme.Latihan14Theme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HeroesList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){

    val visibleState = remember{
        MutableTransitionState(false).apply{
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(animationSpec = spring(dampingRatio = DampingRatioLowBouncy)),
        exit = fadeOut(),
        modifier = modifier
    ){
        LazyColumn(contentPadding = contentPadding) {
        itemsIndexed(heroes){index, hero->
            HeroItem(hero = hero, modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .animateEnterExit(
                    enter = slideInVertically(
                        animationSpec = spring(
                            stiffness = StiffnessVeryLow,
                            dampingRatio = DampingRatioLowBouncy
                        ),
                        initialOffsetY = {
                            it * (index + 1)
                        }
                    )

                )
            )

        }

        }
    }


}


@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
){

//    var expanded by remember{mutableStateOf(false)}

//    val color by animateColorAsState(
//    targetValue = if(expanded){
//        MaterialTheme.colorScheme.tertiaryContainer
//    }else{
//        MaterialTheme.colorScheme.primaryContainer
//    }
//)

    Card(modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)){

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium))
            .sizeIn(minHeight = 72.dp)
        ){
            Column(modifier = Modifier.weight(1f)) {
                HeroInformation(hero.nameRes, hero.descriptionRes)
            }

            Spacer(Modifier.width(16.dp))
            Box(modifier = modifier
                .size(dimensionResource(R.dimen.image_size))
                .clip(RoundedCornerShape(8.dp))
            ){
                HeroIcon(hero.imageRes)

            }
        }

    }

}


@Composable
fun HeroIcon(@DrawableRes heroIcon: Int){
    Image(
        contentScale = ContentScale.FillWidth,
        contentDescription = null,
        alignment = Alignment.TopCenter,
        painter = painterResource(heroIcon)

    )
}

@Composable
fun HeroInformation(
    @StringRes heroName: Int,
    heroDesc: Int
){
        Text(
            text = stringResource(heroName),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(heroDesc),
            style = MaterialTheme.typography.bodyLarge
        )
}

// menampilkan layar uji coba dengan tema terang
@Preview("Light Theme")

// menampilkan layar uji coba dengan tema gelap
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)

@Composable
fun HeroPreview(){
    val hero = Hero(
        R.drawable.android_superhero1,
        R.string.hero1,
        R.string.description1
    )
    Latihan14Theme{
        HeroItem(hero = hero)
    }
}

@Preview("Heroes List")
@Composable
fun HeroesPreview(){
    Latihan14Theme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background){
            HeroesList(heroes = HeroesRepository.heroes)
        }
        
    }
}
