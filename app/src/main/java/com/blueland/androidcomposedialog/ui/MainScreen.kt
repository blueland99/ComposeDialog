package com.blueland.androidcomposedialog.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blueland.androidcomposedialog.model.enums.DialogType
import com.blueland.androidcomposedialog.viewmodel.DialogViewModel
import com.blueland.androidcomposedialog.viewmodel.InputDialogViewModel

@Composable
fun MainScreen(
    dialogViewModel: DialogViewModel = hiltViewModel(),
    inputDialogViewModel: InputDialogViewModel = hiltViewModel()
) {
    val TAG = LocalContext.current.javaClass.simpleName

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    dialogViewModel.showDialog(
                        dialogType = DialogType.CONFIRM,
                        title = "Confirm Dialog",
                        content = "Confirm Dialog Content.",
                        onConfirm = {
                            Log.d(TAG, "MainScreen: click ConfirmDialog onConfirm")
                        },
                        onDismiss = {
                            Log.d(TAG, "MainScreen: click ConfirmDialog onDismiss")
                        }
                    )
                }
            ) {
                Text("show confirm dialog")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    dialogViewModel.showDialog(
                        dialogType = DialogType.ALERT,
                        title = "Alert Dialog",
                        content = "Alert Dialog Content.",
                        onConfirm = {
                            Log.d(TAG, "MainScreen: click AlertDialog onConfirm")
                        },
                        onDismiss = {
                            Log.d(TAG, "MainScreen: click AlertDialog onDismiss")
                        }
                    )
                }
            ) {
                Text("show alert dialog")
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    inputDialogViewModel.showDialog(
                        onConfirm = { it ->
                            Log.d(TAG, "MainScreen: click InputDialog onConfirm. value=$it")
                        },
                        onDismiss = {
                            Log.d(TAG, "MainScreen: click InputDialog onDismiss")
                        }
                    )
                }
            ) {
                Text("show input dialog")
            }
        }
    }

    CustomDialog()
    InputDialog()
}