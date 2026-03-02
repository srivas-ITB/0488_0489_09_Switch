package com.example.app09_Switch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.example.app09_Switch.ui.theme.MyApp_Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp_Theme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyExample(modifier = Modifier.padding( innerPadding))
                }
            }
        }
    }
}


@Composable
fun MyExample(modifier: Modifier = Modifier) {
    Column(
        Modifier.fillMaxSize().verticalScroll( rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        MySwitch1()
        Spacer(modifier = Modifier.padding(10.dp))
        MySwitch2()
        Spacer(modifier = Modifier.padding(10.dp))
        MyCheckedBox1()
        Spacer(modifier = Modifier.padding(10.dp))
        MyCheckedBox2()
        Spacer(modifier = Modifier.padding(10.dp))
        MyCheckedBox3()
        Spacer(modifier = Modifier.padding(10.dp))
        MyCheckedBox4()
        Spacer(modifier = Modifier.padding(10.dp))
        MyTriStateCheckbox1()
        Spacer(modifier = Modifier.padding(10.dp))
        MyRadioButton1()
        Spacer(modifier = Modifier.padding(10.dp))
        MyRadioButton2()
        Spacer(modifier = Modifier.padding(10.dp))
        MyRadioButton3()
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun MySwitch1(){
    Text("Switch bàsic (no fa res)")
    Switch(checked = true, onCheckedChange = {})
}


@Composable
fun MySwitch2(){
    Text("Switch personalitzat i amb canvi de valor")
    var state by rememberSaveable { mutableStateOf(true) }
    Switch(checked = state,
        onCheckedChange = { state = !state },
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            checkedThumbColor = Color.Green,
            checkedBorderColor = Color.Blue,
            uncheckedBorderColor = Color.Yellow,
            checkedTrackColor = Color.Cyan,
            uncheckedTrackColor = Color.Magenta
        ))
}

@Composable
fun MyCheckedBox1() {
    Text("CheckedBox simple (no fa res)")
    Checkbox(checked = true, onCheckedChange = {})
}


@Composable
fun MyCheckedBox2() {
    Text("CheckedBox personalitzat i amb canvi de valor")
    var state by rememberSaveable { mutableStateOf(true) }
    Checkbox(
        checked = state, onCheckedChange = { state = !state },
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Cyan,
            uncheckedColor = Color.Red,
            checkmarkColor = Color.Blue
        )
    )
}



@Composable
fun MyCheckedBox3() {
    Text("CheckedBox acompanyat d'un text (mal alineat)")
    var state by rememberSaveable { mutableStateOf(false) }
    Row(Modifier.wrapContentSize()) {
        Checkbox(checked = state, onCheckedChange = { state = it })
        Spacer(modifier = Modifier.width(8.dp))
        Text("Option 1")
    }
}

@Composable
fun MyCheckedBox4() {
    Text("CheckedBox acompanyat d'un text (ben alineat)")
    var state by rememberSaveable { mutableStateOf(false) }
    Row(Modifier.wrapContentSize()) {
        Checkbox(checked = state, onCheckedChange = { state = it })
        Spacer(modifier = Modifier.width(8.dp))
        Text("Option 1", Modifier.align(CenterVertically))
    }
}


@Composable
fun MyTriStateCheckbox1() {
    Text("TriStateCheckbox")
    var status by rememberSaveable { mutableStateOf(ToggleableState.Off) }
    TriStateCheckbox(state = status, onClick = {
        status = when(status){
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
    //Mostra un text amb el valor actual
    var valorText : String = ""
    when (status ){
        ToggleableState.On -> valorText= "Valor actual: ON"
        ToggleableState.Off -> valorText= "Valor actual: OFF"
        ToggleableState.Indeterminate -> valorText= "Valor actual: Indeterminat"
    }
    Text(valorText)
}

@Composable
fun MyRadioButton1() {
    Text("RadioButton simple (no té sentit que estigui sol...)")
    var status by rememberSaveable { mutableStateOf(false) }
    RadioButton(selected = status, onClick = { status = !status })
}



@Composable
fun MyRadioButton2() {
    Text("RadioButton personalitzat (no té sentit que estigui sol...)")
    var status by rememberSaveable { mutableStateOf(false) }
    RadioButton(
        selected = status,
        onClick = { status = !status },
        colors = RadioButtonDefaults.colors(
            selectedColor = Color.Green,
            unselectedColor = Color.Red
        )
    )
}

@Composable
fun MyRadioButton3() {
    Text("Diversos RadioButtons agrupats")

    var selectedOption by remember { mutableStateOf("Option 1") }
    Row() {
        RadioButton(
            selected = selectedOption == "Option 1",
            onClick = { selectedOption = "Option 1" })
        Text(text = "Option 1", Modifier.align(CenterVertically))
    }
    Row() {
        RadioButton(
            selected = selectedOption == "Option 2",
            onClick = { selectedOption = "Option 2" })
        Text(text = "Option 2", Modifier.align(CenterVertically))
    }
    Row() {
        RadioButton(
            selected = selectedOption == "Option 3",
            onClick = { selectedOption = "Option 3" })
        Text(text = "Option 3", Modifier.align(CenterVertically))
    }
    Row() {
        RadioButton(
            selected = selectedOption == "Option 4",
            onClick = { selectedOption = "Option 4" })
        Text(text = "Option 4", Modifier.align(CenterVertically))
    }

}