package com.orlandev.testmobile.ui.screens.login

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.orlandev.testmobile.R
import com.orlandev.testmobile.navigation.NavigationRoute
import com.orlandev.testmobile.ui.theme.TestMobileTheme
import com.orlandev.testmobile.utils.LoadingState


@Composable
fun LoginScreen(
    navController: NavController? = null,
    viewModel: LoginScreenViewModel = hiltViewModel()
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

    val state by viewModel.loadingState.collectAsState()
    val context = LocalContext.current
    val token = stringResource(R.string.default_web_client_id)


    LaunchedEffect(state) {
        if (state.status == LoadingState.Status.SUCCESS) {
            navController?.navigate(NavigationRoute.HomeScreenRoute.route)
        }
    }


    // Equivalent of onActivityResult
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                viewModel.signWithCredential(credential)
            } catch (e: ApiException) {
                Log.w("GoogleSign", "Google sign in failed ${e.message}", e)
            }
        }

    Box(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(MaterialTheme.colors.primary)
        )

        Column(modifier = Modifier) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .weight(4f),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Card(
                elevation = 10.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .weight(18f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceAround,

                    ) {
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.login_with_simple_account),
                            style = MaterialTheme.typography.caption
                        )
                        OutlinedTextField(
                            leadingIcon = {
                                Icon(Icons.Default.Person, contentDescription = null)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = stringResource(id = R.string.user_name_text)) },
                            value = userName,
                            singleLine = true,
                            onValueChange = setUserName
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = {
                                Icon(Icons.Default.Key, contentDescription = null)
                            },
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
                                val description =
                                    if (passwordVisible) "Hide password" else "Show password"

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

                            //TODO CALL TO FACEBOOK AUTH

                        }
                        SignInButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.sign_with_google_text),
                            icon = painterResource(id = R.drawable.google_sign_in_btn)
                        ) {
                            val gso =
                                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                    .requestIdToken(token)
                                    .requestEmail()
                                    .build()

                            val googleSignInClient = GoogleSignIn.getClient(context, gso)
                            launcher.launch(googleSignInClient.signInIntent)
                        }
                    }
                    when (state.status) {
                        LoadingState.Status.SUCCESS -> {
                            Text(text = "Success")
                        }
                        LoadingState.Status.FAILED -> {
                            Text(text = state.msg ?: "Error")
                        }
                        else -> {}
                    }
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