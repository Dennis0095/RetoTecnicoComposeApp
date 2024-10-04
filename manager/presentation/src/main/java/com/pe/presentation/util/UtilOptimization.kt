package com.pe.presentation.util

import com.pe.manager.domain.entity.Frequency
import com.pe.manager.domain.entity.Priority
import com.pe.manager.domain.entity.Recommendation
import com.pe.manager.domain.entity.RegisterApp
import com.pe.manager.domain.entity.StatusApp

fun recommendDeleting(app: RegisterApp): Recommendation {
    return if (app.statusApp == StatusApp.INACTIVE){
        var text = "- Se recomienda eliminar la aplicación, ya que se encuentra inactivo\n"
        if(app.usersActive.toInt() > 0)
            text = "$text- Revisar el porque hay usuarios que están usando el app\n"

        if(app.costs.toDouble() > maxCostInactive)
            text = "$text- Revisar los costos de esta aplicación, tal vez hay servicios que aún se siguen pagando, y hay que cancelarlos, porque el costo es muy alto"

        Recommendation(false, text)
    }else if (app.statusApp == StatusApp.OBSOLETE || app.statusApp == StatusApp.REDUNDANT){
        var text = "- La aplicación es ${app.statusApp?.description}\n"
        var status = false
        if(app.usersActive.toInt()< minActiveUsers){
            text = "$text- Hay menos de 10 usuarios que están usando el app\n - Se recomienda hacer seguimiento a la aplicación, tal vez otras aplicaciones realicen las mismas funcionalidades que está o se podría agregar las funcionalidades en otro sistema."
            status = true
        }

        if(app.numberOfIncidents.toInt() > maxIncidents){
            text = "$text- Hacer seguimiento a las incidencias de esta aplicación, ya que es ${app.statusApp?.description}\n"
            status = true
        }

        if(app.frequency == Frequency.RARELY && app.statusApp == StatusApp.OBSOLETE ){
            text = "$text- Además, la aplicación se usa rara vez. Entonces, se puede evaluar para eliminar.\n"
            status = true
        }else if(app.frequency == Frequency.RARELY && app.statusApp == StatusApp.REDUNDANT ){
            text = "$text- Además, la aplicación se usa rara vez. Entonces, se puede evaluar para trasladar sus funcionalidades a otro sistema.\n"
            status = true
        }

        if(app.costs.toDouble() > maxCost){
            text = "$text- El mantenimiento de esta aplicación es demasiado costosa. Revisar los servicios que se usa.\n"
            status = true
        }

        if(app.priority == Priority.LOW && app.statusApp == StatusApp.OBSOLETE ){
            text = "$text- Además, la prioridad es baja y es obsoleta. Considerar eliminar la aplicación\n"
            status = true
        }

        Recommendation(status, text)
    } else{
        if (app.statusApp == StatusApp.IN_USE && app.frequency == Frequency.RARELY && app.priority != Priority.HIGH){
            var text = "- Se recomienda eliminar el app y trasladar sus funcionalidades a otra aplicación, ya que su frecuencia es rara y la prioridad es baja.\n"
            if(app.usersActive.toInt()< minActiveUsers){
                text = "$text - Además, la cantidad de usuarios que lo usan es mínimo a lo esperado."
            }
            Recommendation(true, text)
        } else if (app.statusApp == StatusApp.IN_USE && app.usersActive.toInt()< minActiveUsers && app.priority != Priority.HIGH){
            var text = "- Se recomienda eliminar el app y trasladar sus funcionalidades a otra aplicación, ya que la prioridad es baja y la cantidad de usuarios que lo usan es mínimo a lo esperado.\n"
            Recommendation(true, text)
        } else
            Recommendation(false, "")
    }
}