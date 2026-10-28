package com.example.app_arnauserra.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_arnauserra.core.formatCurrency
import com.example.app_arnauserra.core.isPositive
import com.example.app_arnauserra.core.pricePerPerson

// Main screen of the Split Mini app with a modern orange visual design.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplitMiniScreen() {
    // States to manage text field values, result message, and error state
    var totalText by remember { mutableStateOf("") }
    var peopleText by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Split Mini 🧾", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Bill Splitter",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        
                        // Text field to enter total cost (€)
                        OutlinedTextField(
                            value = totalText,
                            onValueChange = { totalText = it },
                            label = { Text("Total Cost (€)") },
                            placeholder = { Text("E.g., 45.50") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Text field to enter number of people
                        OutlinedTextField(
                            value = peopleText,
                            onValueChange = { peopleText = it },
                            label = { Text("Number of People") },
                            placeholder = { Text("E.g., 3") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Vibrant orange calculate button
                        Button(
                            onClick = {
                                val total = totalText.toDoubleOrNull()
                                val people = peopleText.toIntOrNull()

                                // Validation for incorrect or empty inputs
                                if (total == null || people == null) {
                                    resultText = "Please enter valid numbers."
                                    isError = true
                                } else if (!isPositive(total) && total != 0.0 || people <= 0) {
                                    resultText = "Total must be >= 0 and people > 0."
                                    isError = true
                                } else {
                                    // Calculate price per person and format using Utils.kt functions
                                    val perPerson = pricePerPerson(total, people)
                                    val formattedCurrency = formatCurrency(perPerson)
                                    resultText = "$formattedCurrency / person"
                                    isError = false
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text("Calculate", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }

                        // Display result or error with an attractive visual design
                        if (resultText.isNotEmpty()) {
                            Spacer(Modifier.height(8.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (isError) MaterialTheme.colorScheme.errorContainer
                                        else MaterialTheme.colorScheme.primaryContainer
                                    )
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (isError) resultText else "Result: $resultText",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isError) MaterialTheme.colorScheme.onErrorContainer
                                    else MaterialTheme.colorScheme.onPrimaryContainer,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
