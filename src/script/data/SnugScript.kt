package it.oechsler.script.data

import it.oechsler.script.language.*
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.jvm


@KotlinScript(
    displayName = "Snug Script",
    fileExtension = "snug.kts",
    compilationConfiguration = SnugScriptCompilationConfiguration::class,
    evaluationConfiguration = SnugScriptEvaluationConfiguration::class
)
abstract class SnugScript

object SnugScriptCompilationConfiguration : ScriptCompilationConfiguration({
    defaultImports(
        // deployments
        DeploymentBuilder::class,
        ContainerBuilder::class,
        PortBuilder::class,
        ImageBuilder::class,
        PublishBuilder::class,
        PublishBuilder::class,
        PublishDomainBuilder::class,
        PublishPortBuilder::class,
        // loadBalancers
        LoadBalancerBuilder::class,
        LoadBalancedDeploymentBuilder::class,
    )

    defaultImports(
        "it.oechsler.script.language.ResourcesBuilder.Companion.resources"
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
