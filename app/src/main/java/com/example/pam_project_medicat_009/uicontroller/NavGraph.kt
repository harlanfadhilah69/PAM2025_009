package com.example.pam_project_medicat_009.uicontroller

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.pam_project_medicat_009.view.*

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // =========================
        // AUTH
        // =========================
        composable("login") {
            LoginView(navController)
        }

        composable("register") {
            RegisterView(navController)
        }

        // =========================
        // PASIEN
        // =========================
        composable(
            route = "home_pasien/{pasienId}",
            arguments = listOf(
                navArgument("pasienId") { type = NavType.IntType }
            )
        ) { backStack ->
            HomePasienView(
                pasienId = backStack.arguments!!.getInt("pasienId"),
                navController = navController
            )
        }

        composable(
            route = "profil_pasien/{pasienId}",
            arguments = listOf(
                navArgument("pasienId") { type = NavType.IntType }
            )
        ) { backStack ->
            ProfilPasienView(
                backStack.arguments!!.getInt("pasienId")
            )
        }

        composable(
            route = "form_keluhan/{pasienId}",
            arguments = listOf(
                navArgument("pasienId") { type = NavType.IntType }
            )
        ) { backStack ->
            FormKeluhanView(
                pasienId = backStack.arguments!!.getInt("pasienId"),
                navController = navController
            )
        }

        // =========================
        // DOKTER
        // =========================
        composable(
            route = "home_dokter/{dokterId}",
            arguments = listOf(
                navArgument("dokterId") { type = NavType.IntType }
            )
        ) { backStack ->
            HomeDokterView(
                dokterId = backStack.arguments!!.getInt("dokterId"),
                navController = navController
            )
        }


        composable(
            route = "form_respons/{keluhanId}/{dokterId}",
            arguments = listOf(
                navArgument("keluhanId") { type = NavType.IntType },
                navArgument("dokterId") { type = NavType.IntType }
            )
        ) { backStack ->
            FormResponsKeluhanView(
                keluhanId = backStack.arguments!!.getInt("keluhanId"),
                dokterId = backStack.arguments!!.getInt("dokterId"),
                navController = navController
            )
        }

        // =========================
        // DETAIL & EDIT KELUHAN
        // =========================
        composable(
            route = "detail_keluhan_edit/{keluhanId}",
            arguments = listOf(
                navArgument("keluhanId") { type = NavType.IntType }
            )
        ) { backStack ->
            DetailKeluhanEditView(
                keluhanId = backStack.arguments!!.getInt("keluhanId"),
                navController = navController
            )
        }

        composable(
            route = "detail_keluhan_pasien/{keluhanId}/{pasienId}",
            arguments = listOf(
                navArgument("keluhanId") { type = NavType.IntType },
                navArgument("pasienId") { type = NavType.IntType }
            )
        ) { backStack ->
            DetailKeluhanPasienView(
                keluhanId = backStack.arguments!!.getInt("keluhanId"),
                pasienId = backStack.arguments!!.getInt("pasienId"),
                navController = navController
            )
        }

        composable(
            route = "jadwal_dokter/{dokterId}",
            arguments = listOf(navArgument("dokterId") { type = NavType.IntType })
        ) {
            JadwalDokterView(it.arguments!!.getInt("dokterId"))
        }

        composable(
            route = "profil_dokter/{dokterId}",
            arguments = listOf(navArgument("dokterId") { type = NavType.IntType })
        ) {
            ProfileDokterView(
                dokterId = it.arguments!!.getInt("dokterId"),
                navController = navController
            )
        }



    }
}
