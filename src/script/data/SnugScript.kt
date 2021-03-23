package it.oechsler.script.data

import it.oechsler.script.language.ContainerBuilder
import it.oechsler.script.language.DeploymentBuilder
import it.oechsler.script.language.PortBuilder
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.jvm

@Suppress("unused")
@KotlinScript(
    displayName = "Snug Script",
    fileExtension = "snug.kts",
    compilationConfiguration = SnugScriptCompilationConfiguration::class,
    evaluationConfiguration = SnugScriptEvaluationConfiguration::class
)
abstract class SnugScript

object SnugScriptCompilationConfiguration : ScriptCompilationConfiguration({
    defaultImports(
        Container::class,
        Deployment::class,
        Image::class,
        Port::class,
        ContainerBuilder::class,
        DeploymentBuilder::class,
        PortBuilder::class
    )

    defaultImports(
        "it.oechsler.script.language.DeploymentBuilder.Companion.deploy"
    )

    jvm {
        dependenciesFromClassContext(SnugScriptEvaluationConfiguration::class, wholeClasspath = true)
    }

    ide {
        acceptedLocations(ScriptAcceptedLocation.Everywhere)
    }
})

object SnugScriptEvaluationConfiguration : ScriptEvaluationConfiguration({
    scriptsInstancesSharing(true)
})
