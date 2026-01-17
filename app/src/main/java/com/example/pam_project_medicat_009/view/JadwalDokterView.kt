package com.example.pam_project_medicat_009.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pam_project_medicat_009.modeldata.Jadwal
import com.example.pam_project_medicat_009.viewmodel.JadwalViewModel

@Composable
fun JadwalDokterView(
    dokterId: Int
) {
    val vm: JadwalViewModel = viewModel()

    // form tambah
    var hari by remember { mutableStateOf("") }
    var jam by remember { mutableStateOf("") }
    var lokasi by remember { mutableStateOf("") }

    // dialog edit
    var showEditDialog by remember { mutableStateOf(false) }
    var selectedJadwal by remember { mutableStateOf<Jadwal?>(null) }

    // dialog delete
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(dokterId) {
        vm.loadJadwalDokter(dokterId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            "Jadwal Praktek Dokter",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(hari, { hari = it }, label = { Text("Hari") })
        OutlinedTextField(jam, { jam = it }, label = { Text("Jam Mulai") })
        OutlinedTextField(lokasi, { lokasi = it }, label = { Text("Lokasi RS") })

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                vm.tambahJadwal(dokterId, hari, jam, lokasi) {
                    vm.loadJadwalDokter(dokterId)
                    hari = ""
                    jam = ""
                    lokasi = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tambah Jadwal")
        }

        Spacer(Modifier.height(24.dp))

        LazyColumn {
            items(vm.jadwalList) { jadwal ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(Modifier.padding(12.dp)) {

                        Text(jadwal.hari, fontWeight = FontWeight.Bold)
                        Text("Jam: ${jadwal.jam_mulai}")
                        Text(jadwal.lokasi_rs)

                        Spacer(Modifier.height(8.dp))

                        Row {
                            Button(onClick = {
                                selectedJadwal = jadwal
                                showEditDialog = true
                            }) {
                                Text("Edit")
                            }

                            Spacer(Modifier.width(8.dp))

                            Button(
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                onClick = {
                                    selectedJadwal = jadwal
                                    showDeleteDialog = true
                                }
                            ) {
                                Text("Hapus", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }

    // ================= EDIT =================
    if (showEditDialog && selectedJadwal != null) {
        var editHari by remember { mutableStateOf(selectedJadwal!!.hari) }
        var editJam by remember { mutableStateOf(selectedJadwal!!.jam_mulai) }
        var editLokasi by remember { mutableStateOf(selectedJadwal!!.lokasi_rs) }

        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text("Edit Jadwal") },
            text = {
                Column {
                    OutlinedTextField(editHari, { editHari = it }, label = { Text("Hari") })
                    OutlinedTextField(editJam, { editJam = it }, label = { Text("Jam") })
                    OutlinedTextField(editLokasi, { editLokasi = it }, label = { Text("Lokasi RS") })
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    vm.updateJadwal(
                        selectedJadwal!!.id,
                        dokterId,
                        editHari,
                        editJam,
                        editLokasi
                    ) {
                        vm.loadJadwalDokter(dokterId)
                        showEditDialog = false
                    }
                }) {
                    Text("SIMPAN")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) {
                    Text("BATAL")
                }
            }
        )
    }

    // ================= DELETE =================
    if (showDeleteDialog && selectedJadwal != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Hapus Jadwal") },
            text = { Text("Yakin ingin menghapus jadwal ini?") },
            confirmButton = {
                TextButton(onClick = {
                    vm.deleteJadwal(selectedJadwal!!.id) {
                        vm.loadJadwalDokter(dokterId)
                        showDeleteDialog = false
                    }
                }) {
                    Text("HAPUS", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("BATAL")
                }
            }
        )
    }
}
