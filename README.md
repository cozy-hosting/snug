# Snug

[![Build Pipeline](https://github.com/cozy-hosting/snug/actions/workflows/default.yml/badge.svg)](https://github.com/cozy-hosting/snug/actions/workflows/default.yml)
![Lines of code](https://img.shields.io/tokei/lines/github/cozy-hosting/snug)

Snug ist ein Commandline Tool mit integrierter Skriptsprache für die **cozy** Cloud Plattform. Die Skriptsprache ist auf
Basis einer internen Kotlin DSL errichtet und bietet die Möglichkeit, komplexe Deployments in Code umzusetzen 
(Infrastructure as Code).

Ein Beispiel für den Einsatz der Skripsprache stellen Szenarien in einer CI/CD Pipeline dar, bei denen ein Skript dazu
dienen kann die Infrastruktur ensprechend des veränderten Codes anzupassen, oder neu bereitzustellen. 
Das Skript lässt sich hierbei aufgrund seiner Basis in Code leicht versionieren.

Darüber hinaus können mit den Skripten auch im Zuge der Plattformentwicklung einfach Templates für *one-click*
Deployments erzeugt sowie ebenfalls von den Betreibern beziehungsweise auch den Kunden selbst herausgegeben und
versioniert werden.

> Das initiale Release konzentriert sich derzeit in größerem Umfang auf die Skriptsprache, als das Commandline Tool.
> Künftig soll aber auch eine Steuerung der Plattform Teil von Snug werden und die Sprache nur ein Modul
> des gesammten Tools sein.
> Darüberhinaus ist die Skripsrache derzeit ein *Proof of Concept*, der Produktiveinsatz ist noch nicht möglich.

## Skriptbeispiel

Das gezeigtes Beispiel stellt einen NGINX Webserver der neusten verfügbaren Version bereit. 
Dieser ist auf Port `80`innerhalb des privaten Netzwerkes der Plattform erreichbar, noch nicht aber im Internet.

```kotlin
deployment("nginx") {
    tags("personal", "website")
    replicas = 3

    container("webserver") {
        image("nginx", "latest")

        ports {
            "http" from 80 to 8080
        }
    }
}
```

## How to build?

### Docker

Möglichst einfach lässt sich Snug erzeugen, indem [Docker](https://www.docker.com/) verwendet wird. 
Hierzu lediglich das Docker Image erstellt werden.
Dies geschieht mit dem unten aufgeführten Befehl.

```shell
docker build -t snug:local <PROJECT ROOT>
```

Anschließend lässt sich die Anwendung auch per Docker Befehl starten.
Snug wird hierbei stateless gestartet.
Es werden also keine Zustände für den nächsten Aufruf gesichert.

```shell
docker run --rm snug:local [ARGS]
```

### Gradle

Ist kein Docker vorhanden so lässt sich Snug auch über [Gradle](https://gradle.org/) erstellen und ausführen.
Hierbei wird allerdings keine Garantie für *"works on your machine"* gegeben.
Es sollte ergo immer [Docker](#Docker) bevorzugt werden.

```shell
# Build manuell ausfürhren
gradle build

# Tests manuell ausführen
gradle test

# Snug ditribution erzeugen
gadle assembleDist

```

## Clean Architecture

![Snug class diagram](http://www.plantuml.com/plantuml/proxy?fmt=svg&src=https://raw.githubusercontent.com/cozy-hosting/snug/main/docs/assets/class-diagram.plantuml)

[Vollständig anzeigen](http://www.plantuml.com/plantuml/proxy?fmt=svg&src=https://raw.githubusercontent.com/cozy-hosting/snug/main/docs/assets/class-diagram.plantuml)

Snug setzt grundlegend die Schichten der Clean Architecture um.
Diese werden im Folgenden kurz von innen nach außen in ihrer vorhandenen Ausprägung erläutert.  

Auf der Schicht des *Domain Codes* setzt das Projekt all seine Datenobjekte um.
Sie übernehmen im Wesentlichen die Strukturierung jener und bilden Zusammenhänge des Domain-Models ab.
Die Schicht des *Application Codes* besteht bei Snug aus den Buildern, sowie den Services.
Diese beinhalten die allgemeine Buisness-Logik und Grammatik der Sprache.
Die von der Kommandozeile aufrufbaren Befehele stellen die *Adapters* des Projektes dar.
Sie werden in einer Art von simplen Controller Objekten konkret umgesetzt.
Die *Plugins* des Projektes sind unteranderem die Bibliotek für die Kommandozeilen-Ausgabe und die Datenpersistierung
der Konfigurationsdatei (ein tatsächliches DBMS wird dafür jedoch nicht verwendet).


## Domain Driven Design
### Ubiquitous Language

| Keyword                | Erklärung des Keywords                                                      |
|------------------------|-----------------------------------------------------------------------------|
| Config                 | Abbildung der Konfigurationsdatei mit allen Sektionen                       |
| AuthConfig             | Abschnitt in der Config spezifisch für Authentifizierung                    |
| AuthToken              | Token mit dem sich ein Nutzer an der API authentifiziert                    |
| Script                 | Ein generisches, ausführbares Skript der DSL                                |
| Resources              | Aggregat mehrerer Scripts zur sequenziellen Abarbeitung                     |
| Deployment             | Abbildung eines Deployments auf der Plattform                               |
| Container              | Repräsentation eines Docker Containers der in einem Deployment liegt        |
| Image                  | Ein Docker Image, welches vom Container genutzt wird                        |
| Port                   | Abbildung eines Socket Ports                                                |
| Path                   | Ein bestimmter, absoluter Pfad auf dem Unix Dateisystem                     |
| Permission             | Abbildung einer Unix Dateisystem Berechtigung                               |
| Mount                  | Zuweisung von Storage auf bestimmten Einhängepunkt eines Deployments        |
| MountType              | Die spezifische Art eines Einhängepunktes                                   |
| Domain                 | Stellt eine FQDN dar, die in einer Liste von PublishedDomains auftaucht     |
| Publish                | Aggregat von PublishedPorts und PublishedDomains für ein Deployment         |
| PublishedPort          | Zuweisung eines Container Ports auf einen zufälligen öffentlichen Port      |
| PublishedDomains       | Zuweisung eines Container Ports auf eine bestimmte Domain                   |
| LoadBalancer           | Lastverteiler auf der Plattform                                             |
| LoadBalancedDeployment | Ein Deployment, das an einen LoadBalancer gekoppelt ist                     |
| Storage                | Abbildung eines Speichermediums mit StorageClass und Size auf der Plattform |
| StorageClass           | Die spezifische Art eines Speichermediums                                   |
| Size                   | Abbildung einer beliebigen Speichergröße normiert auf Gigabyte              |

###  Analyse und Begründung der verwendeten Muster
Snug verwendet *Aggregates*, *Entities*, *Value-Objects*, *Repositories* und *Services*.
Einige dieser gehen auch bereits aus dem vorangehenden Abschnitt [Ubiquitous Language](#ubiquitous-language) hervor.

So gibt es beispielsweise *Aggregates*, wie etwa `Ressources`, das Skripte gebündelt bereitstellt, 
um batch-prossesing zu erlauben.
*Entities* werden in Snug formuliert und sind Datenobjekte, sowie Bausteine der internen DSL.
Alle Datenobjekte in Snug sind zusätzlich *Value-Objects*.
Diese Basis ergibt sich aus den in Kotlin spezifischen `data class` Objekten.
*Repositories* kommen in Snug für klassische Persitenzaufgaben zum Einsatz.
So gibt es etwa das `ConfigRepository`, welches Abbilder der `Config` auf dem Dateisystem verwaltet.
Neben ihnen werden auch *Services* eingetzt.
Bei ihnen geht es um die allgemeine Bündelung von Funktionalitäten für einen bestimmten Bereich der Domäne.
Ein solch sezifischer Problembereich ist in Snug das Laden und Ausführung von Skripten.
Hierzu existiert dann ein `ScriptService`, der Funktionalität zur bewältigung eben dieses Problem bereitstellt.

## Programming Principles

### SOLID

Hier wird das Single Responsibility Principle verwendet.
Die Klassen sind nur zuständig für eine Funktion und falls andere Funktionen davon abhängig sind, wurden diese in 
Klassen ausgelagert. 
Beispielsweise ist die Klasse "ContainerBuilder" lediglich zuständig um ein Container-Objekt zu erzeugen. 
Andere Attribute, die für den Container nötig sind, wie bspw. das Image, werden von einer seperaten Klasse generiert.

### GRASP

Es wird High Cohesion verwendet, was auch bei dem in [SOLID](#SOLID) genannten Beispiel hervorgeht.
Die Builder-Klassen haben untereinander eine hohe Abhängigkeit, falls weitere Attribute für das zu buildende Objekt 
benötigt werden.
Die Klasse macht daher genau das, nachdem sie benannt wurde und nicht mehr.

### DRY

Um Wiederholungen zu vermeiden, wurden diese Stellen, an denen es möglich war, in eine Funktion ausgelagert. 


## Entwurfsmuster
Das hier verwendete Entwurfsmuster ist das Factory-Pattern. 
Die Builder fungieren als Factory für die eigentlichen Datenobjekte, die aus der Kombination in einem Skript entstehen.
Jeder Builder bietet für die entsprechende Konfiguration eines individuellen Objektes bestimmte Methoden an.
Diese Methoden bestimmen auch indirekt die Grammatik der internen DSL.
Letztlich kann mit einer spezifisch für das resultierende Objekt vorgehsehenen Methode 
(z.B. ``toContainer()``), das entsprechnde Datenobjekt erzeugt werden.

Das Pattern ist für den Einsatzzweck in internen DSL quasi unverzichtbar, da es die Grammatik der Sprache prägt.
Jedoch sind auch andere Patterns zu diesem Zwecke nutzbar.
Man könnte sich alternativ auch für das Decorator-Patten entscheiden, 
oder eine Konbination aus beiden Pattern zum Einsatz bringen.


## Refactoring
### Code smells
Die Analyse mithilfe von Sonarqube ergab folgendes Ergebnis:

![Sonarqube Ergebnis](https://github.com/cozy-hosting/snug/blob/main/docs/assets/code-smells.png?raw=true)

Der einzige Code Smell, der gefunden wurde, war ein TODO-Kommentar, der eine Erinnerung für die Zukunft sein sollte.

### Refactorings
Aufgrund der fehlenden Code-Smells ist es uns nun nicht möglich zwei Refactorings durchzuführen.


## Unit tests

Es wurden 29 Unit Tests implementiert, die die einzelnen Komponenten der Scriptsprache testen.
Im gesamten Projekt haben wir 49% Line-Coverage, jedoch beziehen sich die Tests hauptsächlich auf das "script"-Package, 
und dort beträgt die Line-Coverage 62%. Die Class-Coverage beträgt insgesamt 56% und die Method-Coverage beträgt 62%.
Die Tests werden ausgeführt, sobald das Dockerimage gebuildet wird. 
Die Dateien "(In)ValidTestValues" stellen valide/invalide Values für die Tests zur Verfügung.
Diese werden außerhalb der Tests definiert, da dies andernfalls in duplicate Code resultieren könnte, da
die Values in mehreren Tests identisch wären.
Es konnten bei den Tests keine Mocks verwendet werden, da dies lediglich für die API sinnvoll gewesen wäre.
Die API-Schnittstelle existiert jedoch derzeit noch nicht.
