package fr.systemathicdev.commons.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.navigateSafe(directions: NavDirections?){
    if(directions != null && currentDestination?.getAction(directions.actionId) !=null){
        navigate(directions)
    }
}