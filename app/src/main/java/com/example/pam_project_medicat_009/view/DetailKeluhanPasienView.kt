package com.example.pam_project_medicat_009.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pam_project_medicat_009.viewmodel.KeluhanDetailPasienViewModel
import com.example.pam_project_medicat_009.viewmodel.KeluhanViewModel

@Composable
fun DetailKeluhanPasienView(
    keluhanId: Int,
    pasienId: Int,
    navController: NavController
) {
    val detailVm: KeluhanDetailPasienViewModel = viewModel()
    val keluhanVm: KeluhanViewModel = viewModel()

    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(keluhanId) {
        detailVm.loadDetail(keluhanId)
    }

    when {
        detailVm.isLoading -> {
            Box(Modifier.fillMaxSize(), Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        detailVm.keluhan != null -> {
            val k = detailVm.keluhan!!

            Column(Modifier.padding(24.dp)) {

                Text("Detail Keluhan", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))

                Text("Gejala: ${k.gejalaUtama}")
                Text("Deskripsi: ${k.deskripsi ?: "-"}")
                Text("Status: ${k.status}")

                Spacer(Modifier.height(20.dp))

                // =========================
                // RESPONS DOKTER
                // =========================
                if (k.respons != null) {
                    Divider()
                    Spacer(Modifier.height(12.dp))

                    Text("Respons Dokter", fontWeight = FontWeight.Bold)
                    Text("Dokter: ${k.respons.dokter.nama}")
                    Text("Diagnosis: ${k.respons.diagnosis_sementara}")
                    Text("Anjuran: ${k.respons.anjuran_polahidup}")
                    Text("Rujukan: ${k.respons.rekomendasi_rujukan}")
                } else {
                    Text("Belum ada respons dokter", color = Color.Gray)
                }

                // =========================
                // UPDATE & DELETE
                // =========================
                if (k.pasienId == pasienId) {

                    Spacer(Modifier.height(24.dp))
                    Divider()
                    Spacer(Modifier.height(12.dp))

                    // UPDATE hanya jika BELUM direspons
                    if (k.respons == null) {
                        Button(
                            onClick = {
                                navController.navigate(
                                    "detail_keluhan_edit/${k.idKeluhan}"
                                )
                            }
                        ) {
                            Text("Update Keluhan")
                        }

                        Spacer(Modifier.height(8.dp))
                    }

                    // DELETE selalu tersedia
                    Button(
                        onClick = { showDeleteDialog = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        )
                    ) {
                        Text("Hapus Keluhan")
                    }
                }
            }

            // =========================
            // ALERT DIALOG KONFIRMASI
            // =========================
            if (showDeleteDialog) {
                AlertDialog(
                    onDismissRequest = { showDeleteDialog = false },
                    title = {
                        Text(
                            text = "Hapus Keluhan",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    text = {
                        Text("Apakah Anda yakin ingin menghapus keluhan ini?")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showDeleteDialog = false
                                keluhanVm.deleteKeluhan(k.idKeluhan) {
                                    navController.popBackStack()
                                }
                            }
                        ) {
                            Text("HAPUS", color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { showDeleteDialog = false }
                        ) {
                            Text("BATAL")
                        }
                    }
                )
            }
        }

        else -> {
            Box(Modifier.fillMaxSize(), Alignment.Center) {
                Text("Data tidak ditemukan")
            }
        }
    }
}
