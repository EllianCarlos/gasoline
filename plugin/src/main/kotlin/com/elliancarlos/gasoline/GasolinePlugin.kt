package com.elliancarlos.gasoline

import com.elliancarlos.gasoline.GasolineTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class GasolinePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("gasoline", GasolineTask::class.java)
    }
}
