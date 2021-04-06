package it.oechsler.script

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
        // Storage
        StorageBuilder::class,
        VolumeBuilder::class,
        // Deployments
        DeploymentBuilder::class,
        ContainerBuilder::class,
        ImageBuilder::class,
        PortBuilder::class,
        PublishBuilder::class,
        PublishBuilder::class,
        PublishDomainBuilder::class,
        PublishPortBuilder::class,
        MountBuilder::class,
        // LoadBalancers
        LoadBalancerBuilder::class,
        LoadBalancedDeploymentBuilder::class,
    )

    defaultImports(
        // Syntax constructors
        "it.oechsler.script.language.ResourcesBuilder.Companion.resources",
        "it.oechsler.script.language.VolumeBuilder.Companion.volume",
        "it.oechsler.script.language.DeploymentBuilder.Companion.deployment",
        "it.oechsler.script.language.PathBuilder.Companion.path",
        "it.oechsler.script.language.PathBuilder.Companion.root",
        "it.oechsler.script.language.PermissionBuilder.Companion.permission",
        "it.oechsler.script.language.PermissionBuilder.Companion.default",
        "it.oechsler.script.language.PermissionBuilder.Companion.everyone",
        // Extension functions
        "it.oechsler.script.extensions.gb",
        "it.oechsler.script.extensions.mb"
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
