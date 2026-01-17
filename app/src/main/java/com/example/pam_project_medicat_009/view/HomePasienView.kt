package com.example.pam_project_medicat_009.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pam_project_medicat_009.viewmodel.KeluhanViewModel
import com.example.pam_project_medicat_009.viewmodel.PasienViewModel

// Warna sesuai desain gambar
val MedicatYellow = Color(0xFFF0F31D)
val MedicatBg = Color(0xFFF9F9F7)
val MedicatDarkBlue = Color(0xFF1A3644)
val MedicatGreen = Color(0xFF4C845C)

@Composable
fun HomePasienView(
    pasienId: Int,
    navController: NavController
) {
    val keluhanVM: KeluhanViewModel = viewModel()
    val pasienVM: PasienViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    LaunchedEffect(Unit) {
        pasienVM.loadPasien(pasienId)
        keluhanVM.loadKeluhan(pasienId)
    }

    Scaffold(
        containerColor = MedicatBg,
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                pasienId = pasienId,
                currentRoute = currentRoute
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            // 🔹 HEADER (Halo, Nama Pasien)
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Halo, Selamat Datang Kembali!", // Bagian yang diubah
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 32.sp
                    )
                    Surface(
                        shape = CircleShape,
                        color = Color.White,
                        shadowElevation = 2.dp,
                        modifier = Modifier.size(45.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.padding(4.dp),
                            tint = Color.Black.copy(alpha = 0.7f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // 🔹 CARD STATUS TERAKHIR (Gradient Hijau/Biru)
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(MedicatDarkBlue, MedicatGreen)
                            ),
                            shape = RoundedCornerShape(32.dp)
                        )
                        .padding(24.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            "STATUS KELUHAN TERAKHIR",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // Badge DIRESPONS
                        Surface(
                            color = MedicatYellow,
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(
                                "DIRESPONS",
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        // Info Dokter & Tanggal
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.DateRange,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "12 Oktober 2023 • dr. Siti Aminah",
                                color = Color.White,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // 🔹 BUTTON BUAT KELUHAN (Kuning Bulat)
            item {
                Button(
                    onClick = { navController.navigate("form_keluhan/$pasienId") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MedicatYellow),
                    shape = RoundedCornerShape(28.dp),
                    elevation = ButtonDefaults.buttonElevation(4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AddCircle, contentDescription = null, tint = Color.Black)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "BUAT KELUHAN BARU",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }

            // 🔹 JUDUL RIWAYAT
            item {
                Text(
                    "Riwayat Keluhan Anda",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 🔹 LIST KELUHAN (Card Putih)
            items(keluhanVM.keluhanList) { keluhan ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable {
                            navController.navigate(
                                "detail_keluhan_pasien/${keluhan.idKeluhan}/$pasienId"
                            )
                        },
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Icon Bulat (Contoh: Heart/Medical)
                        Surface(
                            shape = CircleShape,
                            color = MedicatBg,
                            modifier = Modifier.size(45.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                modifier = Modifier.padding(10.dp),
                                tint = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = keluhan.gejalaUtama,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    pasienId: Int,
    currentRoute: String
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {

        NavigationBarItem(
            selected = currentRoute.startsWith("home_pasien"),
            onClick = {
                navController.navigate("home_pasien/$pasienId") {
                    popUpTo("home_pasien/$pasienId") { inclusive = true }
                }
            },
            label = { Text("Beranda") },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                indicatorColor = MedicatYellow
            )
        )

        NavigationBarItem(
            selected = currentRoute.startsWith("profil_pasien"),
            onClick = {
                navController.navigate("profil_pasien/$pasienId")
            },
            label = { Text("Profil") },
            icon = { Icon(Icons.Default.Person, contentDescription = null) }
        )
    }
}
