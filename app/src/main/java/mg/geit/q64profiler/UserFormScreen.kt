package mg.geit.q64profiler

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormScreen(navController: NavController){
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        BackGroundImage()
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Image(
                painter = painterResource(id = R.drawable.q64profiler_logo__1_) ,
                contentDescription = "logo",
                modifier = Modifier.size(300.dp)
            )

            val username = remember { mutableStateOf("") }
            val email = remember { mutableStateOf("") }
            val phoneNumber = remember { mutableStateOf("") }

            val isButtonEnabled = username.value.isNotBlank() && email.value.isNotBlank() && phoneNumber.value.isNotBlank()

            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text("Username", color = Color.White) },
                textStyle = TextStyle(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, start = 30.dp, end = 30.dp)
                    .height(70.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White.copy(alpha = 0.01f),
                    unfocusedBorderColor = Color.White.copy(alpha = 0.01f),
                    cursorColor = Color.White,
                    containerColor = Color.White.copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email", color = Color.White) },
                textStyle = TextStyle(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, start = 30.dp, end = 30.dp)
                    .height(70.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White.copy(alpha = 0.01f),
                    unfocusedBorderColor = Color.White.copy(alpha = 0.01f),
                    cursorColor = Color.White,
                    containerColor = Color.White.copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                label = { Text("Phone Number", color = Color.White) },
                textStyle = TextStyle(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, start = 30.dp, end = 30.dp)
                    .height(70.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White.copy(alpha = 0.01f),
                    unfocusedBorderColor = Color.White.copy(alpha = 0.01f),
                    cursorColor = Color.White,
                    containerColor = Color.White.copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(20.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    navController.navigate("quiz")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, start = 30.dp, end = 30.dp, bottom = 100.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) Color.Black.copy(alpha = 0.3f) else Color.Black.copy(alpha = 0.1f)
                ),

                enabled = isButtonEnabled
            ) {
                Text(text = "Commencer")
            }


        }

    }
}

@Preview(showBackground = true)
@Composable
fun UserFormScreenPreview() {
    UserFormScreen(navController = NavController(LocalContext.current))
}