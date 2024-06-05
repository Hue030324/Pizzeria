package com.example.pizzeria.header

import android.util.Log
import android.widget.ProgressBar
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import com.example.pizzeria.R
import com.example.pizzeria.ui.theme.bg
import com.example.pizzeria.ui.theme.menu1
import com.example.pizzeria.ui.theme.red
import com.example.pizzeria.ui.theme.yellow1
import com.example.pizzeria.ui.theme.yellow2
import com.example.pizzeria.ui.theme.yellow3
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMotionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileHeader(progress: MutableState<Float>) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var searchbarTxt by remember{ mutableStateOf("") }
    val isSearchbarFoucused = remember{ mutableStateOf(false) }

    val empty by remember{ mutableStateOf("") }



    val motionString = remember {
        context.resources.openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    BackHandler(enabled = isSearchbarFoucused.value) {
        focusManager.clearFocus()
    }

    var hintText by remember{ mutableStateOf("") }
    LaunchedEffect(key1 = true, key2 = isSearchbarFoucused.value ){

        val hintCategories = listOf("Pizza","Burger","Chicken fried","Drink","Fries")
        CoroutineScope(Dispatchers.IO).launch {
            var currentHintIndex = 0
            while (!isSearchbarFoucused.value){
                hintText= hintCategories[currentHintIndex]
                if(currentHintIndex >= hintCategories.size -1) currentHintIndex = 0 else currentHintIndex++
                Log.d("Hint_CHANGE","Looping ...")
                delay(1200L)
            }

        }

    }
    MotionLayout(
        motionScene = MotionScene(content = motionString),
        progress = progress.value,
        modifier = Modifier.fillMaxSize()
    ) {


        Spacer(Modifier.height(15.dp))


        OutlinedTextField(
            value = searchbarTxt,
            onValueChange = { searchbarTxt = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = bg,
                focusedIndicatorColor = yellow2,
                unfocusedIndicatorColor = yellow3
            ),
            shape = RoundedCornerShape(41.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .onFocusChanged {
                    isSearchbarFoucused.value = it.isFocused
                }
                .layoutId("searchbar"),
            singleLine = true,
            placeholder = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(Modifier.width(8.dp))
                    Text("Search $hintText", color = Color.LightGray)
                }
            },
            leadingIcon = {
                Box(
                    modifier = Modifier
                        .size(47.dp)
                        .background(color = yellow1, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            },
            trailingIcon = {
                if (searchbarTxt.isNotEmpty()){
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable{
                            searchbarTxt = empty.toString()
                        }
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(30.dp))


        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("category")
        ) {
            items(6){
                Surface(
                    onClick = { },
                    shape = RoundedCornerShape(42.dp),
                    color = yellow1,
                    modifier = Modifier
                        .width(130.dp)
                        .height(50.dp)
                        .padding(end = 10.dp)
                        .background(
                            MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(42.dp)
                        )
                        .padding(bottom = 1.dp)
                    ,
                    shadowElevation = 10.dp,
                ) {
                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Surface(
                            shape = CircleShape,
                            modifier = Modifier
                                .fillMaxHeight()
                                .size(42.dp),
                            color = Color(0xFFF5F0E4)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pizza),
                                contentDescription = "",
                                modifier = Modifier.padding(3.dp)
//                        contentScale = ContentScale.Crop,
                            )
                        }
                        Text(
                            text = "Pizza",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }

    }

}

@Preview
@Composable
fun previewProfileHeader(){
    val toolbarProgress = remember { mutableStateOf(0f) }
    ProfileHeader(progress = toolbarProgress)
}