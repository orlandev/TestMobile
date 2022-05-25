package com.orlandev.testmobile.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.orlandev.testmobile.R
import com.orlandev.testmobile.navigation.NavigationRoute
import com.orlandev.testmobile.ui.theme.TestMobileTheme


@Composable
fun LoginScreen(
    navController: NavController? = null
) {

    val (userName, setUserName) = remember {
        mutableStateOf("")
    }

    val (userPassword, setUserPassword) = remember {
        mutableStateOf("")
    }

    val (passwordVisible, setPasswordVisible) = remember {
        mutableStateOf(false)
    }



    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceAround,

            ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.login_with_simple_account),
                    style = MaterialTheme.typography.caption
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = stringResource(id = R.string.user_name_text)) },
                    value = userName,
                    singleLine = true,
                    onValueChange = setUserName
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = stringResource(id = R.string.password_text)) },
                    value = userPassword,
                    singleLine = true,
                    onValueChange = setUserPassword,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        // Localized description for accessibility services
                        val description = if (passwordVisible) "Hide password" else "Show password"

                        // Toggle button to hide or display password
                        IconButton(onClick = { setPasswordVisible(!passwordVisible) }) {
                            Icon(imageVector = image, description)
                        }
                    }


                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    TextButton(onClick = { /*TODO*/ }) {
                        Text(
                            text = stringResource(id = R.string.forget_password_text),
                            style = MaterialTheme.typography.body2,
                            textDecoration = TextDecoration.Underline
                        )
                    }


                    Button(onClick = { navController?.navigate(NavigationRoute.HomeScreenRoute.route) }) {
                        Text(text = stringResource(id = R.string.ok))
                    }

                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),

                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.sign_with_text)
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }


            Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SignInButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.sign_with_facebook_text),
                    icon = painterResource(id = R.drawable.ic_facebook_signin)
                ) {

                }
                SignInButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.sign_with_google_text),
                    icon = painterResource(id = R.drawable.google_sign_in_btn)
                ) {

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TestMobileTheme {
        LoginScreen()
    }
}